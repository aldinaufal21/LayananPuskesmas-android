package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    public String email;
    @SerializedName("no_hp")
    public String no_hp;
    @SerializedName("password")
    public String password;

    @SerializedName("id")
    public int id;
    @SerializedName("nama")
    public String nama;
    @SerializedName("alamat")
    public String alamat;
    @SerializedName("berat_badan")
    public int berat_badan;
    @SerializedName("tinggi_badan")
    public int tinggi_badan;
    @SerializedName("gol_darah")
    public char gol_darah = 'c';
    @SerializedName("tgl_lahir")
    public String tgl_lahir;
    @SerializedName("jenis_kelamin")
    public int jenis_kelamin = 0;

    public User(String no_hp, String password) {
        this.no_hp = no_hp;
        this.password = password;
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
