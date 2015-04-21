package org.jmmo.component.items;

import org.jmmo.component.ComponentBase;

/**
 * User: Tomas
 * Date: 30.05.13
 * Time: 21:28
 */
public class RechargeableComponent extends ComponentBase<Rechargeable> implements Rechargeable {

    @Override
    public Class<Rechargeable> getType() {
        return Rechargeable.class;
    }

    @Override
    public int getQuantityInCartridge() {
        return 1;
    }

    @Override
    public void run() {
        //do nothing
    }
}
