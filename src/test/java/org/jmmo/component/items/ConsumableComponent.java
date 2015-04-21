package org.jmmo.component.items;


import org.jmmo.component.ComponentBase;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 19:11
 */
public class ConsumableComponent extends ComponentBase<Consumable> implements Consumable {
    private int count;

    @Override
    public Class<Consumable> getType() {
        return Consumable.class;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getStackSize() {
        return 10;
    }
}
