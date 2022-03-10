package org.zerock;

import org.zerock.review.dao.ReviewDAO;
import org.zerock.review.service.ReviewService;
import org.zerock.ui.UI;
import org.zerock.user.dao.UserDAO;
import org.zerock.user.service.UserService;
import org.zerock.webtoon.dao.WebtoonDAO;
import org.zerock.webtoon.service.WebtoonService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner userScanner = new Scanner(new File("./localDB/users.txt"));
        Scanner webtoonScanner = new Scanner(new File("./localDB/webtoons.txt"));
        Scanner reviewScanner = new Scanner(new File("./localDB/reviews.txt"));

        WebtoonDAO webtoonDAO = new WebtoonDAO(webtoonScanner);
        ReviewDAO reviewDAO = new ReviewDAO(reviewScanner);
        UserDAO userDAO = new UserDAO(userScanner);

        WebtoonService webtoonService = new WebtoonService(webtoonDAO);
        ReviewService reviewService = new ReviewService(reviewDAO);
        UserService userService = new UserService(userDAO);

        UI ui = new UI(reviewService, webtoonService, userService, new Scanner(System.in));

        ui.display();

    }
}
