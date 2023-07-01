package com.azubike.ellipsis.reflections_and_annotations;

import com.azubike.ellipsis.annotations.custom.MostUsed;
import com.azubike.ellipsis.annotations.custom.Utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReadingAnnotations {
    public static void main(String[] args) throws Exception {
        final Class<?> utilClass = Class.forName("com.azubike.ellipsis.annotations.custom.Utility");
        final Constructor<?> constructor = utilClass.getConstructor();
        final Utility utility = (Utility) constructor.newInstance();
        final Method[] methods = utilClass.getDeclaredMethods();

        List<Method> annotatedMethods = Arrays.stream(methods).
                filter(method -> method.isAnnotationPresent(MostUsed.class)).
                toList();

        annotatedMethods.forEach(method -> {
            try {
                final MostUsed annotation = method.getAnnotation(MostUsed.class);
                String value = annotation.value();
                method.invoke(utility, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
