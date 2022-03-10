package org.zerock.webtoon.dao;

import org.zerock.webtoon.domain.WebtoonVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WebtoonDAO {

    private ArrayList<WebtoonVO> webtoons;

    public WebtoonDAO(Scanner scanner) {
        webtoons = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split(",");
            Integer wno = Integer.parseInt(arr[0]);
            String title = arr[1];
            String author = arr[2];
            String genre = arr[3];
            WebtoonVO webtoonVo = new WebtoonVO(wno, title, author, genre);
            webtoons.add(webtoonVo);
        }
    }

    public ArrayList<WebtoonVO> getWebtoons() {
        return (ArrayList<WebtoonVO>) webtoons.clone();
    }

    public ArrayList<WebtoonVO> sortWebtoonsByTitle() {
        ArrayList<WebtoonVO> result = getWebtoons();
        Collections.sort(result, (a, b) -> a.getTitle().compareTo(b.getTitle()));
        return result;
    }

    public ArrayList<WebtoonVO> sortWebtoonsByGenre(String genre) {
        ArrayList<WebtoonVO> result = new ArrayList<>();
        for (WebtoonVO webtoon : webtoons) {
            if (webtoon.getGenre().equals(genre)){
                result.add(webtoon);
            }
        }
        return result;
    }

    public ArrayList<WebtoonVO> sortWebtoonsByAuthor() {
        ArrayList<WebtoonVO> result = getWebtoons();
        Collections.sort(result, (a, b) -> a.getAuthor().compareTo(b.getAuthor()));
        return result;
    }

}
