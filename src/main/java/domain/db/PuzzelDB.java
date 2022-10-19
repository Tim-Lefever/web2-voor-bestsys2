package domain.db;


import domain.model.Puzzel;

import java.util.ArrayList;
import java.util.Locale;

public class PuzzelDB {
    private int sequence;
    private ArrayList<Puzzel> puzzels = new ArrayList<>();

    public PuzzelDB() {
        sequence = 0;
        this.add(new Puzzel("Katten",           "Clementoni",   500,  "Dieren",       "Beige"));
        this.add(new Puzzel("Dorp",             "MBPuzzle",     4000, "Dorpen",       "Groen"));
        this.add(new Puzzel("New York",         "Ravensburger", 5000, "Landschappen", "Grijs"));
        this.add(new Puzzel("Theekransje",      "Trefl",        3000, "Stilleven"   , "Wit"));
        this.add(new Puzzel("Winterbetovering", "Goliath",      600,  "LandSChappen", "Wit"));

    }

    public void add(Puzzel puzzel) {
        if (this.puzzels.contains(puzzel)) throw new IllegalArgumentException("Deze puzzel is al opgeslagen.");
        this.sequence += 1;
        puzzel.setId(sequence);
        puzzels.add(puzzel);
    }

    public ArrayList<Puzzel> getPuzzels() {
        return puzzels;
    }

    public Puzzel getPuzzelMeesteStukken() {
        if (this.puzzels.size() == 0) {
            return null;
        }
        Puzzel grootstePuzzel = puzzels.get(0);
        int hoogsteAantal = grootstePuzzel.getAantalStukken();
        for (Puzzel p : puzzels) {
            if(p.getAantalStukken() > hoogsteAantal) {
                grootstePuzzel = p;
                hoogsteAantal = p.getAantalStukken();
            }
        }
        return grootstePuzzel;
    }

    public Puzzel findPuzzelOnId(int id) {
        for (Puzzel p : puzzels) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Puzzel> findPuzzelsOnString(String str) {
        ArrayList<Puzzel> match = new ArrayList<>();
        str = str.toLowerCase();
        for (Puzzel p : puzzels) {
            if (p.getNaam().toLowerCase().contains(str)
                    || p.getKleur().toLowerCase().contains(str)
                    || p.getThema().toLowerCase().contains(str)
                    || p.getMerk().toLowerCase().contains(str)) {
                match.add(p);
            }
        }
        return match;
    }

    public int getAantalPuzzels() {
        return this.puzzels.size();
    }

    public void deletePuzzelOnId(int id) {
        this.puzzels.removeIf(p -> p.getId() == id);
    }
}