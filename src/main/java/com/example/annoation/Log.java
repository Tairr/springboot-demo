package com.example.annoation;

import java.lang.annotation.*;

/**
 * Created by tangjinhui on 2017/10/19.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    String name() default "";
}
