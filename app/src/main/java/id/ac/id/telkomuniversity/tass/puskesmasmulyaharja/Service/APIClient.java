package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://puskesmas.ekopz.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final APIService service = retrofit.create(APIService.class);

    public static APIService getRetrofitInstance(){
        return service;
    }
}
