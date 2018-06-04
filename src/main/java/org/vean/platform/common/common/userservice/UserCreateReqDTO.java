package org.vean.platform.common.common.userservice;

import org.vean.platform.common.common.BaseDTO;

public class UserCreateReqDTO extends BaseDTO {

    private static final long serialVersionUID = -8136326022743105997L;

    /**
     * 用户名
     */
    private String            userName;

    /**
     * 账号
     */
    private String            account;

    /**
     * 角色
     */
    private RoleEnum          role;

    /**
     * 密码
     */
    private String            password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
