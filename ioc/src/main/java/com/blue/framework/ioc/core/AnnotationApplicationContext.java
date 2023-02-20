package com.blue.framework.ioc.core;

import com.blue.framework.ioc.annotation.Bean;
import com.blue.framework.ioc.annotation.Inject;
import com.blue.framework.ioc.annotation.Value;
import com.blue.framework.ioc.bean.BeanDefinition;
import com.blue.framework.ioc.bean.PropertyArg;
import com.blue.framework.ioc.utils.ClassUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.core
 * @className: AnnotationApplicationContext
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 21:30
 */
public class AnnotationApplicationContext extends AbstractApplicationContext {
    String[] basePackages;
    List<String> list = new ArrayList<>();

    public AnnotationApplicationContext(String basePackage) {
        list.add(basePackage);
        this.basePackages = list.toArray(new String[list.size()]);
    }

    public AnnotationApplicationContext(String[] basePackages) {
        this.basePackages = basePackages;
    }

    public void init() {
        scan();
    }

    private void scan() {
        for (String basePackage : basePackages) {
            URI uri = null;
            try {
                uri = this.getClass().getClassLoader().getResource(basePackage.replace(".", "/")).toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            File dir = new File(uri);
            File[] files = dir.listFiles();
            for (File file : files) {
                if(file.isDirectory()) {
                    scan();
                }
                else {
                    String className = basePackage + "." + file.getName().replace(".class", "");
                    Class clazz = ClassUtils.loadClass(className);
                    if(!isBean(clazz)) continue;
                    BeanDefinition beanDefinition = getBeanDefinition(className);
                    if("".equals(beanDefinition.getName())) {
                        registerBean(beanDefinition.getClassName(), beanDefinition);
                    }
                    else {
                        registerBean(beanDefinition.getName(), beanDefinition);
                    }
                }
            }
        }
    }

    private Boolean isBean(Class clazz) {
        return clazz.isAnnotationPresent(Bean.class);
    }

    private BeanDefinition getBeanDefinition(String className) {
        BeanDefinition bd = new BeanDefinition();
        bd.setClassName(className);
        Class clazz = ClassUtils.loadClass(className);
        Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
        bd.setName(annotation.name());

        Field[] fields = clazz.getDeclaredFields();
        List<PropertyArg> propertyArgs = new ArrayList<>();
        for (Field field : fields) {
            Value value = field.getAnnotation(Value.class);
            if(value != null) {
                PropertyArg arg = new PropertyArg();
                arg.setValue(value.value());
                arg.setName(field.getName());
                arg.setTypeName(field.getType().getTypeName());
                propertyArgs.add(arg);
            }
        }
        bd.setPropertyArgs(propertyArgs);

        return bd;
    }

    @Override
    protected void populateBean(Object bean, BeanDefinition beanDefinition) throws Exception {
        super.populateBean(bean, beanDefinition);

        Class<?> clazz = bean.getClass().getSuperclass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Inject inject = field.getAnnotation(Inject.class);
            if(inject == null) continue;

            Object value = null;
            if("".equals(inject.ref())) {
                value = this.resolveBean(field.getType());
            }
            else {
                value = this.resolveBean(ClassUtils.loadClass(inject.ref()));
            }
            field.setAccessible(true);
            field.set(bean, value);
        }

    }

    private Object resolveBean(Class clazz) throws Exception {
        if(!clazz.isInterface()) {
            Bean bean = (Bean) clazz.getAnnotation(Bean.class);
            if("".equals(bean.name())) {
                return getBean(clazz.getName());
            }
            else {
                return getBean(bean.name());
            }
        }
        else {
            for(Map.Entry<String, Class> entry : classMap.entrySet()) {
                if(clazz.isAssignableFrom(entry.getValue())) {
                    return getBean(entry.getKey());
                }
            }
            throw new Exception("找不到可以进行依赖注入的类");
        }
    }
}
