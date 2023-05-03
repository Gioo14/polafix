package com.polafix.polafix.pojos;

import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class ChapterSeen {
    
    @JsonProperty("numChapter")
    private int numChapter;
    @JsonProperty("numSeason")
    private int numSeason;
    @JsonProperty("state")
    private ChapterState state;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    public ChapterSeen(){}
    
    public ChapterSeen(int numSeason, int numChapter, String title, String description, ChapterState state) {
        this.numChapter = numChapter;
        this.numSeason = numSeason;
        this.state = state;
        this.title = title;
        this.description = description;
    }

    public ChapterState getState() {
        return state;
    }

    public void setState(ChapterState state) {
        this.state = state;
    }

    public int getNumChapter() {
        return this.numChapter;
    }

    public void setNumChapter(int numChapter) {
        this.numChapter = numChapter;
    }

    public int getNumSeason() {
        return this.numSeason;
    }

    public void setNumSeason(int numSeason) {
        this.numSeason = numSeason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChapterSeen)) {
            return false;
        }
        ChapterSeen chapterSeen = (ChapterSeen) o;
        return numChapter == chapterSeen.numChapter && numSeason == chapterSeen.numSeason && Objects.equals(state, chapterSeen.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numChapter, numSeason, state);
    }
}
