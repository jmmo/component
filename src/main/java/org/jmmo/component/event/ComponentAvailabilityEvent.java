package org.jmmo.component.event;

import org.jmmo.component.Component;
import org.jmmo.component.ComponentsContainer;

/**
 * User: Tomas
 * Date: 31.05.13
 * Time: 20:54
 */
public abstract class ComponentAvailabilityEvent extends ContainerEvent {

    protected ComponentAvailabilityEvent(ComponentsContainer container, Component component) {
        super(container, component);
    }
}
