package com.github.natholdallas.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public record EClass<T>(Class<T> clazz) {

    @Contract("_ -> new")
    public @NotNull EConstructor<T> constructor(Class<?> parameterType) {
        try {
            return new EConstructor<>(clazz.getDeclaredConstructor(parameterType));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public @NotNull List<Constructor<T>> constructors() {
        List<Constructor<T>> constructors = new ArrayList<>();
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            constructors.add((Constructor<T>) constructor);
        }
        return constructors;
    }

    public @NotNull Method method(String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull List<EMethod> methods() {
        List<EMethod> methods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            methods.add(new EMethod(method));
        }
        return methods;
    }

    @Contract("_ -> new")
    public @NotNull EField field(String name) {
        try {
            return new EField(clazz.getDeclaredField(name));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull List<EField> fields() {
        List<EField> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.add(new EField(field));
        }
        return fields;
    }

}
