package com.example.fpiano;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fpiano.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen safe
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_home);

        Button btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(v -> {
            startActivity(
                    new Intent(HomeActivity.this, MainActivity.class)
            );
        });
    }
}
