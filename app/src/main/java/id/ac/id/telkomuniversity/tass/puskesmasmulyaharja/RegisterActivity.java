package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.User;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Responses.APIResponse;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Service.APIClient;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityLoginBinding;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.databinding.ActivityRegisterBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private User user;
    private String nama, alamat, gol_darah, no_hp, email, password;
    private int jenis_kelamin, berat_badan, tinggi_badan;
    ProgressDialog dialog = null;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dialog = new ProgressDialog(this);

        Spinner spinner = binding.spinnerJenisKelamin;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        binding.buttonDaftar.setOnClickListener(this);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        binding.formTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonDaftar.getId()) {
            user = new User(
                    binding.formEmail.getText().toString(),
                    binding.formNohp.getText().toString(),
                    binding.formPassword.getText().toString(),
                    binding.formNama.getText().toString(),
                    binding.formAlamat.getText().toString(),
                    Integer.parseInt(binding.formBeratBadan.getText().toString()),
                    Integer.parseInt(binding.formTinggiBadan.getText().toString()),
                    binding.spinnerGolDar.getSelectedItem().toString(),
                    binding.formTglLahir.getText().toString(),
                    binding.spinnerJenisKelamin.getSelectedItem().toString()
            );

            Log.d("USER", user.toString());

            Call<APIResponse> call = APIClient.getRetrofitInstance().register(user);
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(true);
            dialog.show();
            call.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    dialog.dismiss();
                    Log.d("RESPONSE", response.toString());
                    if(response.body().status == 200){
                        Toast toast = Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    dialog.dismiss();
                }
            });
        }
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.formTglLahir.setText(sdf.format(myCalendar.getTime()));
    }
}