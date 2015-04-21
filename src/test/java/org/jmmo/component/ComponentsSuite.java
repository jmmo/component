package org.jmmo.component;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * User: Tomas
 * Date: 31.05.13
 * Time: 12:44
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComponentBaseTest.class,
        ComponentsContainerImplTest.class
})
public class ComponentsSuite {
}
