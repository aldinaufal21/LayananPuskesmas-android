package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonLogin.getId()){
            Log.i("LOGIN", "Button login clicked");
        }
    }
}