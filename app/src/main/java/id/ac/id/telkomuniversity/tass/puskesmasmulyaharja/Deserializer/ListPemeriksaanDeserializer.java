package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;

public class ListPemeriksaanDeserializer {
    @SerializedName("pemeriksaan")
    public ArrayList<Pemeriksaan> pemeriksaans;

    public ArrayList<Pemeriksaan> getPemeriksaans() {
        return pemeriksaans;
    }

    public void setPemeriksaans(ArrayList<Pemeriksaan> pemeriksaans) {
        this.pemeriksaans = pemeriksaans;
    }
}
