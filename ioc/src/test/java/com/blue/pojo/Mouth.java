package com.blue.pojo;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Mouth
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 20:36
 */
import com.blue.framework.ioc.annotation.Bean;
import com.blue.framework.ioc.annotation.Inject;
import com.blue.framework.ioc.annotation.Value;
import lombok.Data;

@Data
@Bean
public class Mouth {

    @Inject(ref = "com.blue.pojo.Hand")
    private Hand hand;

    @Value("22222222")
    private String code;

    public void speak() {
        System.out.println("mouth 编号：" + code + ",依赖于hand 编号" + hand.getCode());
        System.out.println("say hello world");
    }
}
