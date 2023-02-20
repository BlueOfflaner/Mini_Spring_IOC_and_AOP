package com.blue.aop.pojo;

import java.util.Random;

/**
 * @projectName: framework
 * @package: com.blue.aop.pojo
 * @className: Car
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 16:04
 */
public class Car extends Vehicle {


    public void run() {
        System.out.println("car running");
        Random random = new Random();

        if(random.nextInt(100) > 50) {
            try {
                broken();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("car running");
    }

    private void broken() throws InterruptedException {
        System.out.println("the car is broken");
        Thread.sleep(1000);
    }

    @Override
    public void move() {
        run();
    }
}
