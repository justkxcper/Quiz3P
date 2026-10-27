package com.example.quiz3p;

public class Pytanie {
    private long idZdjecia;
    private String trescPytania;
    private boolean poprawnaOdp;
    // tak - true; nie - false.
    private boolean czyPoprawnie;
    // dobra odpowiedź - true; zła - false.


    public Pytanie(long idZdjecia, String trescPytania, boolean poprawnaOdp) {
        this.idZdjecia = idZdjecia;
        this.trescPytania = trescPytania;
        this.poprawnaOdp = poprawnaOdp;
        czyPoprawnie = false;
    }

    public long getIdZdjecia() {
        return idZdjecia;
    }

    public String getTrescPytania() {
        return trescPytania;
    }

    public boolean isPoprawnaOdp() {
        return poprawnaOdp;
    }

    public boolean isCzyPoprawnie() {
        return czyPoprawnie;
    }

    public void setCzyPoprawnie(boolean czyPoprawnie) {
        this.czyPoprawnie = czyPoprawnie;
    }

}
