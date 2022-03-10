package org.zerock.user;

public class UserService {

    private UserDAO userDAO;

    public UserVO check(String id){
        UserVO[] users = userDAO.getUsers();
        UserVO result = null;
        for (UserVO user:users) {
            if (user.getId() == id){
                result = user;
                break;
            }
        }
        return result;

    }

}

