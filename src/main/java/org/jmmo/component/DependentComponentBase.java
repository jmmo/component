package org.jmmo.component;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 21:13
 */
public abstract class DependentComponentBase<T> extends ComponentBase<T> {
    protected boolean wasAvailable;

    @Override
    public boolean isAvailable() {
        boolean available = false;
        for (ComponentsContainer container : containerOption) {
            available = true;
            for (Class clazz : require()) {
                if (!container.isComponentAvailable(clazz)) {
                    available = false;
                    break;
                }
            }
        }
        return available;
    }

    @Override
    protected void onContainerAvailable() {
        if (isAvailable()) {
            onBecomeAvailable();
        }
    }

    @Override
    public void componentAvailable(Component<?> component) {
        if (!wasAvailable && containerOption.isDefined()) {
            for (Class clazz : require()) {
                if (component.getType().equals(clazz)) {
                    if (isAvailable()) {
                        onBecomeAvailable();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void componentRevoked(Component<?> component) {
        if (wasAvailable && containerOption.isDefined()) {
            for (Class clazz : require()) {
                if (component.getType().equals(clazz)) {
                    onBecomeRevoked();
                    break;
                }
            }
        }
    }

    @Override
    protected void onBecomeAvailable() {
        wasAvailable = true;
        super.onBecomeAvailable();
    }

    @Override
    protected void onBecomeRevoked() {
        wasAvailable = false;
        super.onBecomeRevoked();
    }

    protected abstract Class[] require();
}
