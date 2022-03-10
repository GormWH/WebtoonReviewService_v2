package org.zerock.review.service;

import com.sun.security.jgss.GSSUtil;
import com.sun.source.tree.UsesTree;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.zerock.review.dao.ReviewDAO;
import org.zerock.review.domain.ReviewVO;

import java.util.Arrays;

public class ReviewService {

    private ReviewDAO reviewDAO;
    private ReviewVO[] reviewVO;
    private int idx;

    public ReviewService(){
        reviewDAO = new ReviewDAO();
        this.idx = 0;
    }

    public void addReview(int wno, String id, int score){
        int rno = idx + 1;
        ReviewVO vo = new ReviewVO(rno, wno, id, score);
        idx++;
        reviewDAO.insertReview(vo);
    }

    public ReviewVO[] sortByScore(){
        Arrays.sort(reviewVO, (a,b) -> {
            if(a == null || b == null){
                return 0;
            }
            return b.getScore() - a.getScore();
        });

        return reviewVO;
    }

}
