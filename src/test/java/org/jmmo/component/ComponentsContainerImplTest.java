package org.jmmo.component;

import org.jmmo.component.event.ComponentAvailableEvent;
import org.jmmo.component.event.ComponentRevokedEvent;
import org.jmmo.component.items.Consumable;
import org.jmmo.component.items.ConsumableComponent;
import org.jmmo.component.items.Rechargeable;
import org.jmmo.component.items.RechargeableComponent;
import org.jmmo.component.items.Spendable;
import org.jmmo.component.items.SpendableComponent;
import org.hamcrest.Matchers;
import org.jmmo.Option;
import org.jmmo.observable.MockListenerHelper;
import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableEvent;
import org.jmmo.observable.event.ObservableListener;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 18:59
 */
public class ComponentsContainerImplTest {

    private ComponentsContainer container = new ComponentsContainerImpl();

    private RechargeableComponent rechargeableComponent = new RechargeableComponent();
    private ConsumableComponent consumableComponent = new ConsumableComponent();
    private SpendableComponent spendableComponent = new SpendableComponent();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetComponents() throws Exception {
        assertThat(container.getComponents(), empty());

        container.addComponent(rechargeableComponent);
        assertThat(container.getComponents(), Matchers.<Class<?>>containsInAnyOrder(rechargeableComponent.getType()));

        container.addComponent(spendableComponent);
        assertThat(container.getComponents(), Matchers.<Class<?>>containsInAnyOrder(rechargeableComponent.getType(), spendableComponent.getType()));

        container.removeComponent(rechargeableComponent);
        assertThat(container.getComponents(), Matchers.<Class<?>>containsInAnyOrder(spendableComponent.getType()));

        container.removeComponent(spendableComponent);
        assertThat(container.getComponents(), empty());
    }

    @Test
    public void testGetComponentOption() throws Exception {
        assertEquals(Option.none(), container.getComponentOption(Rechargeable.class));

        container.addComponent(rechargeableComponent);
        assertEquals(Option.some(rechargeableComponent.componentInterface()), container.getComponentOption(Rechargeable.class));

        container.addComponent(spendableComponent);
        assertEquals(Option.none(), container.getComponentOption(Spendable.class));

        container.addComponent(consumableComponent);
        assertEquals(Option.some(consumableComponent.componentInterface()), container.getComponentOption(Consumable.class));
        assertEquals(Option.some(spendableComponent.componentInterface()), container.getComponentOption(Spendable.class));

        container.removeComponent(consumableComponent);
        assertEquals(Option.none(), container.getComponentOption(Consumable.class));
        assertEquals(Option.none(), container.getComponentOption(Spendable.class));
        assertEquals(Option.some(rechargeableComponent.componentInterface()), container.getComponentOption(Rechargeable.class));

        container.removeComponent(rechargeableComponent);
        assertEquals(Option.none(), container.getComponentOption(Rechargeable.class));
    }

    @Test
    public void testGetComponent() throws Exception {
        assertEquals(null, container.getComponent(Rechargeable.class));

        container.addComponent(rechargeableComponent);
        assertEquals(rechargeableComponent.componentInterface(), container.getComponent(Rechargeable.class));

        container.addComponent(spendableComponent);
        assertEquals(null, container.getComponent(Spendable.class));

        container.addComponent(consumableComponent);
        assertEquals(consumableComponent.componentInterface(), container.getComponent(Consumable.class));
        assertEquals(spendableComponent.componentInterface(), container.getComponent(Spendable.class));

        container.removeComponent(consumableComponent);
        assertEquals(null, container.getComponent(Consumable.class));
        assertEquals(null, container.getComponent(Spendable.class));
        assertEquals(rechargeableComponent.componentInterface(), container.getComponent(Rechargeable.class));

        container.removeComponent(rechargeableComponent);
        assertEquals(null, container.getComponent(Rechargeable.class));
    }

    @Test
    public void testIsComponentAvailable() throws Exception {
        container.addComponent(spendableComponent);
        assertFalse(container.isComponentAvailable(Spendable.class));
        assertFalse(spendableComponent.isAvailable());

        container.addComponent(consumableComponent);
        assertTrue(container.isComponentAvailable(Consumable.class));
        assertTrue(consumableComponent.isAvailable());
        assertTrue(container.isComponentAvailable(Spendable.class));
        assertTrue(spendableComponent.isAvailable());

        container.removeComponent(spendableComponent);
        assertFalse(container.isComponentAvailable(Spendable.class));
        assertFalse(spendableComponent.isAvailable());

        container.addComponent(spendableComponent);
        assertTrue(container.isComponentAvailable(Spendable.class));
        assertTrue(spendableComponent.isAvailable());

        container.removeComponent(consumableComponent);
        assertFalse(container.isComponentAvailable(Spendable.class));
        assertFalse(spendableComponent.isAvailable());
        assertFalse(container.isComponentAvailable(Consumable.class));
        assertFalse(consumableComponent.isAvailable());

        container.removeComponent(spendableComponent);
        container.addComponent(consumableComponent);
        container.addComponent(spendableComponent);
        assertTrue(container.isComponentAvailable(Consumable.class));
        assertTrue(consumableComponent.isAvailable());
        assertTrue(container.isComponentAvailable(Spendable.class));
        assertTrue(spendableComponent.isAvailable());
    }

    @Test
    public void testForInterfaces() throws Exception {
        final int[] counter = new int[] {0};
        final Handler<Runnable> handler = new Handler<Runnable>() {
            @Override
            public void handle(Runnable runnable) {
                counter[0]++;
            }
        };
        container.forInterface(Runnable.class, handler);
        assertEquals(0, counter[0]);

        container.addComponent(rechargeableComponent);
        container.addComponent(spendableComponent);
        container.forInterface(Runnable.class, handler);
        assertEquals(1, counter[0]);

        counter[0] = 0;
        container.addComponent(consumableComponent);
        container.forInterface(Runnable.class, handler);
        assertEquals(2, counter[0]);

        counter[0] = 0;
        container.removeComponent(consumableComponent);
        container.forInterface(Runnable.class, handler);
        assertEquals(1, counter[0]);

        counter[0] = 0;
        container.removeComponent(rechargeableComponent);
        container.forInterface(Runnable.class, handler);
        assertEquals(0, counter[0]);
    }

    @Test
    public void testFireEvents() throws Exception {
        ObservableListener listener = MockListenerHelper.createMockListener();
        container.addObservableListener(listener);

        container.addComponent(spendableComponent);
        verify(listener, never()).handleObservableEvent(any(ObservableEvent.class), eq(Collections.<Observable>emptyList()));

        container.addComponent(consumableComponent);
        verify(listener).handleObservableEvent(new ComponentAvailableEvent(container, consumableComponent), Collections.<Observable>emptyList());
        verify(listener).handleObservableEvent(new ComponentAvailableEvent(container, spendableComponent), Collections.<Observable>emptyList());

        reset(listener);
        container.removeComponent(consumableComponent);
        verify(listener).handleObservableEvent(new ComponentRevokedEvent(container, consumableComponent), Collections.<Observable>emptyList());
        verify(listener).handleObservableEvent(new ComponentRevokedEvent(container, spendableComponent), Collections.<Observable>emptyList());

        reset(listener);
        container.removeComponent(spendableComponent);
        verify(listener, never()).handleObservableEvent(any(ObservableEvent.class), eq(Collections.<Observable>emptyList()));
    }
}
