package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class SerieUser {
    private Serie serie;
    private int currentSeason;
    private ArrayList<ChapterSeen> userChapters;
    private ChapterSeen nextChapter;

    public SerieUser(Serie serie, int currentSeason) {
        this.serie = serie;
        this.currentSeason = currentSeason;
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

        this.nextChapter = userChapters.get(0);
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

    public ChapterSeen getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(ChapterSeen currentChapter) {
        ArrayList<ChapterSeen> chapters = this.getUserChapters();
        ChapterSeen nextChapter = findChapter(chapters, currentSeason, (currentChapter.getNumChapter()+1));
        if(nextChapter!=null && !isLastChapter(nextChapter))
            this.nextChapter=nextChapter;
        else {
            if(nextChapter!=null && isLastChapter(nextChapter))
                this.nextChapter = findChapter(chapters, 1, 1);
            else
                this.nextChapter=findChapter(chapters, currentSeason+1, 1);     
        }
    }

    public ArrayList<ChapterSeen> getUserChapters() {
        return userChapters;
    }

    private boolean isLastChapter(ChapterSeen chapter){
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

    public void addChapterSeen(Season season, Chapter chapter){
        ArrayList<ChapterSeen> chapters = this.getUserChapters();
        ChapterSeen cs = findChapter(chapters, season.getNumber(), chapter.getNumber());
        cs.setState(ChapterState.SEEN);
    }

   /*  public ChapterSeen getLastChapter(){
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
    }*/

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
