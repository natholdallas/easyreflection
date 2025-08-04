package com.github.natholdallas.core;

import com.github.natholdallas.exceptions.UnAccessibleException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public record EConstructor<T>(Constructor<T> constructor) {

    public EConstructor {
        try {
            constructor.setAccessible(true);
        } catch (Exception e) {
            throw new UnAccessibleException(e);
        }
    }

    public T newInstance(Object... args) {
        try {
            return constructor.getDeclaringClass().cast(constructor.newInstance(args));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <A> A newInstance(@NotNull Class<A> clazz, Object... args) {
        try {
            return clazz.cast(constructor.newInstance(args));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
