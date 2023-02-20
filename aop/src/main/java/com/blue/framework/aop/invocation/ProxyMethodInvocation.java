package com.blue.framework.aop.invocation;

/**
 * @projectName: framework
 * @package: com.blue.framework.aop.invocation
 * @className: ProxyMethodInvocation
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/17 16:54
 */
public interface ProxyMethodInvocation extends MethodInvocation {
    Object getProxy();
}
