package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SerieUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @OneToOne
    @JsonProperty("serie")   
    private Serie serie;
    @JsonProperty("title")
    private String title;
    @JsonProperty("currentSeason")
    private int currentSeason;
    @ElementCollection
    private List<ChapterSeen> userChapters;

    public SerieUser() {}

    public SerieUser(Serie serie) {
        this.serie = serie;
        this.currentSeason = 1;
        this.userChapters = new ArrayList<ChapterSeen>();
        this.title = serie.getName();

        List<Season> seasons = this.serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            Season season = seasons.get(i);
            List<Chapter> chapters = season.getChapters();
            for(int j=0; j<chapters.size(); j++){
                ChapterSeen c = new ChapterSeen(season.getNumber(), chapters.get(j).getNumber(), chapters.get(j).getTitle(), chapters.get(j).getDescription(), ChapterState.NOTSEEN);
                userChapters.add(c);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
        this.currentSeason = 1;
        this.userChapters = new ArrayList<ChapterSeen>();
        this.title = serie.getName();

        List<Season> seasons = this.serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            Season season = seasons.get(i);
            List<Chapter> chapters = season.getChapters();
            for(int j=0; j<chapters.size(); j++){
                ChapterSeen c = new ChapterSeen(season.getNumber(), chapters.get(j).getNumber(), chapters.get(j).getTitle(), chapters.get(j).getDescription(), ChapterState.NOTSEEN);
                userChapters.add(c);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public List<ChapterSeen> getUserChapters() {
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

    public ChapterSeen findChapter(List<ChapterSeen> chapters, int numSeason, int numChapter){
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
        List<ChapterSeen> chapters = this.getUserChapters();
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
