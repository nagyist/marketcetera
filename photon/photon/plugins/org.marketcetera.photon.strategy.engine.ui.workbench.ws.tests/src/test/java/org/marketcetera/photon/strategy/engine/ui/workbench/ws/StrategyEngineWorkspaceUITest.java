package org.marketcetera.photon.strategy.engine.ui.workbench.ws;

import org.junit.Test;
import org.marketcetera.photon.strategy.engine.ui.workbench.ws.StrategyEngineWorkspaceUI;
import org.marketcetera.photon.test.OSGITestUtil;

/* $License$ */

/**
 * Tests {@link StrategyEngineWorkspaceUI}.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: StrategyEngineWorkspaceUITest.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
public class StrategyEngineWorkspaceUITest {

    @Test
    public void testPluginId() {
        OSGITestUtil.assertBundle(StrategyEngineWorkspaceUI.PLUGIN_ID);
    }
}
