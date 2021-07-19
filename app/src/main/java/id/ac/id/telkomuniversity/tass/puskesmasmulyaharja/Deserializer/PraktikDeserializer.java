package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Praktik;

public class PraktikDeserializer {
    @SerializedName("praktik")
    public ArrayList<Praktik> praktiks;

    public ArrayList<Praktik> getPraktiks() {
        return praktiks;
    }

    public void setPraktiks(ArrayList<Praktik> praktiks) {
        this.praktiks = praktiks;
    }
}
