package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Pemeriksaan {
    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("id_poli")
    private int id_poli;

    @SerializedName("keluhan")
    private String keluhan;

    @SerializedName("id_pasien")
    private int id_pasien;

    public Pemeriksaan(int id_poli, String keluhan, int id_pasien) {
        this.id_poli = id_poli;
        this.keluhan = keluhan;
        this.id_pasien = id_pasien;

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        this.tanggal = df.format(c);
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId_poli() {
        return id_poli;
    }

    public void setId_poli(int id_poli) {
        this.id_poli = id_poli;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public int getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(int id_pasien) {
        this.id_pasien = id_pasien;
    }
}
