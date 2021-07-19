package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

public class Dokter {
    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("ttl")
    private String ttl;

    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("poli_id")
    private int poli_id;

    @SerializedName("poli")
    private Poli poli;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getPoli_id() {
        return poli_id;
    }

    public void setPoli_id(int poli_id) {
        this.poli_id = poli_id;
    }

    public Poli getPoli() {
        return poli;
    }
}
