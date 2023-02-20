package com.blue.framework.ioc.utils;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.utils
 * @className: ClassUtils
 * @author: BlueOfflaner
 * @description: 进行Java类的加载
 * @date: 2023/2/15 16:19
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    //通过 className 获取 class
    public static Class loadClass(String className) {
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
