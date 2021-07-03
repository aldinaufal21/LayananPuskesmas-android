package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityListPemeriksaanBinding;

public class ListPemeriksaan extends AppCompatActivity {

    private ActivityListPemeriksaanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPemeriksaanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}