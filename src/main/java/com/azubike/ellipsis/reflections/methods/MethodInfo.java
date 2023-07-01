package com.azubike.ellipsis.reflections.methods;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Entity e = new Entity(10, "id");
        final Class<? extends Entity> aClass = e.getClass();
        final Method[] methods = aClass.getMethods();
        Arrays.stream(methods).map(Method::getName).forEach(System.out::println);

        Arrays.stream(methods).forEach(method -> {
            final Class<?> returnType = method.getReturnType();
            System.out.println(returnType.getName());
        });
        System.out.println("-----------------------------");
        final Method[] declaredMethods = aClass.getDeclaredMethods() ;
        Arrays.stream(declaredMethods).map(Method::getName).forEach(System.out::println);
        ////////////////////////////////////////
        // Get details of a particular method
        ////////////////////////////////////////
        //final Method m2 = aClass.getMethod("test", String.class, int.class);
        final Method m1 = aClass.getMethod("setVal", int.class);
        m1.invoke(e,15);

    }

}
