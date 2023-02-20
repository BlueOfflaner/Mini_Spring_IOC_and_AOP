package com.blue.framework.ioc.core;

import com.blue.framework.ioc.bean.BeanDefinition;
import com.blue.framework.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.core
 * @className: JsonApplicationContext
 * @author: BlueOfflaner
 * @description: 容器, 对BeanFactory进行扩展
 * @date: 2023/2/15 20:21
 */
public class JsonApplicationContext extends AbstractApplicationContext {
    protected String[] fileNames;
    List<String> list = new ArrayList<>();

    public JsonApplicationContext(String fileName) {
        list.add(fileName);
        this.fileNames = list.toArray(new String[list.size()]);
    }

    public JsonApplicationContext(String[] fileName) {
        this.fileNames = fileName;
    }

    public void init() {
        loadFile();
    }

    protected void loadFile() {
        for (String fileName : fileNames) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {});
            if(beanDefinitions != null) {
                for (BeanDefinition beanDefinition : beanDefinitions) {
                    registerBean(beanDefinition.getName(), beanDefinition);
                }
            }
        }
    }
}
