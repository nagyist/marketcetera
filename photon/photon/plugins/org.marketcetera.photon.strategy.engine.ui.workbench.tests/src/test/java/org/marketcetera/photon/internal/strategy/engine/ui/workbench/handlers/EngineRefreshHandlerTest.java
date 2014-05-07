package org.marketcetera.photon.internal.strategy.engine.ui.workbench.handlers;

import org.junit.runner.RunWith;
import org.marketcetera.photon.test.WorkbenchRunner;

/* $License$ */

/**
 * Tests {@link RefreshHandler}.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: EngineRefreshHandlerTest.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
@RunWith(WorkbenchRunner.class)
public class EngineRefreshHandlerTest extends ChangeEngineHandlerTestBase {

    public EngineRefreshHandlerTest() {
        super("Refresh", "Refreshing engine");
    }

    @Override
    protected void acceptChange(BlockingConnection connection, Object object)
            throws Exception {
        connection.acceptRefresh(object);
    }
}
