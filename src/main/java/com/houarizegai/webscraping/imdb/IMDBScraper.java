package com.houarizegai.webscraping.imdb;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class IMDBScraper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating";
        Document page = Jsoup.connect(url).get();
        Elements elementsContainer = page.getElementsByClass("lister-item-content");

        List<Film> topFilms = new LinkedList<>();

        elementsContainer.forEach(element -> {
            final String title = element.select("h3 > a").text();
            String year = element.getElementsByClass("lister-item-year").text();
            year = year.substring(1, year.length() - 1); // Remove () from the year
            final String[] category = element.select(".text-muted > .genre").text().split(", ");
            final String description = element.getElementsByTag("p").get(1).text();
            final String rate = element.getElementsByClass("ratings-imdb-rating").attr("data-value");

            Film film = new Film(title, year, category, description, rate);
            topFilms.add(film);
        });

        for (Film film : topFilms) {
        	
        	System.out.println("==========================================");
            System.out.println("Title: " + film.getTitle());
            System.out.println("Year: " + film.getYear());
            System.out.println("Categories: " + Arrays.toString(film.getCategory()));
            System.out.println("Description: " + film.getDescription());
            System.out.println("Rating: " + film.getRate());
        }
    }
}
