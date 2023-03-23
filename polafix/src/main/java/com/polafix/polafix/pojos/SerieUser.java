package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;


public class SerieUser {
    public Serie serie;
    public int currentSeason;
    public ArrayList<ChapterSeen> userChapters;

    public SerieUser(Serie serie, int currentSeason) {
        this.serie = serie;
        this.currentSeason = currentSeason;
        this.userChapters = new ArrayList<ChapterSeen>();

        ArrayList<Season> seasons = this.serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            ArrayList<Chapter> chapters = seasons.get(i).getChapters();
            for(int j=0; j<chapters.size(); j++){
                ChapterSeen c = new ChapterSeen(chapters.get(j), ChapterState.NOTSEEN);
                userChapters.add(c);
            }
        }
    }

    public Serie getSerie() {
        return serie;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public ArrayList<ChapterSeen> getUserChapters() {
        return userChapters;
    }

    private ChapterSeen findChapter(ArrayList<ChapterSeen> chapters, Chapter chapter){
        ChapterSeen cs = null;
        for(int i=0; i<chapters.size(); i++){
            cs = chapters.get(i);
            if(cs.getChapter().equals(chapter))
                return cs;
        }
        return cs;
    }

    public void addChapterSeen(Chapter chapter){
        ArrayList<ChapterSeen> chapters = this.getUserChapters();
        ChapterSeen cs = findChapter(chapters, chapter);
        cs.setState(ChapterState.SEEN);
    }

    public Chapter getLastChapter(){
        Chapter c = null;
        int currentseason = this.getCurrentSeason();
        ArrayList<ChapterSeen> userChapters = this.getUserChapters();
        Season season = this.getSerie().getSeason(currentseason);
        ArrayList<Chapter> chapters = season.getChapters();
        for(int i=0; i<chapters.size(); i++){
            if(userChapters.get(i).getState().equals(ChapterState.NOTSEEN)){
                c = userChapters.get(i).getChapter();
                return c;
            }
        }
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SerieUser)) {
            return false;
        }
        SerieUser serieUtente = (SerieUser) o;
        return Objects.equals(serie, serieUtente.serie) && currentSeason == serieUtente.currentSeason && Objects.equals(userChapters, serieUtente.userChapters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serie, currentSeason, userChapters);
    }
}
