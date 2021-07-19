package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PraktikListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Praktik;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PraktikResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityListPraktikBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPraktikActivity extends AppCompatActivity implements PraktikListAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ActivityListPraktikBinding binding;
    ProgressDialog dialog = null;
    private PraktikListAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPraktikBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        recyclerView = binding.rvListPraktik;
        dialog = new ProgressDialog(this);

        dialog.setMessage("Mengambil data praktik...");
        dialog.show();
        dialog.setIndeterminate(true);

        Call<PraktikResponse> call = APIClient.getRetrofitInstance().getPraktikList();
        call.enqueue(new Callback<PraktikResponse>() {
            @Override
            public void onResponse(Call<PraktikResponse> call, Response<PraktikResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("PRAKTIK", response.body().data.praktiks.toString());
                    adapter = new PraktikListAdapter(response.body().data.getPraktiks(), ListPraktikActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListPraktikActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
                Log.d("GET PRAKTIK LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<PraktikResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(Praktik item) {

    }
}