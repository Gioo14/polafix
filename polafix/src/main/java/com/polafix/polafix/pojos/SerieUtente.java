package com.polafix.polafix.pojos;

import java.util.ArrayList;


public class SerieUtente {
    public Serie serie;
    public int currentSeason;
    public ArrayList<ChapterSeen> userChapters;

    public SerieUtente(Serie serie, int currentSeason) {
        this.serie = serie;
        this.currentSeason = currentSeason;

        this.userChapters = new ArrayList<ChapterSeen>();
        ArrayList<Season> seasons = this.serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            System.out.println(seasons.get(i).getTitle());
            ArrayList<Chapter> chapters = seasons.get(i).getChapters();
            for(int j=0; j<chapters.size(); j++){
                ChapterSeen c = new ChapterSeen(chapters.get(j), ChapterState.PIENDENTE);
                userChapters.add(c);
                System.out.println(chapters.get(j).getTitle()+ " inserito");

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
        cs.setState(ChapterState.VISTO);
    }

   /* public Chapter getLastChapter(){
        Chapter c = null;
        int currentseason = this.getCurrentSeason();
        Map<Chapter, ChapterState> userChapters = this.getUserChapters();
        Season season = this.getSerie().getSeason(currentseason);
        ArrayList<Chapter> chapters = season.getChapters();
        for(int i=0; i<chapters.size(); i++){
            c = chapters.get(i);
            if(userChapters.get(c).equals(ChapterState.PIENDENTE)){
                return c;
            }
        }
        return c;
    }

    
    public boolean equals(SerieUtente serieUt){
        if(this.serie.equals(serieUt.serie) && this.currentSeason==serieUt.currentSeason && chaptersEquals(serieUt.getUserChapters()))
            return true;
        else
            return false;
    }

    private boolean chaptersEquals(Map<Chapter, ChapterState> chapters){
        for (Map.Entry<Chapter, ChapterState> entry : chapters.entrySet()) {
            Chapter chapter1 = entry.getKey();
            ChapterState state1 = entry.getValue();
            if(!this.userChapters.containsKey(chapter1))
                return false;
            else{
                ChapterState state2 = this.userChapters.get(chapter1);
                if(!state2.equals(state1))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serie, currentSeason, userChapters);
    }*/
}
