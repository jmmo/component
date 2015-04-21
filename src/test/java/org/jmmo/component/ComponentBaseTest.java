package org.jmmo.component;

import org.jmmo.component.items.Rechargeable;
import org.jmmo.component.items.RechargeableComponent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 19:00
 */
public class ComponentBaseTest {

    private ComponentBase<Rechargeable> component = new RechargeableComponent();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(Rechargeable.class, component.getType());
    }

    @Test
    public void testComponentInterface() throws Exception {
        assertTrue(component.componentInterface() instanceof Rechargeable);
    }

    @Test
    public void testForInterfaces() throws Exception {
        final boolean[] flag = new boolean[] {false};
        component.forInterface(Runnable.class, new Handler<Runnable>() {
            @Override
            public void handle(Runnable runnable) {
                flag[0] = true;
            }
        });

        assertTrue(flag[0]);
    }
}
