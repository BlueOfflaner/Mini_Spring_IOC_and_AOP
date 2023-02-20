package com.blue.framework.aop.advisor;

import lombok.Data;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.advisor
 * @className: Advisor
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 17:26
 */
@Data
public class Advisor {
    //干什么
    private Advice advice;
    //在哪里
    private Pointcut pointcut;
}
