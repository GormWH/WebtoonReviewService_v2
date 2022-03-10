package org.zerock.webtoon.service;

import org.zerock.webtoon.dao.WebToonDAO;
import org.zerock.webtoon.domain.WebToonVO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class WebToonService {
    private WebToonDAO webToonDAO;

    public ArrayList<WebToonVO> readAll() throws FileNotFoundException {
        return webToonDAO.getWebToons();
    }

    public ArrayList<WebToonVO> getSortByTitle() throws FileNotFoundException {
        return webToonDAO.sortWebToonsByTitle();
    }

    public ArrayList<WebToonVO> getSortByGenre(String genre) throws FileNotFoundException {
        return webToonDAO.sortWebToonsByGenre(genre);
    }

    public WebToonVO getOne(Integer wno) {
        WebToonVO result = null;
        ArrayList<WebToonVO> webToons = webToonDAO.getWebToons();
        for (WebToonVO webToon : webToons) {
            if (webToon.getWno() == wno) {
                result = webToon;
            }
        }
        return result;
    }

    public ArrayList<WebToonVO> getAll() {
        return webToonDAO.getWebToons();
    }
}
