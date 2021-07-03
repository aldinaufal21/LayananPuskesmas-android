package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

public class APIResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
