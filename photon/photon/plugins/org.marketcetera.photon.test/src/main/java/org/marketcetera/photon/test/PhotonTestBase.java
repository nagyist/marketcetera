package org.marketcetera.photon.test;

import java.util.Locale;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.marketcetera.util.log.ActiveLocale;
import org.marketcetera.util.test.TestCaseBase;

/* $License$ */

/**
 * Extends {@link TestCaseBase} to ensure all logs categories are off and
 * process active local is Locale.ROOT.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: PhotonTestBase.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
public class PhotonTestBase extends TestCaseBase {
    
    private static Locale sProcessLocale;

    @Override
    public void setupTestCaseBase() {
        BasicConfigurator.resetConfiguration();
        super.setupTestCaseBase();
        setDefaultLevel(Level.OFF);
    }
    
    @BeforeClass
    public static void pushRootLocale() {
        sProcessLocale = ActiveLocale.getProcessLocale();
        ActiveLocale.setProcessLocale(Locale.ROOT);
    }
    
    @AfterClass
    public static void popLocale() {
        ActiveLocale.setProcessLocale(sProcessLocale);
    }
}
