package org.zerock.review.dao;

import org.zerock.review.domain.ReviewVO;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewDAO {

    private ArrayList<ReviewVO> reviews;

    public ReviewDAO(Scanner scanner) {
        reviews = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            Integer rno = Integer.parseInt(line[0]);
            Integer wno = Integer.parseInt(line[1]);
            String id = line[2];
            Integer score = Integer.parseInt(line[3]);
            addReview(new ReviewVO(rno, wno, id, score));
        }
    }

    public void addReview(ReviewVO review) {
        reviews.add(review);
    }

    public ReviewVO remove(Integer rno) {
        ReviewVO target = null;
        for (ReviewVO review : reviews) {
            if (review.getRno().equals(rno)) {
                target = reviews.remove(rno - 1);
                reNumber(rno - 1);
                return target;
            }
        }
        return target;
    }

    public void reNumber(Integer idx) {
        for (int i = idx; i < reviews.size(); i++) {
            reviews.get(i).setRno(i + 1);
        }
    }

    public ArrayList<ReviewVO> getReviews() {
        return (ArrayList<ReviewVO>) reviews.clone();
    }

    public ArrayList<ReviewVO> getUserReviews(String id) {
        ArrayList<ReviewVO> result = new ArrayList<>();
        reviews.forEach(review -> {
            if (review.getId().equals(id)) {
                result.add(review);
            }
        });
        return result;
    }

}
