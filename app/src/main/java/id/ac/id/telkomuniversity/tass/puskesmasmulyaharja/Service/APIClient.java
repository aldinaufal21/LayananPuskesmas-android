package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://puskesmas.ekopz.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(HttpInterceptor.getLogger())
            .build();

    private static final APIService service = retrofit.create(APIService.class);

    public static APIService getRetrofitInstance(){
        return service;
    }
}
