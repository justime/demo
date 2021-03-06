package org.vean.platform.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vean.platform.client.service.IUserService;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.common.userservice.RoleEnum;
import org.vean.platform.common.common.userservice.UserCreateReqDTO;
import org.vean.platform.common.exception.BusinessException;
import org.vean.platform.common.exception.ErrorEnum;
import org.vean.platform.common.exception.SystemException;
import org.vean.platform.web.constant.CommonUtils;
import org.vean.platform.web.execute.CommonExecute;
import org.vean.platform.web.execute.CommonExecutor;
import org.vean.platform.web.param.BaseParam;
import org.vean.platform.web.param.CreateUserParam;
import org.vean.platform.web.param.TestMultiDataSourceParam;

/**
 * 测试类
 * @author Vean
 *
 */
@Controller
@RequestMapping(value = "/test")
public class TestExceptionController {

    @Resource(name = "userService")
    IUserService userService;
    
    /**
     * 测试异常
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/testControllerException", produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String getUserByAccount(HttpServletRequest request, HttpServletResponse response) {
        return CommonExecutor.execute(request, response, BaseParam.class, new CommonExecute<BaseParam>() {
            @Override
            public HttpResult<BaseParam> execute(BaseParam param) {
                throw new SystemException(ErrorEnum.UNKNOWN_EXCEPTION);
            }
        });
    }
    
    /**
     * 测试事务
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/testTransaction", produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String testTransaction(HttpServletRequest request, HttpServletResponse response) {
        return CommonExecutor.execute(request, response, CreateUserParam.class, new CommonExecute<CreateUserParam>() {
            @Override
            public HttpResult<Boolean> execute(CreateUserParam param) {
                RoleEnum roleEnum = RoleEnum.getRoleByStatus(param.getRole());
                if (roleEnum == null)
                    throw new BusinessException(ErrorEnum.PARAM_IS_INVALID);

                UserCreateReqDTO userCreateReqDTO = new UserCreateReqDTO();
                userCreateReqDTO.setAccount(param.getAccount());
                userCreateReqDTO.setPassword(param.getPassword());
                userCreateReqDTO.setRole(roleEnum);
                userCreateReqDTO.setUserName(param.getUsername());
                return userService.testTransaction(userCreateReqDTO);
            }
        });
    }
    
    /**
     * 测试租户数据源
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/testMultiDataSource", produces = CommonUtils.CONTENT_TYPE)
    @ResponseBody
    public String testMultiDataSource(HttpServletRequest request, HttpServletResponse response) {
        return CommonExecutor.execute(request, response, TestMultiDataSourceParam.class, new CommonExecute<TestMultiDataSourceParam>() {
            @Override
            public HttpResult<Boolean> execute(TestMultiDataSourceParam param) {
                RoleEnum roleEnum = RoleEnum.getRoleByStatus(param.getRole());
                if (roleEnum == null)
                    throw new BusinessException(ErrorEnum.PARAM_IS_INVALID);
                UserCreateReqDTO userCreateReqDTO = new UserCreateReqDTO();
                userCreateReqDTO.setAccount(param.getAccount());
                userCreateReqDTO.setPassword(param.getPassword());
                userCreateReqDTO.setRole(roleEnum);
                userCreateReqDTO.setUserName(param.getUsername());
                return userService.testMultiDataSource(userCreateReqDTO);
            }
        });
    }
}
