package com.warrr.zipflex.global.support;

public class TypeCaster {

    @SuppressWarnings("unchecked")
    public static <T> T castMessage(Object message) {
        try {
            return (T) message;
        } catch (ClassCastException e) {
            return null;
        }
    }

}
