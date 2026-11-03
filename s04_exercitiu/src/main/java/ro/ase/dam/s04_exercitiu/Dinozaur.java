package ro.ase.dam.s04_exercitiu;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Dinozaur implements Parcelable {
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

    protected Dinozaur(Parcel in) {
        nume = in.readString();
        perioada = in.readString();
        habitat = in.readString();
        carnivor = in.readByte() != 0;
        periculozitate = in.readFloat();
        descoperitRecent = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(perioada);
        dest.writeString(habitat);
        dest.writeByte((byte) (carnivor ? 1 : 0));
        dest.writeFloat(periculozitate);
        dest.writeByte((byte) (descoperitRecent ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dinozaur> CREATOR = new Creator<Dinozaur>() {
        @Override
        public Dinozaur createFromParcel(Parcel in) {
            return new Dinozaur(in);
        }

        @Override
        public Dinozaur[] newArray(int size) {
            return new Dinozaur[size];
        }
    };

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

