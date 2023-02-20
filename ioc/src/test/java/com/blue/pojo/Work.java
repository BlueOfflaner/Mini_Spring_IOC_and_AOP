package com.blue.pojo;

import com.blue.framework.ioc.annotation.Bean;
import com.blue.framework.ioc.annotation.Value;
import lombok.Data;

/**
 * @projectName: ioc
 * @package: com.blue.pojo
 * @className: Work
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 18:54
 */
@Data
@Bean
public class Work {

    @Value("coding")
    String name;

    @Value("china")
    String position;

    public void working() {
        System.out.println("Work name " + name);
        System.out.println("Work position " + position);
    }
}
