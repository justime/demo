package org.vean.platform.service.service.usermanage;

import org.vean.platform.common.common.userservice.RoleEnum;
import org.vean.platform.common.common.userservice.UserCreateReqDTO;
import org.vean.platform.common.common.userservice.UserDTO;
import org.vean.platform.dao.dataobject.UserDO;

import java.util.ArrayList;
import java.util.List;

public class UserConvent {

    public static UserDTO conventToUserDTO(UserDO userDO) {
        if (userDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userDO.getAccount());
        userDTO.setUserName(userDO.getUsername());
        userDTO.setRole(RoleEnum.getRoleByStatus(userDO.getRole()));

        return userDTO;
    }

    public static List<UserDTO> conventToUserDTOList(List<UserDO> userDOList) {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        if (userDOList == null) {
            return userDTOList;
        }

        for (UserDO userDO : userDOList) {
            userDTOList.add(conventToUserDTO(userDO));
        }
        return userDTOList;
    }

    public static UserDO conventToUserDO(UserCreateReqDTO userCreateReqDTO) {
        if (userCreateReqDTO == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        userDO.setAccount(userCreateReqDTO.getAccount());
        userDO.setPassword(userCreateReqDTO.getPassword());
        userDO.setRole(userCreateReqDTO.getRole().getStatus());
        userDO.setUsername(userCreateReqDTO.getUserName());
        return userDO;
    }
}
