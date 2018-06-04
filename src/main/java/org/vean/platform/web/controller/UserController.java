package org.vean.platform.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vean.platform.client.service.IUserService;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.common.userservice.UserDTO;
import org.vean.platform.web.constant.CommonUtils;
import org.vean.platform.web.execute.CommonExecute;
import org.vean.platform.web.execute.CommonExecutor;
import org.vean.platform.web.param.GetUserByAccountParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userService")
    IUserService userService;

    @RequestMapping(value = "/getUserByAccount", produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String getUserByAccount(HttpServletRequest request, HttpServletResponse response) {
        return CommonExecutor.execute(request, response, GetUserByAccountParam.class, new CommonExecute<GetUserByAccountParam>() {
            @Override
            public HttpResult<UserDTO> execute(GetUserByAccountParam param) {
            	return userService.getUserByAccount(param.getAccount());
            }
        });
    }
}
