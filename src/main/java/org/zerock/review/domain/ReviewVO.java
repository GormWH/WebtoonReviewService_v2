package org.zerock.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewVO {
    private Integer rno;
    private Integer wno;
    private String id;
    private Integer score;

}
