package com.example.myapplication12.Evenement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication12.R;

public class Consultevnt extends AppCompatActivity {

    private TextView t1, t2, t3, t4;
    private TextView nom2, date2, description2;
    private TextView nom1,nombreparticipants;
    private ImageView save, imageeven;
    private DatePicker date22;
    private TimePicker time22;
    private CheckBox interese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultevnt);
    }
}
