package org.jmmo.component;

import org.jmmo.Option;
import org.jmmo.observable.Observable;

import java.util.List;

/**
 * User: Tomas
 * Date: 16.03.13
 * Time: 10:54
 */
public interface ComponentsContainer extends Observable {

    List<Class<?>> getComponents();

    <C> Option<C> getComponentOption(Class<C> componentClass);

    <C> C getComponent(Class<C> componentClass);

    boolean isComponentAvailable(Class<?> componentClass);

    <I> void forInterface(Class<I> interfaceClass, Handler<I> handler);

    void addComponent(Component<?> component);

    void removeComponent(Component<?> component);

    void becomeAvailable(Component<?> availableComponent);

    void becomeRevoked(Component<?> revokedComponent);
}
