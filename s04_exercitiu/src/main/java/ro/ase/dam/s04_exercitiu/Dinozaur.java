package ro.ase.dam.s04_exercitiu;

public class Dinozaur {
    private String nume;
    private int varsta;
    private String specie;

    public Dinozaur(String nume, int varsta, String specie) {
        this.nume = nume;
        this.varsta = varsta;
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "Dinozaur{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", specie='" + specie + '\'' +
                '}';
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}

//5. mergem in layout si dam new resource file. "activitate AI"
//adaugam cod cu AI
//in MainActivity avem setContentView si modificam si punem "activitate AI"
