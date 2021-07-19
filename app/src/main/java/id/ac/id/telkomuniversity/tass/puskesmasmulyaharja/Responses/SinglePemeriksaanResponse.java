package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer.SinglePemeriksaanDeserializer;

public class SinglePemeriksaanResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public SinglePemeriksaanDeserializer data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public SinglePemeriksaanDeserializer getData() {
        return data;
    }
}
