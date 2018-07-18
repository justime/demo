package org.vean.platform.dao.mapper;

import org.vean.platform.common.common.userservice.QueryUserCondition;
import org.vean.platform.dao.dataobject.UserDO;

import java.util.List;

public interface UserMapper {

    /**
     * 根据账号获取用户
     * 
     * @param account
     * @return
     */
    UserDO getUserByAccount(String account);

    /**
     * 创建用户
     * 
     * @param userDO
     * @return
     */
    int createUser(UserDO userDO);

    /**
     * 统计员工数量
     * 
     * @param queryUserCondition
     * @return
     */
    int countQuery(QueryUserCondition queryUserCondition);

    /**
     * 分页获取用户列表
     * 
     * @param queryUserCondition
     * @return
     */
    List<UserDO> query(QueryUserCondition queryUserCondition);

    /**
     * 删除用户
     * 
     * @param account
     * @return
     */
    int deleteUserByAccount( String account);

}
