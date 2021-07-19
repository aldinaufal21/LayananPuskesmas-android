package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

public class Poli {
    @SerializedName("id")
    private int id;

    @SerializedName("nama_poli")
    private String nama_poli;

    @SerializedName("kode_antrian")
    private String kode_antrian;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public void setNama_poli(String nama_poli) {
        this.nama_poli = nama_poli;
    }

    public String getKode_antrian() {
        return kode_antrian;
    }

    public void setKode_antrian(String kode_antrian) {
        this.kode_antrian = kode_antrian;
    }

    @Override
    public String toString() {
        return "Poli{" +
                "id=" + id +
                ", nama_poli='" + nama_poli + '\'' +
                ", kode_antrian='" + kode_antrian + '\'' +
                '}';
    }
}
