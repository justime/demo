package org.vean.platform.common.common.userservice;

public enum RoleEnum {
    STUDENT(1, "学生"),
    TEACHER(2, "教师"),
    ADMIN(3, "管理员");

    private int    status;
    private String desc;

    public static RoleEnum getRoleByStatus(int status) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getStatus() == status) {
                return roleEnum;
            }
        }
        return null;
    }

    private RoleEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
