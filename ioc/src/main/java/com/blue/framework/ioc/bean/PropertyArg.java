package com.blue.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @projectName: ioc
 * @package: com.blue.framework.ioc.bean
 * @className: PropertyArg
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 16:00
 */
@Data
@ToString
public class PropertyArg {
    private String name;

    private String value;

    private String typeName;

    private String ref;
}
