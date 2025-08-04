package com.github.natholdallas.core;

import com.github.natholdallas.exceptions.UnAccessibleException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record EMethod(Method method) {

    public EMethod {
        try {
            method.setAccessible(true);
        } catch (Exception e) {
            throw new UnAccessibleException(e);
        }
    }

    public void invoke(Object instance, Object... args) {
        try {
            method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
