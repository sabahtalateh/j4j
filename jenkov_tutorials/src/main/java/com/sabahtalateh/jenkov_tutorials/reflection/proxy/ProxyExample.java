package com.sabahtalateh.jenkov_tutorials.reflection.proxy;

import java.lang.reflect.Proxy;

/**
 * ProxyExample.
 */
public class ProxyExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        FooImpl foo = new FooImpl();

        FooInterface fooProxy = (FooInterface) Proxy.newProxyInstance(foo.getClass().getClassLoader(),
                foo.getClass().getInterfaces(),
                new DebugProxy(foo));

        System.out.println(fooProxy.addExclamation("123"));

    }
}
