package com.blue.framework.ioc.utils;

import com.blue.framework.ioc.bean.BeanDefinition;

import java.lang.reflect.Field;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.utils
 * @className: ReflectionUtils
 * @author: BlueOfflaner
 * @description: 属性注入
 * @date: 2023/2/15 16:33
 */
public class ReflectionUtils {
    //使用反射进行属性注入
    public static void inject(Field field, Object o, Object value) throws IllegalAccessException {
        if(field == null) return;
        field.setAccessible(true);
        if(field.getType() != String.class && value instanceof String) {
            value = Integer.parseInt((String) value);
        }
        field.set(o, value);
    }

    //public static void inject(BeanDefinition bd, String propertyName, Object injectValue) throws Exception {
    //    Class beanClass = Class.forName(bd.getClassName());
    //    Class injectValueClass = injectValue.getClass();
    //    beanClass.getMethod("set", injectValueClass);
    //}
}
