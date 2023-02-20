package com.blue.framework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.annotation
 * @className: ComponentScan
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 21:58
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    String[] basePackage() default "";
}
