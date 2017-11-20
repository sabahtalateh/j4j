package com.sabahtalateh.jenkov_tutorials.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * DebugProxy.
 */
public class DebugProxy implements InvocationHandler {

    Object object;

    /**
     * @param object object.
     */
    public DebugProxy(Object object) {
        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;

        try {
            System.out.println("Before method: " + method.getName());
            result = method.invoke(object, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println("After method: " + method.getName());
        }

        return result;
    }
}
