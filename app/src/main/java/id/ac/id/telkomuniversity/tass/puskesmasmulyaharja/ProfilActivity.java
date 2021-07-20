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
import android.widget.ImageView;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter.PemeriksaanListAdapter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
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
                    binding.formEmail.setText(pasien.getEmail());
                    dialog.dismiss();
                }
                Log.d("GET PEMERIKSAAN LIST", response.raw().toString());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
}