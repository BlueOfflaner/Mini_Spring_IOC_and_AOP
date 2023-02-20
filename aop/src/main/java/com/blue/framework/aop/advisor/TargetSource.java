package com.blue.framework.aop.advisor;

import lombok.Data;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.advisor
 * @className: Target
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:35
 */
@Data
public class TargetSource {

    private Class<?> targetClass;

    private Object targetObject;

}
