package com.azubike.ellipsis.annotations.general_purpose;

import java.util.ArrayList;

class A {
    public void m1() {
        System.out.println("MI Parent implementation");
    }

    public void m2() {
        System.out.println("M2 Parent implementation");
    }

}

class B extends A {
    @Override
    public void m1() {
        System.out.println("MI Child implementation");
    }

    @Deprecated(since = "2")
    public void deprecatedMethod() {
        System.out.println("Dont use this method");
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    void method();
}

public class Test {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        int a = 10;
        //@SuppressWarnings("all")
        @SuppressWarnings({"rawtypes", "unused"})
        ArrayList arrayList = new ArrayList();
        MyFunctionalInterface myFunctionalInterfaceImpl = () ->  System.out.println("Hello");
        myFunctionalInterfaceImpl.method();

    }
}
