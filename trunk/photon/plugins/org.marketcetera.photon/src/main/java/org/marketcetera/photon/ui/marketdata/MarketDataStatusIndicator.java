package org.marketcetera.photon.ui.marketdata;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.marketcetera.marketdata.FeedStatus;
import org.marketcetera.photon.Messages;
import org.marketcetera.photon.PhotonPlugin;
import org.marketcetera.photon.marketdata.IFeedStatusChangedListener;
import org.marketcetera.photon.marketdata.IMarketDataManager;
import org.marketcetera.photon.ui.StatusIndicatorContributionItem;
import org.marketcetera.util.log.I18NMessage1P;
import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * Contribution item that displays the status of the active market data feed.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: MarketDataStatusIndicator.java 16854 2014-03-12 01:54:42Z colin $
 * @since 1.0.0
 */
@ClassVersion("$Id: MarketDataStatusIndicator.java 16854 2014-03-12 01:54:42Z colin $")//$NON-NLS-1$
public class MarketDataStatusIndicator extends StatusIndicatorContributionItem {

	private Label imageLabel;
	private final IMarketDataManager mMarketDataManager = PhotonPlugin
			.getDefault().getMarketDataManager();
	private IFeedStatusChangedListener mListener;

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		layout.marginWidth = 2;
		composite.setLayout(layout);
		imageLabel = new Label(composite, SWT.NONE);
		mListener = new IFeedStatusChangedListener() {

			@Override
			public void feedStatusChanged(IFeedStatusEvent event) {
				imageLabel.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						updateLabel();
					}
				});
			}
		};
		mMarketDataManager.addActiveFeedStatusChangedListener(mListener);
		updateLabel();
		return composite;
	}
	
	@Override
	public void dispose() {
		if (mListener != null) {
			mMarketDataManager
					.removeActiveFeedStatusChangedListener(mListener);
			mListener = null;
		}
		super.dispose();
	}

	private void updateLabel() {
		FeedStatus newStatus = mMarketDataManager.getFeedStatus();
		String name = "Market Data Nexus";//mMarketDataManager.getActiveFeedName();
		Image image;
		I18NMessage1P tooltip;
		switch (newStatus) {
		case AVAILABLE:
			image = getOnImage();
			tooltip = Messages.MARKET_DATA_STATUS_ON_TOOLTIP;
			break;
		case ERROR:
			image = getErrorImage();
			tooltip = Messages.MARKET_DATA_STATUS_ERROR_TOOLTIP;
			break;
		default:
			image = getOffImage();
			tooltip = Messages.MARKET_DATA_STATUS_OFF_TOOLTIP;
			break;
		}
		imageLabel.setImage(image);
		if (name == null) {
			imageLabel.setToolTipText(Messages.MARKET_DATA_STATUS_NO_FEED_TOOLTIP.getText());
		} else {
			imageLabel.setToolTipText(tooltip.getText(name));
		}
	}
}
