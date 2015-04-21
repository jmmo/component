package org.jmmo.component;

import org.jmmo.component.event.ComponentAvailableEvent;
import org.jmmo.component.event.ComponentRevokedEvent;
import org.jmmo.Option;
import org.jmmo.observable.Observable;
import org.jmmo.observable.ObservableContainerBaseCol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * User: Tomas
 * Date: 24.05.13
 * Time: 12:12
 *
 * <p>Fires events:</p>
 * {@link org.jmmo.component.event.ComponentAvailableEvent}<br>
 * {@link org.jmmo.component.event.ComponentRevokedEvent}<br>
 */
public class ComponentsContainerImpl extends ObservableContainerBaseCol<Observable> implements ComponentsContainer {
    protected final List<Component<?>> components = new ArrayList<Component<?>>();
    protected final Map<Class<?>, Component<?>> availableComponents = new HashMap<Class<?>, Component<?>>();

    @Override
    public List<Class<?>> getComponents() {
        final List<Class<?>> result = new ArrayList<Class<?>>(components.size());
        for (Component<?> component : components) {
            result.add(component.getType());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> Option<C> getComponentOption(Class<C> componentClass) {
        final Component<?> component = availableComponents.get(componentClass);
        return Option.option(component != null ? (C) component.componentInterface() : null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getComponent(Class<C> componentClass) {
        final Component<?> component = availableComponents.get(componentClass);
        return component != null ? (C) component.componentInterface() : null;
    }

    @Override
    public boolean isComponentAvailable(Class<?> componentClass) {
        return availableComponents.containsKey(componentClass);
    }

    @Override
    public <I> void forInterface(Class<I> interfaceClass, Handler<I> handler) {
        for (Component<?> component : availableComponents.values()) {
            component.forInterface(interfaceClass, handler);
        }
    }

    @Override
    public void addComponent(Component<?> component) {
        for (Component<?> otherComponent : components) {
            if (otherComponent.getType().equals(component.getType())) {
                throw new IllegalArgumentException("Can't add " + component + " with same type as " + otherComponent + " in " + this);
            }
        }

        for (Component<?> availableComponent : availableComponents.values()) {
            component.componentAvailable(availableComponent);
        }

        components.add(component);
        component.containerAvailable(this);

        if (component instanceof Observable) {
            addChildObservable((Observable) component);
        }
    }

    @Override
    public void removeComponent(Component<?> component) {
        boolean removed = false;
        Iterator<Component<?>> componentIterator = components.iterator();
        while (componentIterator.hasNext()) {
            if (componentIterator.next() == component) {
                componentIterator.remove();
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new IllegalArgumentException(component + " was not found in " + this);
        }

        component.containerRevoked(this);

        availableComponents.remove(component.getType());
        for (Component<?> availableComponent : availableComponents.values()) {
            component.componentRevoked(availableComponent);
        }

        if (component instanceof Observable) {
            removeChildObservable((Observable) component);
        }
    }

    @Override
    public void becomeAvailable(Component<?> availableComponent) {
        if (isComponentAvailable(availableComponent.getType())) {
            throw new IllegalArgumentException(availableComponent + " was already available in " + this);
        }

        availableComponents.put(availableComponent.getType(), availableComponent);

        for (Component<?> component : components) {
            if (!(component == availableComponent)) {
                component.componentAvailable(availableComponent);
            }
        }

        fireObservableEvent(new ComponentAvailableEvent(this, availableComponent));
    }

    @Override
    public void becomeRevoked(Component<?> revokedComponent) {
        if (!availableComponents.containsKey(revokedComponent.getType())) {
            throw new IllegalArgumentException(revokedComponent + " was not available in " + this);
        }

        for (Component<?> component : components) {
            if (!(component == revokedComponent)) {
                component.componentRevoked(revokedComponent);
            }
        }

        availableComponents.remove(revokedComponent.getType());

        fireObservableEvent(new ComponentRevokedEvent(this, revokedComponent));
    }

    @Override
    protected void doFireAddedObservableEvent(Observable source, Observable involved) {
        //not fire this event
    }

    @Override
    protected void doFireRemovedObservableEvent(Observable source, Observable involved) {
        //not fire this event
    }
}
