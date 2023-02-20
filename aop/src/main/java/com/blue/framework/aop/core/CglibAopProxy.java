package com.blue.framework.aop.core;

import com.blue.framework.aop.advisor.AdvisorSupport;
import com.blue.framework.ioc.utils.ClassUtils;
import lombok.Data;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.core
 * @className: CglibAopProxy
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 14:22
 */
@Data
public class CglibAopProxy implements AopProxy {

    private AdvisorSupport advisorSupport;
    private Object[] constructorArgs;
    private Class[] constructorArgTypes;

    public CglibAopProxy(AdvisorSupport advisorSupport) {
        this.advisorSupport = advisorSupport;
    }

    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    //和IOC中 BeanUtils 的 getInstance类似
    public Object getProxy(ClassLoader classLoader) {
        Class<?> targetClass = advisorSupport.getTargetSource().getTargetClass();
        if(classLoader == null) {
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        Enhancer enhancer = new Enhancer();
        //targetSource 本身就是一个被代理生成的类, 其 superClass 才是原本的 target
        enhancer.setSuperclass(targetClass.getSuperclass());
        enhancer.setCallback(getCallback(advisorSupport));
        enhancer.setClassLoader(classLoader);
        if(constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        }
        return enhancer.create();
    }

    private Callback getCallback(AdvisorSupport advisorSupport) {
        return new DynamicAdvisedInterceptor(advisorSupport.getInterceptorList(), advisorSupport.getTargetSource());
    }
}
