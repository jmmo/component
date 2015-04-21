package org.jmmo.component.items;

import org.jmmo.component.DependentComponentBase;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 19:14
 */
public class SpendableComponent extends DependentComponentBase<Spendable> implements Spendable {
    private float charge = 1f;

    @Override
    public Class<Spendable> getType() {
        return Spendable.class;
    }

    @Override
    public float getCharge() {
        return charge;
    }

    @Override
    public void setCharge(float charge) {
        this.charge = charge;
        if (charge <= 0f) {
            final Consumable consumable = containerOption.get().getComponent(Consumable.class);
            int count = consumable.getCount();
            if (count > 0) {
                consumable.setCount(count - 1);
            }
        }
    }

    @Override
    protected Class[] require() {
        return new Class[] {Consumable.class};
    }

    @Override
    public void run() {
        //do nothing
    }
}
