package org.marketcetera.event.impl;

import org.marketcetera.event.Event;
import org.marketcetera.util.misc.ClassVersion;

/* $License$ */

/**
 * Constructs objects of the given type of {@link Event}.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: EventBuilder.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
@ClassVersion("$Id: EventBuilder.java 16154 2012-07-14 16:34:05Z colin $")
public interface EventBuilder<E extends Event>
{
    /**
     * Causes the <code>EventBuilder</code> to create an object of the given type.
     * 
     * <p>This method will invoke the object type's validation routine, if any.
     * 
     * @return an <code>E</code> value
     */
    public E create();
}
