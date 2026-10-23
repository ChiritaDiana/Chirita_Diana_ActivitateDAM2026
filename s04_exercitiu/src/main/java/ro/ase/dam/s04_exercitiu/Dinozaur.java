package ro.ase.dam.s04_exercitiu;

import java.io.Serializable;

public class Dinozaur implements Serializable {
    private String nume;
    private String perioada;      // Spinner
    private String habitat;       // RadioButton
    private boolean carnivor;     // CheckBox
    private float periculozitate; // RatingBar
    private boolean descoperitRecent; // Switch

    public Dinozaur(String nume, String perioada, String habitat,
                    boolean carnivor, float periculozitate, boolean descoperitRecent) {
        this.nume = nume;
        this.perioada = perioada;
        this.habitat = habitat;
        this.carnivor = carnivor;
        this.periculozitate = periculozitate;
        this.descoperitRecent = descoperitRecent;
    }

    @Override
    public String toString() {
        return "Dinozaur: " + nume + "\n" +
                "Perioada: " + perioada + "\n" +
                "Habitat: " + habitat + "\n" +
                "Carnivor: " + (carnivor ? "da" : "nu") + "\n" +
                "Periculozitate: " + periculozitate + " / 5\n" +
                "Descoperit recent: " + (descoperitRecent ? "da" : "nu");
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPerioada() {
        return perioada;
    }

    public void setPerioada(String perioada) {
        this.perioada = perioada;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public boolean isCarnivor() {
        return carnivor;
    }

    public void setCarnivor(boolean carnivor) {
        this.carnivor = carnivor;
    }

    public float getPericulozitate() {
        return periculozitate;
    }

    public void setPericulozitate(float periculozitate) {
        this.periculozitate = periculozitate;
    }

    public boolean isDescoperitRecent() {
        return descoperitRecent;
    }

    public void setDescoperitRecent(boolean descoperitRecent) {
        this.descoperitRecent = descoperitRecent;
    }
}

//5. mergem in layout si dam new resource file. "activitate AI"
//adaugam cod cu AI
//in MainActivity avem setContentView si modificam si punem "activitate AI"
