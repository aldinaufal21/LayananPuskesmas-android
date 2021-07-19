package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PemeriksaanListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PoliSpinnerAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityListPemeriksaanBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPemeriksaanActivity extends AppCompatActivity implements PemeriksaanListAdapter.OnItemClickListener {

    private ActivityListPemeriksaanBinding binding;
    private RecyclerView recyclerView;
    ProgressDialog dialog = null;
    private PemeriksaanListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPemeriksaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recyclerView = binding.rvListPemeriksaan;
        dialog = new ProgressDialog(this);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_key", Context.MODE_PRIVATE);
        String id_user = sharedPref.getString("user_id", "1");

        dialog.show();
        dialog.setIndeterminate(true);

        Call<PemeriksaanResponse> call = APIClient.getRetrofitInstance().getPemeriksaanByPasien(id_user);
        call.enqueue(new Callback<PemeriksaanResponse>() {
            @Override
            public void onResponse(Call<PemeriksaanResponse> call, Response<PemeriksaanResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("PEMERIKSAAN", response.body().data.pemeriksaans.toString());
                    adapter = new PemeriksaanListAdapter(response.body().data.getPemeriksaans(), ListPemeriksaanActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListPemeriksaanActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
                Log.d("GET PEMERIKSAAN LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<PemeriksaanResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(Pemeriksaan item) {
        Intent intent;
        if(item.getStatus() >= 2 && item.getStatus() <= 4){
            intent = new Intent(this, DetailPemeriksaanOnlineActivity.class);
        } else {
            intent = new Intent(this, DetailPemeriksaanOfflineActivity.class);
        }
        startActivity(intent);
    }
}