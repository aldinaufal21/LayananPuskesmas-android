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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {
    @POST("login")
    Call<UserResponse> login(@Body User user);

    @POST("register")
    Call<UserResponse> register(@Body User user);

    @GET("pasien/id/{id}")
    Call<UserResponse> getPasienByID(@Path("id") String id_pasien);

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

    @Multipart
    @POST("ktp/tambah/{id}")
    Call<APIResponse> uploadKTP(@Path("id") String id_pasien, @Part MultipartBody.Part foto, @Part("nik") RequestBody nik);
}
