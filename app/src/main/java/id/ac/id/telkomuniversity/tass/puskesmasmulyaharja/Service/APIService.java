package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("login")
    Call<User> login(@Body User user);
}
