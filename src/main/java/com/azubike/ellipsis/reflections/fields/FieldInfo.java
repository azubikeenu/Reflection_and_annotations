package com.azubike.ellipsis.fields;

import java.lang.reflect.Field;
import java.util.Arrays;

public class FieldInfo {
    public static void main(String[] args) throws Exception {
        final Entity entity = new Entity(10, "id");
        final Class<? extends Entity> aClass = entity.getClass();
        // This gets all public fields of class and parent
        final Field[] fields = aClass.getFields();
        Arrays.stream(fields).map(Field::getName).forEach(System.out::println);
        System.out.println("-----------------------------");
        // This gets all public and private fields
        final Field[] declaredFields = aClass.getDeclaredFields();
        Arrays.stream(declaredFields).map(Field::getName).forEach(System.out::println);
        System.out.println("-----------------------------");
        // Modifying the value of a public class field
        final Field type = aClass.getField("type");
        type.set(entity, "rNumber");
        System.out.println(entity);
        // Modifying the value of a public class field
        final Field val = aClass.getDeclaredField("val");
        val.setAccessible(true);
        val.set(entity ,90);
        System.out.println(entity);

    }
}
