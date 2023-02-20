package com.blue.pojo;

import com.blue.framework.ioc.annotation.Bean;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Car
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/16 21:47
 */
@Bean(name = "car")
public class Car implements Movable {
    @Override
    public void move() {
        System.out.println("Car running");
    }
}
