package org.zerock.webtoon.service;

import lombok.AllArgsConstructor;
import org.zerock.webtoon.dao.WebtoonDAO;
import org.zerock.webtoon.domain.WebtoonVO;

import java.util.ArrayList;

@AllArgsConstructor
public class WebtoonService {

    private WebtoonDAO webtoonDAO;

    public ArrayList<WebtoonVO> getAll() {
        return webtoonDAO.getWebtoons();
    }

    public WebtoonVO getOne(Integer wno) {
        WebtoonVO result = null;
        ArrayList<WebtoonVO> webtoons = webtoonDAO.getWebtoons();
        for (WebtoonVO webtoon : webtoons) {
            if (webtoon.getWno().equals(wno)) {
                result = webtoon;
            }
        }
        return result;
    }

    public ArrayList<WebtoonVO> sortByWno() {
        return webtoonDAO.getWebtoons();
    }

    public ArrayList<WebtoonVO> sortByTitle() {
        return webtoonDAO.sortWebtoonsByTitle();
    }

    public ArrayList<WebtoonVO> sortByGenre(String genre) {
        return webtoonDAO.sortWebtoonsByGenre(genre);
    }

    public ArrayList<WebtoonVO> sortByAuthor() {
        return webtoonDAO.sortWebtoonsByAuthor();
    }



}
