package org.zerock.webtoon.dao;

import org.zerock.webtoon.domain.WebToonVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class WebToonDAO {

    private ArrayList<WebToonVO> webToons;

    public WebToonDAO() throws FileNotFoundException {
        webToons = new ArrayList<>();
        File webToosFile = new File("C:\\zzz\\webtoondata.txt");
        Scanner scanner = new Scanner(webToosFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split(",");
            Integer wno = Integer.parseInt(arr[0]);
            String title = arr[1];
            String author = arr[2];
            String genre = arr[3];
            WebToonVO webToonVo = new WebToonVO(wno, title, author, genre);
            webToons.add(webToonVo);
        }

    }

//    public WebToonVO[] addWebToons() throws FileNotFoundException {
//
//        return this.webToons.clone();
//    }

    public ArrayList<WebToonVO> sortWebToonsByTitle() {
        Collections.sort(webToons, (a, b)-> a.getTitle().compareTo(b.getTitle()));
        return webToons;
    }

    public ArrayList<WebToonVO> sortWebToonsByGenre(String genre) {
        ArrayList<WebToonVO>webToonsGenreList = new ArrayList<>();
        for (WebToonVO webToon:webToons) {
            if (webToon.getGenre().equals(genre)){
                webToonsGenreList.add(webToon);
            }
        }
        System.out.println(webToonsGenreList);
        return webToonsGenreList;
    }

    public ArrayList<WebToonVO> getWebToons() {
        return (ArrayList<WebToonVO>) webToons.clone();
    }
}
