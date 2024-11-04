package com.edu.ifpb.caprin.model.utils;

import java.util.Objects;

public class ObjectUtils {
    private ObjectUtils() {}

    public static Boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
    public static Boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }
}