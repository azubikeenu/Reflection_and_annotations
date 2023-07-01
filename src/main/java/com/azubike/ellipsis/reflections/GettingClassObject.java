package com.azubike.ellipsis.reflections;

public class GettingClassObject {
    public static void main(String[] args) throws ClassNotFoundException {
        //////////////////////////////
        // Using the Class.forName(String className)
        // All classes created using this method share the same reference
        ///////////////////////////////
        final Class<?> classOne = Class.forName("java.lang.String");
        final Class<?> classTwo = Class.forName("java.lang.String");
        System.out.println(classOne.equals(classTwo));

        //////////////////////////////
        // Using the ClassName.class
        ///////////////////////////////
        Class<?> integerClassOne = int.class;
        Class<?> integerClassTwo = int.class;
        Class<?> stringClass = String.class;

        //////////////////////////////
        // Using the Object.getClass()
        ///////////////////////////////
        GettingClassObject classObject = new GettingClassObject();
        final Class<? extends GettingClassObject> aClass = classObject.getClass();

        ///////////////////////////////////
        //  GET SUPERCLASS
        ///////////////////////////////////
        final Class<?> superclass = aClass.getSuperclass();

        ///////////////////////////////////
        //  GET INTERFACES
        ///////////////////////////////////
        final Class<?>[] interfaces = aClass.getInterfaces();

        ///////////////////////////////////
        //  GET THE NAME OF THE CLASS
        ///////////////////////////////////
        String className = aClass.getName();
        System.out.println(className);

    }
}
