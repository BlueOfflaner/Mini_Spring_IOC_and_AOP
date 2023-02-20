package com.blue.framework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.annotation
 * @className: Value
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/16 20:46
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value();
}
