package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.DokterResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.ListPemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PoliResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PraktikResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.SinglePemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("login")
    Call<UserResponse> login(@Body User user);

    @POST("register")
    Call<APIResponse> register(@Body User user);

    @GET("poli")
    Call<PoliResponse> getPoli();

    @POST("pemeriksaan/add")
    Call<APIResponse> addPemeriksaan(@Body Pemeriksaan pemeriksaan);

    @GET("pemeriksaan/id/{id}")
    Call<ListPemeriksaanResponse> getPemeriksaanByPasien(@Path("id") String id_pasien);

    @GET("pemeriksaan/detail/{id}")
    Call<SinglePemeriksaanResponse> getPemeriksaanByID(@Path("id") String id_pemeriksaan);

    @GET("dokter")
    Call<DokterResponse> getDokterList();

    @GET("praktik")
    Call<PraktikResponse> getPraktikList();
}
