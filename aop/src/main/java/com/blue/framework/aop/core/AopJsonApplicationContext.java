package com.blue.framework.aop.core;

import com.blue.framework.aop.adaptor.AdvisorAdapterRegistry;
import com.blue.framework.aop.adaptor.DefaultAdvisorAdapterRegistry;
import com.blue.framework.aop.advisor.Advice;
import com.blue.framework.aop.advisor.Advisor;
import com.blue.framework.aop.advisor.AdvisorSupport;
import com.blue.framework.aop.advisor.TargetSource;
import com.blue.framework.aop.bean.AopBeanDefinition;
import com.blue.framework.aop.interceptor.AopMethodInterceptor;
import com.blue.framework.ioc.bean.BeanDefinition;
import com.blue.framework.ioc.core.JsonApplicationContext;
import com.blue.framework.ioc.utils.ClassUtils;
import com.blue.framework.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.core
 * @className: AopJsonApplicationContext
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 14:57
 */
public class AopJsonApplicationContext extends JsonApplicationContext {

    private static final ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String,Object> aopBeanMap = new ConcurrentHashMap<>();

    public AopJsonApplicationContext(String fileName) {
        super(fileName);
    }

    public AopJsonApplicationContext(String[] fileName) {
        super(fileName);
    }

    @Override
    protected void loadFile() {
        for (String fileName : fileNames) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<AopBeanDefinition>>() {});
            if(beanDefinitions != null) {
                for (AopBeanDefinition beanDefinition : beanDefinitions) {
                    Class clazz = ClassUtils.loadClass(beanDefinition.getClassName());
                    if(clazz == ProxyBeanFactory.class) {
                        registerBean(beanDefinition.getName(),beanDefinition);
                    }
                    else {
                        registerBean(beanDefinition.getName(), (BeanDefinition) beanDefinition);
                    }
                }
            }
        }
    }


    /*
        重写 AbstractApplicationContext 中的方法
     */

    protected void registerBean(String name, AopBeanDefinition aopBeanDefinition){
        aopBeanDefinitionMap.put(name,aopBeanDefinition);
    }

    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean = aopBeanMap.get(name);
        if(aopBean != null) {
            return aopBean;
        }

        if(aopBeanDefinitionMap.containsKey(name)) {
            AopBeanDefinition aopBeanDefinition = aopBeanDefinitionMap.get(name);
            AdvisorSupport advisorSupport = this.getAdvisorSupport(aopBeanDefinition);
            aopBean = createAopBean(advisorSupport);
            aopBeanMap.put(name, aopBean);
            return aopBean;
        }

        return super.getBean(name);
    }

    private Object createAopBean(AdvisorSupport advisorSupport) {
        return new CglibAopProxy(advisorSupport).getProxy();
    }

    private AdvisorSupport getAdvisorSupport(AopBeanDefinition aopBeanDefinition) throws Exception {
        AdvisorAdapterRegistry adapterRegistry = DefaultAdvisorAdapterRegistry.getInstance();

        AdvisorSupport advisorSupport = new AdvisorSupport();
        List<String> interceptorNameList = aopBeanDefinition.getInterceptorNames();

        for (String interceptorName : interceptorNameList) {
            Advice advice = (Advice) getBean(interceptorName);
            Advisor advisor = new Advisor();
            advisor.setAdvice(advice);

            List<AopMethodInterceptor> interceptors = adapterRegistry.getInterceptors(advisor);
            advisorSupport.addAopMethodInterceptors(interceptors);
        }

        TargetSource targetSource = new TargetSource();
        Object object = getBean(aopBeanDefinition.getTargetName());
        targetSource.setTargetObject(object);
        targetSource.setTargetClass(object.getClass());
        advisorSupport.setTargetSource(targetSource);

        return advisorSupport;
    }
}
