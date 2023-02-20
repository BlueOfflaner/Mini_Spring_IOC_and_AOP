package com.blue.framework.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.utils
 * @className: ReflectionUtils
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:01
 */
public class ReflectionUtils {

    public static Object invokeMethodUseReflection(Object target, Method method, Object[] args){

        method.setAccessible(true);
        try {
            return method.invoke(target,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}