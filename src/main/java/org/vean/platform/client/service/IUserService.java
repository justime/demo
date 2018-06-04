package org.vean.platform.client.service;

import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.common.Pagination;
import org.vean.platform.common.common.userservice.QueryUserCondition;
import org.vean.platform.common.common.userservice.RoleEnum;
import org.vean.platform.common.common.userservice.UserCreateReqDTO;
import org.vean.platform.common.common.userservice.UserDTO;

public interface IUserService {

    HttpResult<UserDTO> getUserByAccount(String account);

    HttpResult<UserDTO> verifyAndGetUser(String account, String password, RoleEnum roleEnum);

    HttpResult<Boolean> createUser(UserCreateReqDTO userCreateReqDTO);

    HttpResult<Integer> countQuery(QueryUserCondition queryUserCondition);

    HttpResult<Pagination<UserDTO>> query(QueryUserCondition queryUserCondition);

    HttpResult<Boolean> deleteUserByAccount(String account);

    HttpResult<Boolean> testTransaction(UserCreateReqDTO userCreateReqDTO);

    HttpResult<Boolean> testMultiDataSource(UserCreateReqDTO userCreateReqDTO);

}
