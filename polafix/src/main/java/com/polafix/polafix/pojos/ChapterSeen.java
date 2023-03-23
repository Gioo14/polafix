package com.polafix.polafix.pojos;

import java.util.Objects;

public class ChapterSeen {
    public Chapter chapter;
    public ChapterState state;
    
    public ChapterSeen(Chapter chapter, ChapterState state) {
        this.chapter = chapter;
        this.state = state;
    }

    public Chapter getChapter() {
        return chapter;
    }
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
    public ChapterState getState() {
        return state;
    }
    public void setState(ChapterState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChapterSeen)) {
            return false;
        }
        ChapterSeen chapterSeen = (ChapterSeen) o;
        return Objects.equals(chapter, chapterSeen.chapter) && Objects.equals(state, chapterSeen.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapter, state);
    }
}
