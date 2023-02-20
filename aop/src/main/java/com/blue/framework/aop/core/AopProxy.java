package com.blue.framework.aop.core;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.core
 * @className: AopProxy
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 14:21
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);

}
