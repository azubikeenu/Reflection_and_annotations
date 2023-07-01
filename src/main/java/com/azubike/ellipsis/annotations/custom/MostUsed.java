package com.azubike.ellipsis.annotations.custom;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MostUsed {
    String value() default "Java";
}
