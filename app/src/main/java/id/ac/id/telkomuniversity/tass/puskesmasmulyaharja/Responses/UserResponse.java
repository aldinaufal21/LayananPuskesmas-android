package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer.UserDeserializer;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;

public class UserResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public UserDeserializer data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserDeserializer getData() {
        return data;
    }
}
