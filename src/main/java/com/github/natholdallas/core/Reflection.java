package com.github.natholdallas.core;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public class Reflection {

    public static Object $invoke(@NotNull Method method, Object instance, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> @NotNull T $new(@NotNull Constructor<T> constructor, Object... initargs) {
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(initargs);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void $set(@NotNull Field field, Object instance, Object value) {
        try {
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runCatch(Runnable runnable) throws RuntimeException {
        try {
            runnable.run();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void runCatch(Runnable runnable, Function<Throwable, RuntimeException> throwFunc) throws RuntimeException {
        try {
            runnable.run();
        } catch (Throwable e) {
            throw throwFunc.apply(e);
        }
    }

    private Reflection() {
    }

}
