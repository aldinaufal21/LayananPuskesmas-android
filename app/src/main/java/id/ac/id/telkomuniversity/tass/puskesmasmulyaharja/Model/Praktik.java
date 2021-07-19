package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

public class Praktik {
    @SerializedName("id")
    private int id;

    @SerializedName("id_dokter")
    private int id_dokter;

    @SerializedName("jam_mulai")
    private String jam_mulai;

    @SerializedName("jam_berakhir")
    private String jam_berakhir;

    @SerializedName("dokter")
    private Dokter dokter;

    @SerializedName("poli")
    private Poli poli;

    public Praktik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(int id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_berakhir() {
        return jam_berakhir;
    }

    public void setJam_berakhir(String jam_berakhir) {
        this.jam_berakhir = jam_berakhir;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public Poli getPoli() {
        return poli;
    }

    public void setPoli(Poli poli) {
        this.poli = poli;
    }

    public String getJamShift() {
        return this.jam_mulai + " - " + this.jam_berakhir;
    }
}
