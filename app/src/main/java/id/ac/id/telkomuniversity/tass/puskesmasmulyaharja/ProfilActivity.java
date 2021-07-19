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
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    EasyImage easyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dialog = new ProgressDialog(this);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("login_key", Context.MODE_PRIVATE);
        String id_user = sharedPref.getString("user_id", "1");

        dialog.setMessage("Mengambil data profil Anda...");
        dialog.setIndeterminate(true);
        dialog.show();

        Call<UserResponse> call = APIClient.getRetrofitInstance().getPasienByID(id_user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User pasien = response.body().data.getUser();
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

        imageView = binding.formFotoKTP;
        easyImage = new EasyImage.Builder(ProfilActivity.this)
                .setChooserTitle("Pilih foto KTP")
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setCopyImagesToPublicGalleryFolder(false)
                .setFolderName("Puskesmas")
                .allowMultiple(false)
                .build();

        binding.buttonPilihFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyImage.openCameraForImage(ProfilActivity.this);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                Uri uri = Uri.fromFile(imageFiles[0].getFile());

                imageView.setImageURI(uri);
            }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                //Some error handling
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                //Not necessary to remove any files manually anymore
            }
        });
    }
}