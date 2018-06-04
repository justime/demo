package org.vean.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vean.platform.client.service.IUserService;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.common.userservice.UserDTO;
import org.vean.platform.web.constant.CommonUtils;
import org.vean.platform.web.constant.UrlConstant;
import org.vean.platform.web.execute.CommonExecute;
import org.vean.platform.web.execute.CommonExecutor;
import org.vean.platform.web.param.GetUserByAccountParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = UrlConstant.USER_PREFIX)
public class UserController {

    @Resource(name = "userService")
    IUserService userService;

    @RequestMapping(value = UrlConstant.GET_USER_BY_ACCOUNT, produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String getUserByAccount(HttpServletRequest request, HttpServletResponse response) {
        return CommonExecutor.execute(request, response, GetUserByAccountParam.class, new CommonExecute<GetUserByAccountParam>() {
            @Override
            public HttpResult execute(GetUserByAccountParam param) {
                HttpResult<UserDTO> result = userService.getUserByAccount(param.getAccount());
                return result;
            }
        });
    }
}
