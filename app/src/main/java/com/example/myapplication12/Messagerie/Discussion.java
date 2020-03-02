package com.example.myapplication12.Messagerie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication12.R;

public class Discussion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().hide();
    }
}
