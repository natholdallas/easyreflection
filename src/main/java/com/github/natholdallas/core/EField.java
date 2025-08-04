package com.github.natholdallas.core;

import com.github.natholdallas.exceptions.UnAccessibleException;

import java.lang.reflect.Field;

public record EField(Field field) {

    public EField {
        try {
            field.setAccessible(true);
        } catch (Exception e) {
            throw new UnAccessibleException(e);
        }
    }

    public void set(Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
