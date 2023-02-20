package com.blue.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.bean
 * @className: ConstructorArg
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 16:00
 */
@Data
@ToString
public class ConstructorArg {
    int index;

    String ref;

    String name;

    Object value;
}
