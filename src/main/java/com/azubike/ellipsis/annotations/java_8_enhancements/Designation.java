package com.azubike.ellipsis.annotations.java_8_enhancements;


import java.lang.annotation.*;

@Repeatable( value = Designations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Designation {
    String value() ;
}
