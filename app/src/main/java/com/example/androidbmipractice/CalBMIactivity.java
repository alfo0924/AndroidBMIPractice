package com.example.androidbmipractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalBMIactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cal_bmiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void CalBMI(View view) {
        EditText etwt = findViewById(R.id.etwt);
        EditText etht = findViewById(R.id.etht);

        double w = Double.parseDouble(etwt.getText().toString());
        double h = Double.parseDouble(etht.getText().toString());
        h = h / 100; // Convert height from cm to m
        double bmi = w / (h * h);

        Intent intent = new Intent();
        intent.putExtra("BMI", bmi);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}