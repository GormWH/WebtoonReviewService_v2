package org.zerock.review.dao;

import org.zerock.review.domain.ReviewVO;

public class ReviewDAO {

    private ReviewVO[] reviewVOs;

    public ReviewDAO() {
        reviewVOs = new ReviewVO[10];
    }
    public void insertReview(ReviewVO vo) {
        for(int i=0; i<reviewVOs.length; i++){
            reviewVOs[i] = vo;
        }
    }

}
