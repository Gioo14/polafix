package com.polafix.polafix.pojos;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="chapters")
public class Chapter {

    @Id
    private int number;
    @Id
    private String title;
    @Column(name="description")
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) o;
        return number == chapter.number && Objects.equals(title, chapter.title) && Objects.equals(description, chapter.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title, description);
    }
}
