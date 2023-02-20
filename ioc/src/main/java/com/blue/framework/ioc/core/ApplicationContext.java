package com.blue.framework.ioc.core;

/**
 * @projectName: framework
 * @package: com.blue.framework.ioc.core
 * @className: ApplicationContext
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 16:11
 */
public interface ApplicationContext extends BeanFactory {

    default void init() {

    };

}
