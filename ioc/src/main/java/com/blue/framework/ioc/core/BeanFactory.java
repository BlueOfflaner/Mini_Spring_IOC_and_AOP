package com.blue.framework.ioc.core;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.core
 * @className: BeanFactory
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 16:35
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
