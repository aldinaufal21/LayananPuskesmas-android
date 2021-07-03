package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer.PoliDeserializer;

public class PoliResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public PoliDeserializer data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public PoliDeserializer getData() {
        return data;
    }
}
