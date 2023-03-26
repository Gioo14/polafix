package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class SerieUser {
    private Serie serie;
    private int currentSeason;
    private ArrayList<ChapterSeen> userChapters;

    public SerieUser(Serie serie) {
        this.serie = serie;
        this.currentSeason = 1;
        this.userChapters = new ArrayList<ChapterSeen>();

        ArrayList<Season> seasons = this.serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            Season season = seasons.get(i);
            ArrayList<Chapter> chapters = season.getChapters();
            for(int j=0; j<chapters.size(); j++){
                ChapterSeen c = new ChapterSeen(season.getNumber(), chapters.get(j).getNumber(), ChapterState.NOTSEEN);
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

    public ArrayList<ChapterSeen> getUserChapters() {
        return userChapters;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public void setNextSeason(ChapterSeen currentChapter, int num_chapters) {
        if(currentChapter.getNumChapter()==num_chapters && !isLastChapter(currentChapter))
            this.currentSeason = currentChapter.getNumSeason()+1;
        else {
            if(isLastChapter(currentChapter))
                this.currentSeason = 1;
            else
                this.currentSeason = currentChapter.getNumSeason();
        }
    }

    public boolean isLastChapter(ChapterSeen chapter){
        if(this.getUserChapters().get(this.getUserChapters().size()-1).equals(chapter))
            return true;
        else
            return false;
    }

    public ChapterSeen findChapter(ArrayList<ChapterSeen> chapters, int numSeason, int numChapter){
        ChapterSeen cs = null;
        for(int i=0; i<chapters.size(); i++){
            cs = chapters.get(i);
            int season = chapters.get(i).getNumSeason();
            int chapter = chapters.get(i).getNumChapter();
            if(season==numSeason && chapter==numChapter)
                return cs;
        }
        return cs;
    }

    public void addChapterSeen(int season, int chapter){
        ArrayList<ChapterSeen> chapters = this.getUserChapters();
        ChapterSeen cs = findChapter(chapters, season, chapter);
        cs.setState(ChapterState.SEEN);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SerieUser)) {
            return false;
        }
        SerieUser serieUtente = (SerieUser) o;
        return Objects.equals(serie, serieUtente.serie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serie);
    }
}
