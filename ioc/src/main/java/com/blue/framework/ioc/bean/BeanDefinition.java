package com.blue.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.bean
 * @className: BeanDefinition
 * @author: BlueOfflaner
 * @description: 定义框架的数据结构
 * @date: 2023/2/15 15:48
 */
@Data
@ToString
public class BeanDefinition {
    //对象名称
    String name;

    //对象类名
    String className;

    //实现接口的名称
    List<String> interfaceNames;

    //构造函数中的参数
    List<ConstructorArg> constructorArgs;

    //需要注入的参数
    List<PropertyArg> propertyArgs;
}
