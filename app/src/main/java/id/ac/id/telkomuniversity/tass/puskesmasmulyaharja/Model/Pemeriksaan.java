package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Pemeriksaan {
    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String tanggal;

    @SerializedName("id_poli")
    private int id_poli;

    @SerializedName("keluhan")
    private String keluhan;

    @SerializedName("id_pasien")
    private int id_pasien;

    @SerializedName("hasil_pemeriksaan")
    private String hasil_pemeriksaan;

    @SerializedName("status_pemeriksaan")
    private String status_pemeriksaan;

    @SerializedName("status")
    private int status;

    @SerializedName("pasien")
    private User pasien;

    @SerializedName("antrian")
    private Antrian antrian;

    public Pemeriksaan(int id_poli, String keluhan, int id_pasien) {
        this.id_poli = id_poli;
        this.keluhan = keluhan;
        this.id_pasien = id_pasien;

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        this.tanggal = df.format(c);
    }

    public int getId() {
        return id;
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

    public String getHasil_pemeriksaan() {
        return hasil_pemeriksaan;
    }

    public void setHasil_pemeriksaan(String hasil_pemeriksaan) {
        this.hasil_pemeriksaan = hasil_pemeriksaan;
    }

    public String getStatus_pemeriksaan() {
        return status_pemeriksaan;
    }

    public void setStatus_pemeriksaan(String status_pemeriksaan) {
        this.status_pemeriksaan = status_pemeriksaan;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getPasien() {
        return pasien;
    }

    public String getStatusDetail() {
        String msg = "";
        switch (this.status) {
            case 1:
                msg = "Pengajuan";
                break;
            case 2:
                msg = "Pemeriksaan Ringan";
                break;
            case 3:
                msg = "Kirim Obat";
                break;
            case 4:
                msg = "Selesai";
                break;
            case 5:
                msg = "Pemeriksaan Berat";
                break;
            case 6:
                msg = "Pengajuan pemeriksaan ditolak";
                break;
        }
        return msg;
    }

    public Antrian getAntrian() {
        return antrian;
    }
}
