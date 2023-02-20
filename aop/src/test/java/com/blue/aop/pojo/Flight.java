package com.blue.aop.pojo;

/**
 * @projectName: framework
 * @package: com.blue.aop.pojo
 * @className: Flight
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 16:07
 */
public class Flight extends Vehicle {

    public void fly() {
        System.out.println("flight flying");
    }

    @Override
    public void move() {
        fly();
    }
}
