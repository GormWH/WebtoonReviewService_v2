package org.zerock.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserDAO {

    private UserVO[] userList = new UserVO[4];

    public UserDAO() throws FileNotFoundException {

        File userFile = new File("./userVO.txt");   // 직접 경로
        Scanner scanner = new Scanner(userFile);

        for(int i = 0; i < userList.length; i++) {
            String line = scanner.nextLine();
            String[] arr = line.split(",");

            String id = arr[0];
            String name = arr[1];

            UserVO userVO = new UserVO(id, name);
            userList[i] = userVO;
        } // for end
    }

    public UserVO[] getUsers(){
        return this.userList.clone();
    }

}


