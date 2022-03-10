package org.zerock.ui;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.zerock.review.service.ReviewService;
import org.zerock.webtoon.domain.WebToonVO;
import org.zerock.webtoon.service.WebToonService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@RequiredArgsConstructor
public class UI {

    @NonNull
    private ReviewService reviewService;
    @NonNull
    private WebToonService webtoonService;
    @NonNull
    private UserService userService;
    @NonNull
    private Scanner scanner;

    private UserVO user;

    public void display() {
        // 처음에 한번 웹툰 목록 출력
        viewWebtoons();

        // 메인 화면 루프
        outer:
        while (true) {
            System.out.println("무엇을 하시겠습니까?");
            String action = inputString("1:리뷰 등록, 2:정렬 순서 변경, 3:로그 아웃, q:종료");
            switch (action) {
                case "1":
                    addReview();
                    break;
                case "2":
                    viewSortedWebtoons();
                    break;
                case "3":
                    logout();
                    break;
                case "q":
                    break outer;
                default:
                    System.out.println("유효하지 않은 선택지 입니다. 다시 선택해주세요.");
            }
        }
    }

    /*
     * 유저 아이디를 입력 받고 존재하는 유저라면 UserVO를 반환한다.(없는 유저면 null 반환)
     */
    private void login() {
        String id = inputString("당신의 아이디를 입력해주세요.(q:로그인 취소)");
        if ("q".equals(id)) {
            return;
        }

        this.user = userService.getOne(id); /**********여기서 null 나왔을 경우 예외처리**********/

        System.out.println(user.getName() + "님, 로그인 되셨습니다.");
    }

    private void logout() {
        if (this.user == null) {
            System.out.println("당신은 이미 로그아웃 되어있습니다..");
            return;
        }
        System.out.println(user.getName() + "님, 로그아웃 되셨습니다.");
        this.user = null;
    }

    // 웹툰 목록 출력
    private void viewWebtoons() {
        ArrayList<WebToonVO> webToons = webtoonService.getAll();
        for (WebToonVO webtoon : webToons) {
            System.out.println(webtoon);
        }
    }

    // 웹툰 정렬해서 출력
    private void viewSortedWebtoons() {
        System.out.println("어떤 순서로 정렬할까요?");
        String sortOrder = inputString("1:제목, 2:장르, 3:작가, 4:등록번호(기본)");
        switch (sortOrder) {
            case "1":
                Arrays.stream(webtoonService.sortByTitle()).forEach(webtoon -> System.out.println(webtoon));
                break;
            case "2":
                Arrays.stream(webtoonService.sortByGenre()).forEach(webtoon -> System.out.println(webtoon));
                break;
            case "3":
                Arrays.stream(webtoonService.sortByAuthor()).forEach(webtoon -> System.out.println(webtoon));
                break;
            case "4":
                viewWebtoons();
                break;
            default:
                System.out.println("유효하지 않은 메뉴를 선택하셨습니다. 다시 선택해 주세요.");
                viewSortedWebtoons();
                break;
        }
    }

    private void addReview() {
        if (user == null) {
            System.out.println("로그인된 사용자만 리뷰를 남기실 수 있습니다.");
            login();
            if (user == null) {
                System.out.println("로그인에 실패하였습니다. 이전으로 돌아갑니다.");
                return;
            }
        }
        String action = inputString("리뷰를 남기실 웹툰을 골라주세요.(q:이전으로 돌아가기)");
        if ("q".equals(action)) {
            return;
        }
        Integer wno = Integer.parseInt(action);
        WebToonVO webtoon = webtoonService.getOne(wno);
        UserVO userCopy = user.clone();
        int score = inputInteger("점수를 메겨주세요.");
        reviewService.addReview(webtoon.getWno(), user.getId(), score);
    }

    private String inputString(String msg) {
        System.out.println(msg);
        System.out.print(">> ");
        String input = scanner.nextLine();
        return input;
    }

    private int inputInteger(String msg) {
        int input = Integer.parseInt(inputString(msg));
        return input;
    }
}
