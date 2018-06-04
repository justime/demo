package org.vean.platform.common.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 336439024770228552L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
