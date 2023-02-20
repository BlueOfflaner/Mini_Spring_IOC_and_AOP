package com.blue.framework.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.utils
 * @className: BeanUtils
 * @author: BlueOfflaner
 * @description: 负责对象的实例化
 * @date: 2023/2/15 16:23
 */

public class BeanUtils {

    //使用cglib反射实例化对象
    public static <T> T getInstance(Class<T> clazz, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        //表示调用代理类时直接使用被代理类代码
        enhancer.setCallback(NoOp.INSTANCE);
        if(constructor == null) {
            return (T) enhancer.create();
        }
        return (T) enhancer.create(constructor.getParameterTypes(), args);
    }
}
