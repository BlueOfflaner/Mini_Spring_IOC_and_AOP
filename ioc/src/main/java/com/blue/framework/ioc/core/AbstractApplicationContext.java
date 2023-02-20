package com.blue.framework.ioc.core;

import com.blue.framework.ioc.bean.BeanDefinition;
import com.blue.framework.ioc.bean.ConstructorArg;
import com.blue.framework.ioc.bean.PropertyArg;
import com.blue.framework.ioc.utils.BeanUtils;
import com.blue.framework.ioc.utils.ClassUtils;
import com.blue.framework.ioc.utils.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.core
 * @className: AbstractApplicationContext
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 16:51
 */
public class AbstractApplicationContext implements ApplicationContext {

    //用于解决循环依赖问题, 存储未加载完成的bean
    private static final Map<String, Object> earlySingletonObjects = new HashMap<>();

    //存储实例化的bean
    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    protected static final ConcurrentHashMap<String, Class> classMap = new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());

    protected void registerBean(String name, BeanDefinition bd) {
        beanDefinitionMap.put(name, bd);
        beanNameSet.add(name);
        classMap.put(name, ClassUtils.loadClass(bd.getClassName()));
    }

    @Override
    public Object getBean(String name) throws Exception {
        //查找bean是否已经实例化过
        Object bean = beanMap.get(name);
        if(bean != null) {
            return bean;
        }

        Object earlyBean = earlySingletonObjects.get(name);
        if(earlyBean != null) {
            //发生了循环依赖,返回为加载完成的bean
            System.out.println();
            System.out.println("发生循环依赖!!");
            return earlyBean;
        }

        //如果 Bean 未实例化, 调用 creatBean 创建对象
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        bean = createBean(beanDefinition);

        if(bean != null) {
            //先加入早期单例中防止循环依赖
            earlySingletonObjects.put(name, bean);

            //属性注入
            populateBean(bean, beanDefinition);

            //加入 beanMap
            beanMap.put(name, bean);

            //从早期单例中移除
            earlySingletonObjects.remove(name);
        }

        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(className);
        if(clazz == null) {
            throw new Exception("Could not find class by this className " + className);
        }

        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        Object bean = null;

        if(constructorArgs != null && !constructorArgs.isEmpty()) {
            List<Object> args = new ArrayList<>();

            for(ConstructorArg constructorArg : constructorArgs) {
                if(constructorArg.getValue() != null) {
                    args.add(constructorArg.getValue());
                }
                else {
                    args.add(getBean(constructorArg.getRef()));
                }
            }

            //多构造函数时的处理
            Constructor[] constructors = clazz.getConstructors();
            for(Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                if(args.size() != parameterTypes.length) continue;

                boolean flag = true;
                for(int i = 0; i < parameterTypes.length; i++) {
                    if(args.get(i).getClass() != parameterTypes[i]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    bean = BeanUtils.getInstance(clazz, constructor, args.toArray());
                    break;
                }
            }

        }
        else {
            bean = BeanUtils.getInstance(clazz, null, null);
        }
        return bean;
    }

    protected void populateBean(Object bean, BeanDefinition beanDefinition) throws Exception {
        List<PropertyArg> propertyArgs = beanDefinition.getPropertyArgs();

        if(propertyArgs == null || propertyArgs.isEmpty()) return;

        for(PropertyArg arg : propertyArgs) {
            String propertyName = arg.getName();
            String value = arg.getValue();
            String ref = arg.getRef();
            Object injectValue = null;
            if(value != null) {
                injectValue = value;
            }
            else if(ref != null && !ref.isEmpty()) {
                injectValue = getBean(ref);
            }

            //bean 是 cglib 代理生成的类, 其父类才是我们需要的类
            Class clazz = bean.getClass().getSuperclass();
            Field field = clazz.getDeclaredField(propertyName);
            if(injectValue != null) {
                ReflectionUtils.inject(field, bean, injectValue);
            }
        }

    }
}
