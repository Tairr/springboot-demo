package com.example.annoation;

import java.lang.annotation.*;

/**
 * Created by tangjinhui on 2017/10/19.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestLimit {

    int count() default Integer.MAX_VALUE;
    int limitTime() default 600000;
}
