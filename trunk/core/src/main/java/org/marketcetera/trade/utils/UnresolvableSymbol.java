package org.marketcetera.trade.utils;

import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * Indicates that a symbol could not be resolved to an <code>Instrument</code>.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: UnresolvableSymbol.java 16604 2013-06-26 14:49:42Z colin $
 * @since $Release$
 */
@ClassVersion("$Id: UnresolvableSymbol.java 16604 2013-06-26 14:49:42Z colin $")
public class UnresolvableSymbol
        extends RuntimeException
{
    /**
     * Create a new UnresolvableSymbol instance.
     */
    public UnresolvableSymbol()
    {
        super();
    }
    /**
     * Create a new UnresolvableSymbol instance.
     *
     * @param inMessage a <code>String</code> value
     */
    public UnresolvableSymbol(String inMessage)
    {
        super(inMessage);
    }
    /**
     * Create a new UnresolvableSymbol instance.
     *
     * @param inCause a <code>Throwable</code> value
     */
    public UnresolvableSymbol(Throwable inCause)
    {
        super(inCause);
    }
    /**
     * Create a new UnresolvableSymbol instance.
     *
     * @param inMessage a <code>String</code> value
     * @param inCause a <code>Throwable</code> value
     */
    public UnresolvableSymbol(String inMessage,
                              Throwable inCause)
    {
        super(inMessage,
              inCause);
    }
    private static final long serialVersionUID = -5186563438454940394L;
}
