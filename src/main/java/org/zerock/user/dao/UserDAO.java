package org.zerock.user.dao;

import org.zerock.user.domain.UserVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserDAO {

    private UserVO[] users = new UserVO[4];

    public UserDAO(Scanner scanner) {

        for(int i = 0; i < users.length; i++) {
            String line = scanner.nextLine();
            String[] arr = line.split(",");

            String id = arr[0];
            String name = arr[1];

            UserVO userVO = new UserVO(id, name);
            users[i] = userVO;
        } // for end

    }

    public UserVO[] getUsers(){
        return this.users.clone();
    }

}


