package com.blue.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.advisor
 * @className: BeforeMethodAdvice
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:33
 */
public interface BeforeMethodAdvice extends Advice {

    Object before(Method method, Object[] args, Object target);

}
