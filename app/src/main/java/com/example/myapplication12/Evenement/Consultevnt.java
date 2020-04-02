package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Listcours;

public class Consultevnt extends AppCompatActivity {

    Menu menuitem;
    private TextView t1, t2, t3, t4;
    private ImageView scolarete1, messages1, evenement1;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu,menu);

        menuitem=menu;
        MenuItem itm = menuitem.findItem(R.id.item4);
        //itm.setVisible(false);
        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item4:
                Intent in = new Intent(Consultevnt.this, Addcours.class);
                startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}
