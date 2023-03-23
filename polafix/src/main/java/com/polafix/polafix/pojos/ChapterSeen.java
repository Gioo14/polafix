package com.polafix.polafix.pojos;

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
}
