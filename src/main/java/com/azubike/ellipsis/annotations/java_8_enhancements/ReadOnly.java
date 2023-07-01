package com.azubike.ellipsis.annotations.java_8_enhancements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE , ElementType.TYPE_PARAMETER})
public @interface ReadOnly {
}
