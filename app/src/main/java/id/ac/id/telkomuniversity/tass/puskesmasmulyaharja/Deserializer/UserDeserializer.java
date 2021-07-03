package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Deserializer;

import com.google.gson.annotations.SerializedName;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;

public class UserDeserializer {
    @SerializedName("pasien")
    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
