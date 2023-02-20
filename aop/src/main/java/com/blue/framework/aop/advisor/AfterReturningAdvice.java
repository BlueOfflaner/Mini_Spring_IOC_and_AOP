package com.blue.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.advisor
 * @className: AfterMethodAdvice
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:29
 */
public interface AfterReturningAdvice extends Advice {

    Object after(Object returnVal, Method method, Object[] args, Object target);

}
