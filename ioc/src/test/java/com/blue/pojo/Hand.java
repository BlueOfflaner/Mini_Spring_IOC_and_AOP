package com.blue.pojo;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Hand
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
public class Hand {

    @Inject(ref = "com.blue.pojo.Mouth")
    private Mouth mouth;

    @Value("1111111")
    private String code;

    public void waveHand() {
        System.out.println("hand 编号：" + code + ",依赖于mouth 编号" + mouth.getCode());
        System.out.println("挥一挥手");
    }
}
