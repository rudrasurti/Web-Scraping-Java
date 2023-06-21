package com.houarizegai.webscraping.imdb;

import java.util.Arrays;

public class Film {
    private String title;
    private String year;
    private String[] category;
    private String description;
    private String rate;

    public Film(String title, String year, String[] category, String description, String rate) {
        this.title = title;
        this.year = year;
        this.category = category;
        this.description = description;
        this.rate = rate;
    }

    // Getters and setters (if needed) go here

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String[] getCategory() {
		return category;
	}

	public void setCategory(String[] category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", category=" + Arrays.toString(category) +
                ", description='" + description + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
