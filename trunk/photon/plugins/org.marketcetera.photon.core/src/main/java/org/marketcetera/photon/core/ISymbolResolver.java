package org.marketcetera.photon.core;

import org.marketcetera.trade.Instrument;
import org.marketcetera.trade.utils.UnresolvableSymbol;
import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * Provides symbol-to-instrument services.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: ISymbolResolver.java 16604 2013-06-26 14:49:42Z colin $
 * @since $Release$
 */
@ClassVersion("$Id: ISymbolResolver.java 16604 2013-06-26 14:49:42Z colin $")
public interface ISymbolResolver
{
    /**
     * Resolves the given symbol to an <code>Instrument</code>.
     *
     * @param inSymbol a <code>String</code> value
     * @return an <code>Instrument</code> value
     * @throws UnresolvableSymbol if the symbol cannot be resolved
     */
    public Instrument resolveSymbol(String inSymbol);
}
