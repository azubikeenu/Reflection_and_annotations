package com.azubike.ellipsis.reflections;


import java.lang.reflect.Constructor;

class MyClass {
    private MyClass() {
        System.out.println("Calling Constructor of MyClass");
    }
    public void showMessage() {
        System.out.println("Hello guy what's up");
    }


}

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Class<?> myClass = Class.forName("com.azubike.ellipsis.reflections.MyClass");
        Constructor<?> classConstructor = myClass.getDeclaredConstructor();
        classConstructor.setAccessible(true);
        final MyClass newInstance = (MyClass) classConstructor.newInstance();
        newInstance.showMessage();

    }
}
