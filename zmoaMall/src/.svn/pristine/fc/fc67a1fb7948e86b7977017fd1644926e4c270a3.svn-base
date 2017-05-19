package com.zm.mall.common.security;

import java.lang.annotation.*;

/**
 * Created by liyanshuai on 2016/11/8.
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Convertable {
    String valueMethod() default "value";

    String ofMethod() default "of";
}