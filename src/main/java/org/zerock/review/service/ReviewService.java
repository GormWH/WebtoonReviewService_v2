package org.zerock.review.service;

import org.zerock.review.dao.ReviewDAO;
import org.zerock.review.domain.ReviewVO;
import org.zerock.user.domain.UserVO;
import org.zerock.webtoon.domain.WebtoonVO;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewService {

    private ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO){
        this.reviewDAO = reviewDAO;
    }

    public ArrayList<ReviewVO> getAll() {
        return reviewDAO.getReviews();
    }

    public void addReview(WebtoonVO webtoon, UserVO user, int score){
        int rno = reviewDAO.getReviews().size() + 1;
        ReviewVO vo = new ReviewVO(rno, webtoon.getWno(), user.getId(), score);
        reviewDAO.addReview(vo);
    }

    public ArrayList<ReviewVO> getUsersReview(UserVO user) {
        return reviewDAO.getUserReviews(user.getId());
    }
    public ReviewVO removeReview(Integer rno, UserVO user) {
        ReviewVO target = reviewDAO.getReviews().get(rno - 1);
        if (target != null && target.getId().equals(user.getId())) {
            return reviewDAO.remove(rno);
        }
        return null;
    }

    public ArrayList<ReviewVO> sortByScore() {
        ArrayList<ReviewVO> reviews = reviewDAO.getReviews();
        Collections.sort(reviews, (a,b) -> b.getScore() - a.getScore());
        return reviews;
    }

}
