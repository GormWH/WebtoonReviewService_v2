package org.zerock.webtoon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class WebToonVO {
    private Integer wno;
    private String title;
    private String author;
    private String genre;

}
