package com.blue.framework.aop.bean;

import com.blue.framework.ioc.bean.BeanDefinition;
import lombok.Data;

import java.util.List;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.bean
 * @className: AopBeanDefinition
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:41
 */
@Data
public class AopBeanDefinition extends BeanDefinition {

    private String targetName;

    private List<String> interceptorNames;
}
