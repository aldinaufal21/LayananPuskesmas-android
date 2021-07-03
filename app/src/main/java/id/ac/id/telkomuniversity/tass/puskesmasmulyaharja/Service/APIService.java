package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PoliResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("login")
    Call<UserResponse> login(@Body User user);

    @GET("poli")
    Call<PoliResponse> getPoli();

    @POST("pemeriksaan/add")
    Call<APIResponse> addPemeriksaan(@Body Pemeriksaan pemeriksaan);
}
