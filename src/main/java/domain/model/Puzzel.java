package domain.model;

import java.util.Locale;

public class Puzzel {
    private int id;
    private String naam;
    private String merk;
    private int aantalStukken;
    private String thema;
    private String kleur;

    //constructor
    public Puzzel(String naam, String merk, int aantalStukken, String thema, String kleur) {
        setNaam(naam);
        setMerk(merk);
        setAantalStukken(aantalStukken);
        setThema(thema);
        setKleur(kleur);
    }

    public Puzzel() {}

    public void setNaam(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("De naam is ongeldig!");
        }
        this.naam = naam.trim();
    }

    public void setKleur(String kleur) {
        if (kleur == null || kleur.trim().isEmpty()) {
            throw new IllegalArgumentException("De kleur is ongeldig!");
        }
        this.kleur = kleur.trim();
    }

    public void setAantalStukken(int aantalStukken) {
        if (aantalStukken <= 0) {
            throw new IllegalArgumentException("Het aantal stukken is ongeldig!");
        }
        this.aantalStukken = aantalStukken;
    }

    public void setMerk(String merk) {
        if (merk == null || merk.trim().isEmpty()) {
            throw new IllegalArgumentException("Het merk is ongeldig!");
        }
        this.merk = merk.trim();
    }

    public void setThema(String thema) {
        if (thema == null || thema.trim().isEmpty()) {
            throw new IllegalArgumentException("Het thema is ongeldig!");
        }
        this.thema = (thema.substring(0, 1).toUpperCase() + thema.substring(1).toLowerCase()).trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puzzel)) return false;
        Puzzel puzzel = (Puzzel) o;
        return aantalStukken == puzzel.aantalStukken
                && naam.equals(puzzel.naam)
                && merk.equals(puzzel.merk)
                && thema.equals(puzzel.thema)
                && kleur.equals(puzzel.kleur);
    }




    public int getAantalStukken() {
        return aantalStukken;
    }

    public int getId() {
        return id;
    }

    public String getMerk() {
        return merk;
    }

    public String getThema() {
        return thema;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return this.naam;
    }

    public String getKleur() {
        return kleur;
    }
}