package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer.DokterDeserializer;

public class DokterResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public DokterDeserializer data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DokterDeserializer getData() {
        return data;
    }
}
