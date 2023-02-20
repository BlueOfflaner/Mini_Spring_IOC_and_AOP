package com.blue.aop.advice;

import com.blue.aop.utils.ThreadLocalUtils;
import com.blue.framework.aop.advisor.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @projectName: framework
 * @package: com.blue.aop.advice
 * @className: AfterAdvice
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 15:37
 */
public class EndTiming implements AfterReturningAdvice {
    @Override
    public Object after(Object returnVal, Method method, Object[] args, Object target) {
        long endTime = System.currentTimeMillis();
        long startTime = ThreadLocalUtils.get();
        ThreadLocalUtils.remove();
        System.out.println("方法耗时：" + (endTime - startTime) + "ms");
        return returnVal;
    }
}
