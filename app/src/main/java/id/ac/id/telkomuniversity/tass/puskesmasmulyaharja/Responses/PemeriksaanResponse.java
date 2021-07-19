package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer.PemeriksaanDeserializer;

public class PemeriksaanResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public PemeriksaanDeserializer data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public PemeriksaanDeserializer getData() {
        return data;
    }
}
