package com.azubike.ellipsis.constructors;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

public class GettingConstructors {
    public static void main(String[] args) throws Exception {
        final Class<?> aClass = Class.forName("com.azubike.ellipsis.constructors.Entity");
        final Constructor<?>[] constructors = aClass.getConstructors();
        // This returns only the public constructors
        Arrays.stream(constructors).forEach(System.out::println);
        // getDeclaredConstructors() returns public and private constructors
        System.out.println("=================================");
        List.of(aClass.getDeclaredConstructors()).forEach(System.out::println);
        System.out.println("=================================");
        final Constructor<?> publicConstructor = aClass.getConstructor(int.class, String.class);
        final Entity student = (Entity) publicConstructor.newInstance(10, "StudentId");
        System.out.println(student);
        final Constructor<?> privateConstructor = aClass.getDeclaredConstructor();
        privateConstructor.setAccessible(true);
        final Entity privateObject = (Entity) privateConstructor.newInstance();
        System.out.println(privateObject);

    }
}
