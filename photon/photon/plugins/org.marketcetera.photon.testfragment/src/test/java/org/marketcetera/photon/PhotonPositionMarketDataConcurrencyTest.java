package org.marketcetera.photon;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.marketcetera.core.position.MarketDataSupport.InstrumentMarketDataEvent;
import org.marketcetera.core.position.MarketDataSupport.InstrumentMarketDataListener;
import org.marketcetera.core.position.MarketDataSupport.InstrumentMarketDataListenerBase;
import org.marketcetera.photon.marketdata.IMarketData;
import org.marketcetera.photon.marketdata.IMarketDataReference;
import org.marketcetera.photon.model.marketdata.MDFactory;
import org.marketcetera.photon.model.marketdata.MDItem;
import org.marketcetera.photon.model.marketdata.MDLatestTick;
import org.marketcetera.photon.model.marketdata.MDMarketstat;
import org.marketcetera.photon.model.marketdata.MDPackage;
import org.marketcetera.photon.test.MultiThreadedTestBase;
import org.marketcetera.trade.Equity;
import org.marketcetera.trade.Instrument;
import org.marketcetera.util.log.SLF4JLoggerProxy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.ImmutableMap.Builder;

/* $License$ */

/**
 * Test {@link PhotonPositionMarketData} concurrency issues.
 * <p>
 * This class provides three threads that can be spawned:
 * <ul>
 * <li>ListenerThread - add/removes symbol changed listeners</li>
 * <li>LatestTickThread - simulates latest tick updates</li>
 * <li>MarketstatThread - simulates marketstat updates</li>
 * </ul>
 * 
 * <code>mSimulatedDataFlowLock</code> is used to simulate a lock on market
 * data. Market data updates are done when the lock is held. Market data
 * reference disposal is also done while holding the lock.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: PhotonPositionMarketDataConcurrencyTest.java 10628 2009-06-26
 *          02:20:04Z will $
 * @since 1.5.0
 */
public class PhotonPositionMarketDataConcurrencyTest extends
        MultiThreadedTestBase implements MDPackage.Literals {
    private final Random mGenerator = new Random();
    private final ImmutableList<String> mSymbols = ImmutableList.of("IBM",
            "GOOG", "METC", "JAVA", "YHOO");
    private final ImmutableMap<Instrument, MDLatestTick> mLatestTicks;
    private final ImmutableMap<Instrument, MDMarketstat> mMarketstats;
    private final Thread mLatestTickThread;
    private final Thread mMarketstatThread;
    private final Object mSimulatedDataFlowLock = new Object();

    public PhotonPositionMarketDataConcurrencyTest() {
        Builder<Instrument, MDLatestTick> tickBuilder = ImmutableMap.builder();
        Builder<Instrument, MDMarketstat> statBuilder = ImmutableMap.builder();
        for (String symbol : mSymbols) {
            Equity instrument = new Equity(symbol);
            MDLatestTick tick = MDFactory.eINSTANCE.createMDLatestTick();
            tick.eSet(MD_ITEM__INSTRUMENT, instrument);
            tickBuilder.put(instrument, tick);
            MDMarketstat stat = MDFactory.eINSTANCE.createMDMarketstat();
            stat.eSet(MD_ITEM__INSTRUMENT, instrument);
            statBuilder.put(instrument, stat);
        }
        mLatestTicks = tickBuilder.build();
        mMarketstats = statBuilder.build();
        mLatestTickThread = new MarketDataThread<MDLatestTick>(
                "LatestTicksThread", mLatestTicks.values()) {
            @Override
            protected void update(MDLatestTick randomElement) {
                randomElement
                        .eSet(MD_LATEST_TICK__PRICE, getRandomBigDecimal());
                randomElement.eSet(MD_LATEST_TICK__SIZE, getRandomBigDecimal());
            }
        };
        mMarketstatThread = new MarketDataThread<MDMarketstat>(
                "MarketstatsThread", mMarketstats.values()) {
            @Override
            protected void update(MDMarketstat randomElement) {
                randomElement.eSet(MD_MARKETSTAT__CLOSE_PRICE,
                        getRandomBigDecimal());
                randomElement.eSet(MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE,
                        getRandomBigDecimal());
            }
        };
    }

    private final Thread mListenersThread = new ReportingThread(
            "ListenerThread") {

        private final ListMultimap<Equity, InstrumentMarketDataListener> mListeners = LinkedListMultimap
                .create();

        @Override
        public void runWithReporting() {
            while (true) {
                if (isInterrupted())
                    return;
                Equity equity = new Equity(getRandomElement(mSymbols));
                InstrumentMarketDataListener listener = new InstrumentMarketDataListenerBase() {

                    @Override
                    public void symbolTraded(InstrumentMarketDataEvent event) {
                        try {
                            SLF4JLoggerProxy.trace(getName(), event.toString());
                            Thread.sleep(19);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    @Override
                    public void closePriceChanged(InstrumentMarketDataEvent event) {
                        try {
                            SLF4JLoggerProxy.trace(getName(), event.toString());
                            Thread.sleep(37);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                };
                mListeners.put(equity, listener);
                SLF4JLoggerProxy.trace(getName(), "Adding listener");
                try {
                    sleep(12);
                } catch (InterruptedException e) {
                    return;
                }
                mFixture.addInstrumentMarketDataListener(equity, listener);
                equity = new Equity(getRandomElement(mSymbols));
                List<InstrumentMarketDataListener> list = mListeners.get(equity);
                if (!list.isEmpty()) {
                    SLF4JLoggerProxy.trace(getName(), "Removing listener");
                    int index = list.size() == 1 ? 0 : mGenerator.nextInt(list
                            .size() - 1);
                    mFixture.removeInstrumentMarketDataListener(equity,
                            list.get(index));
                }
                try {
                    sleep(7);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    };

    private abstract class MarketDataThread<T extends MDItem> extends
            ReportingThread {
        private final ImmutableList<T> mItems;

        public MarketDataThread(String name, ImmutableCollection<T> items) {
            super(name);
            mItems = ImmutableList.copyOf(items);
        }

        @Override
        public void runWithReporting() {
            while (true) {
                if (isInterrupted())
                    return;
                T randomElement = getRandomElement(mItems);
                SLF4JLoggerProxy.trace(getName(), "Updating " + randomElement);
                synchronized (mSimulatedDataFlowLock) {
                    update(randomElement);
                    try {
                        sleep(3);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

        protected abstract void update(T randomElement);
    }

    private class MockReference<T extends MDItem> implements
            IMarketDataReference<T> {

        private final T mItem;

        public MockReference(T item) {
            mItem = item;
        }

        @Override
        public void dispose() {
            synchronized (mSimulatedDataFlowLock) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        @Override
        public T get() {
            return mItem;
        }

    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(mGenerator.nextInt(list.size() - 1));
    }

    private BigDecimal getRandomBigDecimal() {
        return new BigDecimal(mGenerator.nextDouble());
    }

    private PhotonPositionMarketData mFixture;
    private IMarketData mMockMarketData;

    @Before
    public void before() {
        // BasicConfigurator.configure();
        // Logger.getLogger("LatestTicksThread").setLevel(Level.TRACE);
        // Logger.getLogger("MarketstatsThread").setLevel(Level.TRACE);
        // Logger.getLogger("ListenerThread").setLevel(Level.TRACE);
        mMockMarketData = mock(IMarketData.class);
        mFixture = new PhotonPositionMarketData(mMockMarketData);
        when(mMockMarketData.getLatestTick((Instrument) anyObject())).thenAnswer(
                new Answer<IMarketDataReference<MDLatestTick>>() {
                    @Override
                    public IMarketDataReference<MDLatestTick> answer(
                            InvocationOnMock invocation) throws Throwable {
                        return new MockReference<MDLatestTick>(mLatestTicks
                                .get(invocation.getArguments()[0]));
                    }
                });
        when(mMockMarketData.getMarketstat((Instrument) anyObject())).thenAnswer(
                new Answer<IMarketDataReference<MDMarketstat>>() {
                    @Override
                    public IMarketDataReference<MDMarketstat> answer(
                            InvocationOnMock invocation) throws Throwable {
                        return new MockReference<MDMarketstat>(mMarketstats
                                .get(invocation.getArguments()[0]));
                    }
                });
    }

    @Test(timeout = 10000)
    public void testSimpleRun() throws Exception {
        mListenersThread.start();
        mMarketstatThread.start();
        mLatestTickThread.start();
        Thread.sleep(6000);
        checkFailure();
        mListenersThread.interrupt();
        mMarketstatThread.interrupt();
        mLatestTickThread.interrupt();
        checkFailure();
        mListenersThread.join();
        mMarketstatThread.join();
        mLatestTickThread.join();
    }

    @Test(timeout = 10000)
    public void testDispose() throws Exception {
        mListenersThread.start();
        mMarketstatThread.start();
        mLatestTickThread.start();
        Thread.sleep(2000);
        mFixture.dispose();
        checkFailure();
        Thread.sleep(1000);
        mListenersThread.interrupt();
        mMarketstatThread.interrupt();
        mLatestTickThread.interrupt();
        checkFailure();
        mListenersThread.join();
        mMarketstatThread.join();
        mLatestTickThread.join();
    }

}
