package com.blue.framework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.annotation
 * @className: Inject
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 21:27
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    boolean required() default true;

    String ref() default "";
}
