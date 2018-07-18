package org.vean.platform.web.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseParam {
    //可以放一些公共参数

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
