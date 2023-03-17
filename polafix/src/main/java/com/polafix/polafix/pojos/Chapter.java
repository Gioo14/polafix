package com.polafix.polafix.pojos;

public class Chapter {
    public int number;
    public String title;
    public String description;

    public Chapter(int number, String title, String description) {
        setNumber(number);
        setTitle(title);
        setDescription(description);
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
