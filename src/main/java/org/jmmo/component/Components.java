package org.jmmo.component;

/**
 * User: Tomas
 * Date: 16.03.13
 * Time: 20:00
 */
public class Components {

    public static <C, T1 extends C, T2 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2, Handler2<T1, T2> handler2) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                handler2.components(component1, component2);
            }
        }
    }
    public interface Handler2<T1, T2> {
        public void components(T1 t1, T2 t2);
    }

    public static <C, T1 extends C, T2 extends C, T3 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2,
                                                                                                     Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                for (T3 component3 : container.getComponentOption(class3)) {
                    handler3.components(component1, component2, component3);
                }
            }
        }
    }
    public interface Handler3<T1, T2, T3> {
        public void components(T1 t1, T2 t2, T3 t3);
    }

    public static <C, T1 extends C, T2 extends C, T3 extends C, T4 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2,
                                                                            Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                for (T3 component3 : container.getComponentOption(class3)) {
                    for (T4 component4 : container.getComponentOption(class4)) {
                        handler4.components(component1, component2, component3, component4);
                    }
                }
            }
        }
    }
    public interface Handler4<T1, T2, T3, T4> {
        public void components(T1 t1, T2 t2, T3 t3, T4 t4);
    }

    public static <C, T1 extends C, T2 extends C, T3 extends C, T4 extends C, T5 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2,
                                                            Class<T3> class3, Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                for (T3 component3 : container.getComponentOption(class3)) {
                    for (T4 component4 : container.getComponentOption(class4)) {
                        for (T5 component5 : container.getComponentOption(class5)) {
                            handler5.components(component1, component2, component3, component4, component5);
                        }
                    }
                }
            }
        }
    }
    public interface Handler5<T1, T2, T3, T4, T5> {
        public void components(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
    }

    public static <C, T1 extends C, T2 extends C, T3 extends C, T4 extends C, T5 extends C, T6 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2,
                                    Class<T3> class3, Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                for (T3 component3 : container.getComponentOption(class3)) {
                    for (T4 component4 : container.getComponentOption(class4)) {
                        for (T5 component5 : container.getComponentOption(class5)) {
                            for (T6 component6 : container.getComponentOption(class6)) {
                                handler6.components(component1, component2, component3, component4, component5, component6);
                            }
                        }
                    }
                }
            }
        }
    }
    public interface Handler6<T1, T2, T3, T4, T5, T6> {
        public void components(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
    }

    public static <C, T1 extends C, T2 extends C, T3 extends C, T4 extends C, T5 extends C, T6 extends C, T7 extends C> void collect(ComponentsContainer container, Class<T1> class1, Class<T2> class2,
                Class<T3> class3, Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        for (T1 component1 : container.getComponentOption(class1)) {
            for (T2 component2 : container.getComponentOption(class2)) {
                for (T3 component3 : container.getComponentOption(class3)) {
                    for (T4 component4 : container.getComponentOption(class4)) {
                        for (T5 component5 : container.getComponentOption(class5)) {
                            for (T6 component6 : container.getComponentOption(class6)) {
                                for (T7 component7 : container.getComponentOption(class7)) {
                                    handler7.components(component1, component2, component3, component4, component5, component6, component7);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public interface Handler7<T1, T2, T3, T4, T5, T6, T7> {
        public void components(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
    }
}
