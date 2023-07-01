package com.azubike.ellipsis.annotations.java_8_enhancements;

public class Enhancements {
    public static void main(String[] args) {
        Box<String> box =   new @ReadOnly  Box<>(1 , "container");
        Box<String>.NestedBox nestedBox = box.new  @ReadOnly  NestedBox(10 , "cylinder");
    }
}
