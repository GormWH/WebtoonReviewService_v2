package org.zerock.user.service;

import lombok.AllArgsConstructor;
import org.zerock.user.domain.UserVO;
import org.zerock.user.dao.UserDAO;

@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;

    public UserVO check(String id){
        UserVO[] users = userDAO.getUsers();
        UserVO result = null;
        for (UserVO user:users) {
            if (user.getId().equals(id)){
                result = user;
                break;
            }
        }
        return result;
    }

}

