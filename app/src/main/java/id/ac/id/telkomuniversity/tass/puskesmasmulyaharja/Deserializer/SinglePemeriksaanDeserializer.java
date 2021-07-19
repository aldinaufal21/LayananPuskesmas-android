package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;

public class SinglePemeriksaanDeserializer {
    @SerializedName("pemeriksaan")
    public Pemeriksaan pemeriksaan;

    public Pemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(Pemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }
}
