package com.polafix.polafix.pojos;

import java.util.Objects;
import javax.persistence.*;

@Embeddable
public class ChapterSeen {
    private int numChapter;
    private int numSeason;
    private ChapterState state;
    
    public ChapterSeen(int numSeason, int numChapter, ChapterState state) {
        this.numChapter = numChapter;
        this.numSeason = numSeason;
        this.state = state;
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
