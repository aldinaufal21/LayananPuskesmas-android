package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.UserResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    ProgressDialog dialog = null;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPref = getApplicationContext().getSharedPreferences("login_key", Context.MODE_PRIVATE);
        if(sharedPref.contains("user_id")) {
            moveToMain();
        }
        dialog = new ProgressDialog(this);

        binding.buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonLogin.getId()){
            String no_hp = binding.formNohp.getText().toString();
            String password = binding.formPassword.getText().toString();

            User user = new User(no_hp, password);

            Call<UserResponse> call = APIClient.getRetrofitInstance().login(user);
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(true);
            dialog.show();
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        sharedPref.edit().putString("user_id", ""+response.body().data.user.id).apply();
                        moveToMain();
                        finish();
                    }
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    dialog.dismiss();
                }
            });
        }
    }

    private void moveToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}