package com.blue.pojo;

import com.blue.framework.ioc.annotation.Bean;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Flight
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/16 21:49
 */
@Bean
public class Flight implements Movable {

    @Override
    public void move() {
        System.out.println("Flight flying");
    }
}
