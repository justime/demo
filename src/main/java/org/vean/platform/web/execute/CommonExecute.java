package org.vean.platform.web.execute;

import org.vean.platform.web.param.BaseParam;
import org.vean.platform.common.common.HttpResult;

public interface CommonExecute<T extends BaseParam> {
    HttpResult<T> execute(T param);
}
