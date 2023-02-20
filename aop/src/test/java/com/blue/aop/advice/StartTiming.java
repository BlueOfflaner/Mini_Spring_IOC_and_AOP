package com.blue.aop.advice;

import com.blue.aop.utils.ThreadLocalUtils;
import com.blue.framework.aop.advisor.BeforeMethodAdvice;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.aop.advice
 * @className: BeforeAdvice
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 15:37
 */
public class StartTiming implements BeforeMethodAdvice {
    @Override
    public Object before(Method method, Object[] args, Object target) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始计时");
        ThreadLocalUtils.set(startTime);
        return null;
    }
}
