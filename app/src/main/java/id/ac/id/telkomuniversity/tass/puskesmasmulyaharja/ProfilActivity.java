package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PemeriksaanListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.UserResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityProfilBinding;
import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    private ActivityProfilBinding binding;
    ProgressDialog dialog = null;
    private User pasien = null;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dialog = new ProgressDialog(this);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_key", Context.MODE_PRIVATE);
        id_user = sharedPref.getString("user_id", "1");

        getData(id_user);

        binding.buttonUploadKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this, KtpActivity.class);
                intent.putExtra("ktp", pasien.getKtp());
                intent.putExtra("id_pasien", id_user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData(id_user);
    }

    public void getData(String id_user) {
        dialog.setMessage("Mengambil data profil Anda...");
        dialog.setIndeterminate(true);
        dialog.show();

        Call<UserResponse> call = APIClient.getRetrofitInstance().getPasienByID(id_user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    pasien = response.body().data.getUser();
                    binding.formNama.setText(pasien.getNama());
                    binding.formAlamat.setText(pasien.getAlamat());
                    binding.formBeratBadan.setText(pasien.getBerat_badan()+"");
                    binding.formTinggiBadan.setText(pasien.getTinggi_badan()+"");
                    binding.formTglLahir.setText(pasien.getTgl_lahir());
                    binding.formNohp.setText(pasien.getNo_hp());
                    binding.spinnerJenisKelamin.setSelection(pasien.getJenis_kelamin()-1);
                    binding.spinnerGolDar.setSelection(((ArrayAdapter) binding.spinnerGolDar.getAdapter()).getPosition(pasien.getGol_darah()));
                    dialog.dismiss();
                }
                Log.d("GET PEMERIKSAAN LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
        
        binding.buttonUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Mengupdate profil Anda...");
                dialog.setIndeterminate(true);
                dialog.show();

                User user = new User(
                        binding.formNohp.getText().toString(),
                        binding.formNama.getText().toString(),
                        binding.formAlamat.getText().toString(),
                        Integer.parseInt(binding.formBeratBadan.getText().toString()),
                        Integer.parseInt(binding.formTinggiBadan.getText().toString()),
                        binding.spinnerGolDar.getSelectedItem().toString(),
                        binding.formTglLahir.getText().toString(),
                        binding.spinnerJenisKelamin.getSelectedItem().toString()
                );

                Call<APIResponse> call1 = APIClient.getRetrofitInstance().updateProfil(id_user, user);
                call1.enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        if(response.code() == 200) {
                            Toast.makeText(ProfilActivity.this, "Update profil berhasil", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}