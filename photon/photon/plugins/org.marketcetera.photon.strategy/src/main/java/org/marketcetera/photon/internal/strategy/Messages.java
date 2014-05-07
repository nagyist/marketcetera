package org.marketcetera.photon.internal.strategy;

import org.marketcetera.photon.commons.ReflectiveMessages;
import org.marketcetera.util.log.I18NMessage0P;
import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * The internationalization constants used by this package.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: Messages.java 16154 2012-07-14 16:34:05Z colin $
 * @since 1.0.0
 */
@ClassVersion("$Id: Messages.java 16154 2012-07-14 16:34:05Z colin $")//$NON-NLS-1$
public final class Messages {
	
    static I18NMessage0P TRADE_SUGGESTION_MANAGER_INVALID_DATA_NO_ORDER;
	
	static {
        ReflectiveMessages.init(Messages.class);
    }

    private Messages() {
        throw new AssertionError("non-instantiable"); //$NON-NLS-1$
    }
}
