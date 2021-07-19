package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.DokterListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PemeriksaanListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Dokter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.DokterResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityDokterListBinding;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityPemeriksaanBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterListActivity extends AppCompatActivity implements DokterListAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ActivityDokterListBinding binding;
    ProgressDialog dialog = null;
    private DokterListAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDokterListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        recyclerView = binding.rvListDokter;
        dialog = new ProgressDialog(this);

        dialog.setMessage("Mengambil data dokter...");
        dialog.show();
        dialog.setIndeterminate(true);

        Call<DokterResponse> call = APIClient.getRetrofitInstance().getDokterList();
        call.enqueue(new Callback<DokterResponse>() {
            @Override
            public void onResponse(Call<DokterResponse> call, Response<DokterResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("DOKTER", response.body().data.dokters.toString());
                    adapter = new DokterListAdapter(response.body().data.getDokters(), DokterListActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DokterListActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
                Log.d("GET DOKTER LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<DokterResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(Dokter item) {

    }
}