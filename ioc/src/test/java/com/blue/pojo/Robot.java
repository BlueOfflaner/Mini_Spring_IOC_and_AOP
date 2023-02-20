package com.blue.pojo;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Robot
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 20:35
 */
import com.blue.framework.ioc.annotation.Bean;
import com.blue.framework.ioc.annotation.Inject;
import com.blue.framework.ioc.annotation.Value;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Bean(name = "robot")
public class Robot {

    @Inject
    private Hand hand;

    @Inject
    private Mouth mouth;

    @Value("rp")
    private String name;

    public Robot() {

    }

    public Robot(String name) {
        this.name = name;
    }

    public Robot(Hand hand, Mouth mouth) {
        this.hand = hand;
        this.mouth = mouth;
    }

    public void show(){
        System.out.println(name);
        hand.waveHand();
        mouth.speak();
    }

}