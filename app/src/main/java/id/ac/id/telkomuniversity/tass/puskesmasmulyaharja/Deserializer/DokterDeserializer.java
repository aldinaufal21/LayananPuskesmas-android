package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Dokter;

public class DokterDeserializer {
    @SerializedName("dokter")
    public ArrayList<Dokter> dokters;

    public ArrayList<Dokter> getDokters() {
        return dokters;
    }

    public void setDokters(ArrayList<Dokter> dokters) {
        this.dokters = dokters;
    }
}
