package com.blue.pojo;

import com.blue.framework.ioc.annotation.Bean;
import com.blue.framework.ioc.annotation.Inject;
import com.blue.framework.ioc.annotation.Value;
import lombok.Data;

import java.lang.reflect.Constructor;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Person
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 18:53
 */
@Data
@Bean(name = "person")
public class Person {
    @Value("zhangsan")
    String name;

    @Value("18")
    Integer age;

    @Inject
    Work work;

    @Inject(ref = "com.blue.pojo.Flight")
    Movable m;

    public Person() {

    }

    public Person(Integer age) {
        this.age = age;
    }

    public void work() {
        System.out.println(name);
        System.out.println(age);
        System.out.println(work);
        work.working();
        m.move();
    }
}
