package org.vean.platform.service.service.usermanage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vean.platform.client.service.IUserService;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.common.Pagination;
import org.vean.platform.common.common.userservice.QueryUserCondition;
import org.vean.platform.common.common.userservice.RoleEnum;
import org.vean.platform.common.common.userservice.UserCreateReqDTO;
import org.vean.platform.common.common.userservice.UserDTO;
import org.vean.platform.common.exception.BusinessException;
import org.vean.platform.common.exception.ErrorEnum;
import org.vean.platform.common.exception.SystemException;
import org.vean.platform.dao.dataobject.DataSourceDO;
import org.vean.platform.dao.dataobject.UserDO;
import org.vean.platform.service.dao.DataSourceDao;
import org.vean.platform.service.dao.UserDao;
import org.vean.platform.service.datasourceswitch.DataSourceBeanBuilder;
import org.vean.platform.service.datasourceswitch.impl.DataSourceHolder;

@Service("userService")
public class UserServiceImpl implements IUserService {


    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "datasourceDao")
    private DataSourceDao dataSourceDao;

    @Override
    public HttpResult<UserDTO> getUserByAccount(String account) {
        UserDO userDO = userDao.getUserByAccount(account);
        if (userDO == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        UserDTO userDTO = UserConvent.conventToUserDTO(userDO);
        return HttpResult.successResult(userDTO);
    }

    @Override
    public HttpResult<UserDTO> verifyAndGetUser(String account, String password, RoleEnum roleEnum) {
        if (account == null || password == null || roleEnum == null) {
            return HttpResult.successResult(null);
        }

        UserDO userDO = userDao.getUserByAccount(account);
        if (userDO == null) {
            // 账号不存在
            return HttpResult.successResult(null);
        }
        if (!userDO.getPassword().equals(password)) {
            // 密码错误
            return HttpResult.successResult(null);
        }
        if (userDO.getRole() != roleEnum.getStatus()) {
            // 用户不属于此角色
            return HttpResult.successResult(null);
        }

        UserDTO userDTO = UserConvent.conventToUserDTO(userDO);
        return HttpResult.successResult(userDTO);
    }

    @Override
    public HttpResult<Pagination<UserDTO>> query(QueryUserCondition queryUserCondition) {
        if (queryUserCondition == null) {
            throw new BusinessException(ErrorEnum.PARAM_NULL);
        }
        int totalCount = userDao.countQuery(queryUserCondition);
        List<UserDO> userDOList = userDao.query(queryUserCondition);
        Pagination<UserDTO> pagination = new Pagination<UserDTO>(queryUserCondition.getOffset(),
                queryUserCondition.getLimit(), totalCount, UserConvent.conventToUserDTOList(userDOList));
        return HttpResult.successResult(pagination);
    }

    @Override
    public HttpResult<Integer> countQuery(QueryUserCondition queryUserCondition) {
        int totalCount = userDao.countQuery(queryUserCondition);
        return HttpResult.successResult(totalCount);
    }

    @Override
    public HttpResult<Boolean> createUser(UserCreateReqDTO userCreateReqDTO) {
        if (userCreateReqDTO == null) {
            return HttpResult.successResult(Boolean.FALSE);
        }
        UserDO userDO = UserConvent.conventToUserDO(userCreateReqDTO);
        if (userDao.createUser(userDO)) {
            return HttpResult.successResult(Boolean.TRUE);
        }
        return HttpResult.successResult(Boolean.FALSE);
    }

    @Override
    public HttpResult<Boolean> deleteUserByAccount(String account) {
        if (account == null) {
            throw new BusinessException(ErrorEnum.PARAM_IS_INVALID);
        }
        HttpResult<UserDTO> accountResult = this.getUserByAccount(account);
        if (!accountResult.isSuccess()) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }

        if (!this.userDao.deleteUserByAccount(account)) {
            throw new BusinessException(ErrorEnum.USER_DELETE_FAIL);
        }
        return HttpResult.successResult(Boolean.TRUE);
    }

    @Override
    @Transactional
    public HttpResult<Boolean> testTransaction(UserCreateReqDTO userCreateReqDTO) {
        if (userCreateReqDTO == null) {
            return HttpResult.successResult(Boolean.FALSE);
        }
        UserDO userDO = UserConvent.conventToUserDO(userCreateReqDTO);
        if (userDao.createUser(userDO)) {
            throw new SystemException(ErrorEnum.TEST_TRANSACTION_EXCEPTION);
        }
        return HttpResult.successResult(Boolean.FALSE);
    }

    /**
     * 注意！ @Transactional注解用在此处将导致错误，因为该注解支持的是单数据源的事务，而此方法中已经是多数据源了
     * @param userCreateReqDTO
     * @return
     */
    @Override
    public HttpResult<Boolean> testMultiDataSource(UserCreateReqDTO userCreateReqDTO) {
        if (userCreateReqDTO == null) {
            return HttpResult.successResult(Boolean.FALSE);
        }

        UserDO userDO = UserConvent.conventToUserDO(userCreateReqDTO);
        //先向默认数据源插入
        /*if (!userDao.createUser(userDO)) {
            throw new BusinessException(ErrorEnum.TEST_MULTI_DATASOURCE_EXCEPTION);
        }*/

        //再向起他数据源插入
        List<DataSourceDO> dataSourceDOList = this.dataSourceDao.query();
        for (DataSourceDO dataSourceDO : dataSourceDOList) {
            DataSourceBeanBuilder builder = new DataSourceBeanBuilder(
            		dataSourceDO.getDatasourceName()+dataSourceDO.getDatabaseIp(),
                    dataSourceDO.getDatabaseIp(),
                    dataSourceDO.getDatabasePort(),
                    dataSourceDO.getDatabaseName(),
                    dataSourceDO.getUsername(),
                    dataSourceDO.getPassword());
            DataSourceHolder.setDataSource(builder);
            if (!userDao.createUser(userDO)) {
                throw new BusinessException(ErrorEnum.TEST_MULTI_DATASOURCE_EXCEPTION);
            }
            DataSourceHolder.clearDataSource();
        }

        return HttpResult.successResult(Boolean.TRUE);
    }
}
