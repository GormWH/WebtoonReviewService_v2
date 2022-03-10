package org.zerock.ui;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.zerock.review.domain.ReviewVO;
import org.zerock.review.service.ReviewService;
import org.zerock.user.service.UserService;
import org.zerock.user.domain.UserVO;
import org.zerock.webtoon.domain.WebtoonVO;
import org.zerock.webtoon.service.WebtoonService;

import java.util.ArrayList;
import java.util.Scanner;

@RequiredArgsConstructor
public class UI {

    @NonNull
    private ReviewService reviewService;
    @NonNull
    private WebtoonService webtoonService;
    @NonNull
    private UserService userService;
    @NonNull
    private Scanner scanner;

    private UserVO user;

    public void display() {
        this.user = null;
        // 처음에 한번 웹툰 목록 출력(번호 순)
        webtoonService.getAll().forEach(webtoon -> System.out.println(webtoon));

        // 메인 화면 루프
        outer:
        while (true) {
            System.out.println("무엇을 하시겠습니까?");
            String action = inputString("1:웹툰 보기, 2:리뷰 보기, 3:리뷰 작성, 4:리뷰 삭제, 5:로그인, 6:로그아웃, q:종료");
            switch (action) {
                case "1":
                    viewSortedWebtoons();
                    break;
                case "2":
                    viewSortedReviews();
                    break;
                case "3":
                    addReview();
                    break;
                case "4":
                    removeReview();
                    break;
                case "5":
                    login();
                    break;
                case "6":
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
        String id = inputString("당신의 아이디를 입력해주세요.");
        user = userService.check(id);
        if (user != null) {
            System.out.println(this.user.getName() + "님, 로그인 되셨습니다.");
        } else {
            System.out.println("존재하지 않는 아이디입니다");
        }

    }

    private void logout() {
        if (this.user == null) {
            System.out.println("당신은 이미 로그아웃 되어있습니다..");
            return;
        }
        System.out.println(user.getName() + "님, 로그아웃 되셨습니다.");
        this.user = null;
    }

    // 웹툰 정렬해서 출력
    private void viewSortedWebtoons() {
        System.out.println("어떤 순서로 정렬할까요?");
        String sortOrder = inputString("1:제목, 2:장르, 3:작가, 4:등록번호(기본)");
        ArrayList<WebtoonVO> webtoons;
        switch (sortOrder) {
            case "1":
                webtoons = webtoonService.sortByTitle();
                break;
            case "2":
                String genre = inputString("장르를 입력해주세요.");
                webtoons = webtoonService.sortByGenre(genre);
                break;
            case "3":
                webtoons = webtoonService.sortByAuthor();
                break;
            case "4":
                webtoons = webtoonService.getAll();
                break;
            default:
                System.out.println("유효하지 않은 메뉴를 선택하셨습니다. 뒤로 돌아갑니다.");
                return;
        }
        webtoons.forEach(webtoon -> System.out.println(webtoon));
    }

    private void addReview() {
        // 로그인 체크
        if (user == null) {
            System.out.println("로그인된 사용자만 리뷰를 남기실 수 있습니다.");
            return;
        }

        // 리뷰할 웹툰 선택
        String action = inputString("리뷰를 남기실 웹툰을 골라주세요.(q:이전으로 돌아가기)");
        if ("q".equals(action)) {
            return;
        }
        Integer wno = Integer.parseInt(action);
        WebtoonVO webtoon = webtoonService.getOne(wno);
        if (webtoon == null) {
            System.out.println("선택하신 번호는 존재하지 않습니다.");
            return;
        }
        int score = inputInteger("점수를 메겨주세요.");
        reviewService.addReview(webtoon, user, score);
    }

    private void removeReview() {
        // 로그인 체크
        if (user == null) {
            System.out.println("로그인된 사용자만 리뷰를 삭제하실 수 있습니다.");
            return;
        }
        System.out.println(user.getName() + "님의 리뷰 목록입니다.");
        reviewService.getUsersReview(user).forEach(review -> System.out.println(review));
        Integer rno = inputInteger("삭제할 리뷰 번호를 선택해 주세요.");
        ReviewVO removed = reviewService.removeReview(rno, user);
        if (removed == null) {
            System.out.println("존재하지 않는 리뷰이거나 당신이 작성한 리뷰가 아닙니다.");
            return;
        }
        System.out.println("리뷰가 성공적으로 삭제 되었습니다.");
        System.out.println("Removed:" + removed);
    }

    private void viewSortedReviews() {
        String action = inputString("1:등록순, 2:점수순");
        ArrayList<ReviewVO> reviews;
        switch (action) {
            case "1":
                reviews = reviewService.getAll();
                break;
            case "2":
                reviews = reviewService.sortByScore();
                break;
            default:
                System.out.println("유효하지 않은 입력입니다.");
                return;
        }
        reviews.forEach(review -> System.out.println(review));
    }

    private String inputString(String msg) {
        System.out.println(msg);
        System.out.print(">> ");
        return scanner.nextLine();
    }

    private int inputInteger(String msg) {
        return Integer.parseInt(inputString(msg));
    }
}
