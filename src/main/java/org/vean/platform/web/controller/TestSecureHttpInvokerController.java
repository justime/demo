package org.vean.platform.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vean.platform.client.service.IRpcService;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.exception.ErrorEnum;
import org.vean.platform.web.constant.CommonUtils;
import org.vean.platform.web.constant.UrlConstant;
import org.vean.platform.web.execute.CommonExecute;
import org.vean.platform.web.execute.CommonExecutor;
import org.vean.platform.web.param.BaseParam;

import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping(value = UrlConstant.TEST_PREFIX)
public class TestSecureHttpInvokerController {

    @Resource(name="consumeRpcService")
    IRpcService rpcService;

    @RequestMapping(value = UrlConstant.TEST_SECURE_HTTP_INVOKER, produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String testSecureHttpInvoker(final HttpServletRequest request, final HttpServletResponse response) {
        return CommonExecutor.execute(request, response, BaseParam.class, new CommonExecute<BaseParam>() {
            @Override
            public HttpResult<String> execute(BaseParam param) {
                String result=rpcService.service();
                if(StringUtils.isNullOrEmpty(result)){
                    return HttpResult.failedResult(ErrorEnum.RUNTIME_EXCEPTION);
                }else{
                    return HttpResult.successResult(result);
                }
            }
        });
    }
}
