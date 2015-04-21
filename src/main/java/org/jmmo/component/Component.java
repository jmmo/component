package org.jmmo.component;

/**
 * User: Tomas
 * Date: 31.03.13
 * Time: 13:46
 */
public interface Component<T> {

    Class<T> getType();

    T componentInterface();

    <I> void forInterface(Class<I> interfaceClass, Handler<I> handler);

    void containerAvailable(ComponentsContainer container);

    void containerRevoked(ComponentsContainer container);

    void componentAvailable(Component<?> component);

    void componentRevoked(Component<?> component);
}
