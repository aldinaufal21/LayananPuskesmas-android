package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.SinglePemeriksaanResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityDetailPemeriksaanOfflineBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemeriksaanOfflineActivity extends AppCompatActivity {

    private ActivityDetailPemeriksaanOfflineBinding binding;
    ProgressDialog dialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPemeriksaanOfflineBinding.inflate(getLayoutInflater());
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
                    binding.tvOfflinePemeriksaanAntrian.setText(p.getAntrian().getAntrian());
                    binding.tvOfflinePemeriksaanTanggal.setText(p.getAntrian().getTanggal());
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