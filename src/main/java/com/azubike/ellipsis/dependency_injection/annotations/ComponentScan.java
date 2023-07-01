package com.azubike.ellipsis.dependency_injection.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(ComponentScans.class)
public @interface ComponentScan {
    String value();
}
