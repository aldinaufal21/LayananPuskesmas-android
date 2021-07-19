package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PemeriksaanListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.SinglePemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityDetailPemeriksaanOnlineBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemeriksaanOnlineActivity extends AppCompatActivity {

    private ActivityDetailPemeriksaanOnlineBinding binding;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPemeriksaanOnlineBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dialog = new ProgressDialog(this);

        dialog.setMessage("Mengambil data pemeriksaan...");
        dialog.setIndeterminate(true);
        dialog.show();

        String id_pemeriksaan = getIntent().getStringExtra("id_pemeriksaan");

        Call<SinglePemeriksaanResponse> call = APIClient.getRetrofitInstance().getPemeriksaanByID(id_pemeriksaan);
        call.enqueue(new Callback<SinglePemeriksaanResponse>() {
            @Override
            public void onResponse(Call<SinglePemeriksaanResponse> call, Response<SinglePemeriksaanResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("PEMERIKSAAN", response.body().data.pemeriksaan.toString());
                    Pemeriksaan p = response.body().data.getPemeriksaan();
                    binding.tvOnlinePemeriksaanHasil.setText(p.getHasil_pemeriksaan());
                    binding.tvOnlinePemeriksaanStatus.setText(p.getStatusDetail());
                    dialog.dismiss();
                }
                Log.d("GET PEMERIKSAAN LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<SinglePemeriksaanResponse> call, Throwable t) {
            }
        });
    }
}