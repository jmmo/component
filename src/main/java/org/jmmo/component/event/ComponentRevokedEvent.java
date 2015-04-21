package org.jmmo.component.event;

import org.jmmo.component.Component;
import org.jmmo.component.ComponentsContainer;

/**
 * User: Tomas
 * Date: 31.05.13
 * Time: 20:58
 */
public class ComponentRevokedEvent extends ComponentAvailabilityEvent {

    public ComponentRevokedEvent(ComponentsContainer container, Component component) {
        super(container, component);
    }
}
