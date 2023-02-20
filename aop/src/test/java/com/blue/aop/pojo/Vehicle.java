package com.blue.aop.pojo;

/**
 * @projectName: framework
 * @package: com.blue.aop.pojo
 * @className: Vehicle
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 17:30
 */
public class Vehicle implements Movable {
    @Override
    public void move() {
        System.out.println("move");
    }
}
