package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PoliSpinnerAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Poli;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.PoliResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityPemeriksaanBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemeriksaanActivity extends AppCompatActivity {

    private ActivityPemeriksaanBinding binding;
    private Poli poli;
    private String keluhan;
    private PoliSpinnerAdapter adapter;
    private Spinner spinner;
    ProgressDialog dialog = null;
    private ArrayList<Poli> polis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPemeriksaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        spinner = binding.spinnerPoli;
        dialog = new ProgressDialog(this);

        Call<PoliResponse> call = APIClient.getRetrofitInstance().getPoli();
        call.enqueue(new Callback<PoliResponse>() {
            @Override
            public void onResponse(Call<PoliResponse> call, Response<PoliResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("PEMERIKSAAN", response.body().data.polis.toString());
                    polis = response.body().data.polis;
                    adapter = new PoliSpinnerAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, polis);
                    spinner.setAdapter(adapter);
                }
                Log.d("GET POLI RESULT", response.raw().toString());
            }

            @Override
            public void onFailure(Call<PoliResponse> call, Throwable t) {
            }
        });

        binding.buttonPeriksa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(true);
//                if(binding.formKeluhan.getText().toString().isEmpty()) {
                    dialog.show();
                    poli = (Poli) spinner.getSelectedItem();
                    keluhan = binding.formKeluhan.getText().toString();
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_key", Context.MODE_PRIVATE);

                    String id_user = sharedPref.getString("user_id", "1");

                    Pemeriksaan pemeriksaan = new Pemeriksaan(poli.getId(), keluhan, Integer.parseInt(id_user));

                    Call<APIResponse> call = APIClient.getRetrofitInstance().addPemeriksaan(pemeriksaan);
                    call.enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("PEMERIKSAAN", response.body().message);
                                Toast.makeText(PemeriksaanActivity.this, "Berhasil membuat pemeriksaan", Toast.LENGTH_SHORT).show();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        PemeriksaanActivity.this.finish();
                                    }
                                }, Toast.LENGTH_LONG);

                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });
//                }
            }
        });
    }
}