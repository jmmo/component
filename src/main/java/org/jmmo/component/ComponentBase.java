package org.jmmo.component;

import org.jmmo.Option;
import org.jmmo.observable.Observable;
import org.jmmo.observable.ObservableTransparentContainerBaseCol;

/**
 * User: Tomas
 * Date: 24.05.13
 * Time: 12:00
 */
public abstract class ComponentBase<T> extends ObservableTransparentContainerBaseCol<Observable> implements Component<T> {
    protected Option<ComponentsContainer> containerOption = Option.none();

    @SuppressWarnings("unchecked")
    @Override
    public T componentInterface() {
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I> void forInterface(Class<I> interfaceClass, Handler<I> handler) {
        if (interfaceClass.isInstance(componentInterface())) {
            handler.handle((I) componentInterface());
        }
    }

    protected boolean isAvailable() {
        return containerOption.isDefined();
    }

    protected ComponentsContainer getContainer() {
        return containerOption.get();
    }

    @Override
    public void containerAvailable(ComponentsContainer container) {
        this.containerOption = Option.some(container);
        onContainerAvailable();
    }

    protected void onContainerAvailable() {
        onBecomeAvailable();
    }

    protected void onBecomeAvailable() {
        getContainer().becomeAvailable(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void containerRevoked(ComponentsContainer container) {
        onContainerRevoked();
        containerOption = Option.none();
    }

    protected void onContainerRevoked() {
        if (getContainer().isComponentAvailable(getType())) {
            onBecomeRevoked();
        }
    }

    protected void onBecomeRevoked() {
        getContainer().becomeRevoked(this);
    }

    @Override
    public void componentAvailable(Component<?> component) {
    }

    @Override
    public void componentRevoked(Component<?> component) {
    }
}
