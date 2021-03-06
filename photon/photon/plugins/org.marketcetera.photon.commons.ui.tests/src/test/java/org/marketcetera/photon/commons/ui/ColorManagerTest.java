package org.marketcetera.photon.commons.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.Collections;

import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.junit.Before;
import org.junit.Test;

/* $License$ */

/**
 * Test {@link ColorManager}. 
 *
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: ColorManagerTest.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
public class ColorManagerTest extends ColorManagerTestBase {

    private ColorDescriptor mDescriptor;
    private ColorManager mFixture;

    @Before
    public void before() {
        mDescriptor = ColorDescriptor.createFrom(new RGB(120, 45, 22));
        mFixture = ColorManager.createFor(Collections.singleton(mDescriptor));
    }

    @Override
    protected void dispose() {
        mFixture.disposeColors();
        
    }

    @Override
    protected Color get() {
        return mFixture.getColor(mDescriptor);
    }

    @Override
    protected void init() {
        mFixture.initColors();
        
    }
    
    @Test
    public void multipleManagers() throws Exception {
        Display d = new Display();
        mFixture.initColors();
        ColorManager fixture = ColorManager.createFor(Collections.singleton(mDescriptor));
        fixture.initColors();
        assertNotNull(mFixture);
        assertNotNull(fixture);
        mFixture.disposeColors();
        assertNull(mFixture);
        assertNotNull(fixture);
        d.dispose();
    }

    private void assertNotNull(final ColorManager fixture) {
        assertThat(fixture.getColor(mDescriptor), not(nullValue(Color.class)));
    }

    private void assertNull(final ColorManager fixture) {
        assertThat(fixture.getColor(mDescriptor), nullValue());
    }

}
