package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.KTP;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityKtpBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KtpActivity extends AppCompatActivity {
    private ActivityKtpBinding binding;
    private ImageView imageView;
    ProgressDialog dialog = null;

    EasyImage easyImage;
    
    File photo = null;

    private String id_pasien = null;
    private KTP ktp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKtpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        imageView = binding.formFotoKTP;

        dialog = new ProgressDialog(this);

        ktp = (KTP) getIntent().getSerializableExtra("ktp");
        id_pasien = getIntent().getStringExtra("id_pasien");

        if(ktp != null) {
            Glide.with(this).load(ktp.getFoto()).into(imageView);
            binding.formNik.setText(ktp.getNik());
        }
        easyImage = new EasyImage.Builder(KtpActivity.this)
                .setChooserTitle("Pilih foto KTP")
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setCopyImagesToPublicGalleryFolder(false)
                .setFolderName("Puskesmas")
                .allowMultiple(false)
                .build();

        binding.formFotoKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyImage.openCameraForImage(KtpActivity.this);
            }
        });

        binding.buttonUploadKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photo == null || binding.formNik.getText().toString().isEmpty()) {
                    Toast.makeText(KtpActivity.this, "Isikan data terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                KTP ktp = new KTP(binding.formNik.getText().toString());

                dialog.setMessage("Mengupload data KTP...");
                dialog.setIndeterminate(true);
                dialog.show();

                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), photo);
                RequestBody nik = RequestBody.create(MediaType.parse("multipart/form-data"), ktp.getNik());
                MultipartBody.Part body = MultipartBody.Part.createFormData("foto", photo.getName(), requestBody);

                Call<APIResponse> call = APIClient.getRetrofitInstance().uploadKTP(id_pasien, body, nik);
                call.enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            if(response.body().status == 200) {
                                Toast.makeText(KtpActivity.this, "Berhasil mengupload KTP", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        Log.d("UPLOAD KTP", response.raw().toString());
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(KtpActivity.this, "Gagal mengupload KTP", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                Uri uri = Uri.fromFile(imageFiles[0].getFile());
                photo = imageFiles[0].getFile();

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