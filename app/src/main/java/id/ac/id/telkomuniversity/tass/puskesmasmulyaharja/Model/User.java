package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User {
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("password")
    private String password;

    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("berat_badan")
    private int berat_badan;
    @SerializedName("tinggi_badan")
    private int tinggi_badan;
    @SerializedName("gol_darah")
    private String gol_darah;
    @SerializedName("tgl_lahir")
    private String tgl_lahir;
    @SerializedName("jenis_kelamin")
    private int jenis_kelamin;
    @SerializedName("ktp")
    private KTP ktp;

    public User(String email, String no_hp, String password, String nama, String alamat, int berat_badan, int tinggi_badan, String gol_darah, String tgl_lahir, String jenis_kelamin) {
        this.email = email;
        this.no_hp = no_hp;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.gol_darah = gol_darah;
        this.tgl_lahir = tgl_lahir;
        if(jenis_kelamin.equals("Laki-Laki"))
            this.jenis_kelamin = 1;
        else
            this.jenis_kelamin = 2;
    }

    public User(String no_hp, String password) {
        this.no_hp = no_hp;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getBerat_badan() {
        return berat_badan;
    }

    public int getTinggi_badan() {
        return tinggi_badan;
    }

    public String getGol_darah() {
        return gol_darah;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public int getJenis_kelamin() {
        return jenis_kelamin;
    }

    public KTP getKtp() {
        return ktp;
    }

    public void setKtp(KTP ktp) {
        this.ktp = ktp;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", no_hp='" + no_hp + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", berat_badan=" + berat_badan +
                ", tinggi_badan=" + tinggi_badan +
                ", gol_darah=" + gol_darah +
                ", tgl_lahir='" + tgl_lahir + '\'' +
                ", jenis_kelamin=" + jenis_kelamin +
                '}';
    }
}
