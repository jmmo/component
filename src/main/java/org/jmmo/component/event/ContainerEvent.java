package org.jmmo.component.event;

import org.jmmo.component.Component;
import org.jmmo.component.ComponentsContainer;
import org.jmmo.observable.event.ObservableEvent;

/**
 * User: Tomas
 * Date: 31.05.13
 * Time: 20:50
 */
public class ContainerEvent extends ObservableEvent {
    private final Component component;

    public ContainerEvent(ComponentsContainer container, Component component) {
        super(container);
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public ComponentsContainer getSource() {
        return (ComponentsContainer) super.getSource();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerEvent that = (ContainerEvent) o;

        if (!component.equals(that.component)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return component.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "component=" + component +
                '}';
    }
}
