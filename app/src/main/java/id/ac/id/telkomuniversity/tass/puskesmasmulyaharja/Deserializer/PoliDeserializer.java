package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Poli;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;

public class PoliDeserializer {
    @SerializedName("poli")
    public ArrayList<Poli> polis;

    public ArrayList<Poli> getPolis() {
        return polis;
    }

    public void setPolis(ArrayList<Poli> polis) {
        this.polis = polis;
    }
}
