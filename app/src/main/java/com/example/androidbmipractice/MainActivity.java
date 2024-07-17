package com.example.androidbmipractice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private double bmi;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private TextView tv_Result;
    private TextView tv_thin_value;
    private TextView tv_thin_description;
    private TextView tv_normal_value;
    private TextView tv_normal_description;
    private TextView tv_overweight_value;
    private TextView tv_overweight_description;
    private TextView tv_obese_value;
    private TextView tv_obese_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result != null && result.getResultCode() == Activity.RESULT_OK) {
                            bmi = result.getData().getDoubleExtra("BMI", -1);
                            updateUI();
                        }
                    }
                }
        );
    }

    private void updateUI() {
        tv_Result = findViewById(R.id.Result);
        tv_thin_value = findViewById(R.id.thin_value);
        tv_thin_description = findViewById(R.id.thin_description);
        tv_normal_value = findViewById(R.id.normal_value);
        tv_normal_description = findViewById(R.id.normal_description);
        tv_overweight_value = findViewById(R.id.overweight_value);
        tv_overweight_description = findViewById(R.id.overweight_description);
        tv_obese_value = findViewById(R.id.obese_value);
        tv_obese_description = findViewById(R.id.obese_description);

        tv_Result.setText(String.valueOf(bmi));

        if (bmi < 18.5) {
            tv_thin_value.setVisibility(View.VISIBLE);
            tv_thin_value.setTextColor(Color.parseColor("#FF0000"));
            tv_thin_description.setVisibility(View.VISIBLE);
            tv_thin_description.setTextColor(Color.parseColor("#FF0000"));
            tv_normal_value.setVisibility(View.GONE);
            tv_normal_description.setVisibility(View.GONE);
            tv_overweight_value.setVisibility(View.GONE);
            tv_overweight_description.setVisibility(View.GONE);
            tv_obese_value.setVisibility(View.GONE);
            tv_obese_description.setVisibility(View.GONE);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            tv_thin_value.setVisibility(View.GONE);
            tv_thin_description.setVisibility(View.GONE);
            tv_normal_value.setVisibility(View.VISIBLE);
            tv_normal_value.setTextColor(Color.parseColor("#00FF00"));
            tv_normal_description.setVisibility(View.VISIBLE);
            tv_normal_description.setTextColor(Color.parseColor("#00FF00"));
            tv_overweight_value.setVisibility(View.GONE);
            tv_overweight_description.setVisibility(View.GONE);
            tv_obese_value.setVisibility(View.GONE);
            tv_obese_description.setVisibility(View.GONE);
        } else if (bmi >= 25 && bmi <= 29.9) {
            tv_thin_value.setVisibility(View.GONE);
            tv_thin_description.setVisibility(View.GONE);
            tv_normal_value.setVisibility(View.GONE);
            tv_normal_description.setVisibility(View.GONE);
            tv_overweight_value.setVisibility(View.VISIBLE);
            tv_overweight_value.setTextColor(Color.parseColor("#FF0000"));
            tv_overweight_description.setVisibility(View.VISIBLE);
            tv_overweight_description.setTextColor(Color.parseColor("#FF0000"));
            tv_obese_value.setVisibility(View.GONE);
            tv_obese_description.setVisibility(View.GONE);
        } else if (bmi >= 30) {
            tv_thin_value.setVisibility(View.GONE);
            tv_thin_description.setVisibility(View.GONE);
            tv_normal_value.setVisibility(View.GONE);
            tv_normal_description.setVisibility(View.GONE);
            tv_overweight_value.setVisibility(View.GONE);
            tv_overweight_description.setVisibility(View.GONE);
            tv_obese_value.setVisibility(View.VISIBLE);
            tv_obese_value.setTextColor(Color.parseColor("#FF0000"));
            tv_obese_description.setVisibility(View.VISIBLE);
            tv_obese_description.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void GotoBMIcalculate(View view) {
        Intent intent = new Intent(this, CalBMIactivity.class);
        intentActivityResultLauncher.launch(intent);
    }
}