package com.blue.aop.advice;

import com.blue.framework.aop.advisor.BeforeMethodAdvice;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.aop.advice
 * @className: StartMethodPrint
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 15:58
 */
public class StartMethodPrint implements BeforeMethodAdvice {

    @Override
    public Object before(Method method, Object[] args, Object target) {
        System.out.println("Method " + method.getName() + " will start");
        return null;
    }
}
