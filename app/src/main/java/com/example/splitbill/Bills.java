package com.example.splitbill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bills extends AppCompatActivity {


    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        in=getIntent();
        id = in.getIntExtra("ID",0);


    }

    private int id;

    public void Status_pressed_Bills(View v)
    {
        Intent pass = new Intent(this,StatusActivity.class);
        pass.putExtra("ID",id);
        startActivity(pass);
    }

    public void Activity_pressed_Bills(View v)
    {
        Intent pass = new Intent(this,Activities_page.class);
        pass.putExtra("ID",id);
        startActivity(pass);
    }
}

