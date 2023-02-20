package com.blue.aop;

import com.blue.aop.pojo.Car;
import com.blue.aop.pojo.Flight;
import com.blue.aop.pojo.Movable;
import com.blue.aop.pojo.Vehicle;
import com.blue.framework.aop.core.AopJsonApplicationContext;
import com.blue.framework.ioc.core.ApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * @projectName: framework
 * @package: com.blue.aop
 * @className: MyTest
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 16:03
 */
public class MyTest {
    @Test
    void test1() {
        Car car = new Car();
        car.run();

        Flight flight = new Flight();
        flight.fly();
    }

    @Test
    void test2() throws Exception {
        ApplicationContext applicationContext = new AopJsonApplicationContext("application.json");
        applicationContext.init();
        Car car = (Car) applicationContext.getBean("CarProxy");
        car.run();

        Flight flight = (Flight) applicationContext.getBean("Flight");
        flight.fly();
    }

    @Test
    void test3() throws Exception {
        ApplicationContext applicationContext = new AopJsonApplicationContext("application.json");
        applicationContext.init();

        Car car = (Car) applicationContext.getBean("CarProxy");
        Vehicle v1 = car;
        v1.move();

        Vehicle v = (Vehicle) applicationContext.getBean("VehicleProxy");
        v.move();

    }
}
