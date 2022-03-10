package org.zerock;

import org.zerock.user.UserDAO;
import org.zerock.user.UserVO;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        UserDAO test = new UserDAO();
        UserVO[] testarr = test.getUser();
        System.out.println(Arrays.toString(testarr));
    }
}
