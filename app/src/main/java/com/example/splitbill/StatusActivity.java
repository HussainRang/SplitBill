package com.example.splitbill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {


    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        in = getIntent();
        id = in.getIntExtra("ID",0);



        create_table();
    }

    public DataBaseHandler dbh = new DataBaseHandler(this);
    private int id;

    public void Activity_pressed_Status(View v)
    {
        Intent pass = new Intent(this,Activities_page.class);
        pass.putExtra("ID",id);
        startActivity(pass);
    }

    public void Bills_pressed_Status(View v)
    {
        Intent pass = new Intent(this,Bills.class);
        pass.putExtra("ID",id);
        startActivity(pass);
    }

    public void create_table()
    {
        TableLayout tbv = findViewById(R.id.table_add_rows);

        ArrayList friends_details;
        friends_details = dbh.get_friends_details(id);

        int i=0;
        while(i<friends_details.size())
        {
            TableRow tbr = new TableRow(this);

            TextView name = new TextView(this);
            name.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            name.setText(""+friends_details.get(i));
            i=i+1;
            name.setTextSize(10);
            tbr.addView(name);

            TextView paid = new TextView(this);
            paid.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            paid.setText(""+friends_details.get(i));
            i=i+1;
            paid.setTextSize(10);
            tbr.addView(paid);

            TextView spent = new TextView(this);
            spent.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            spent.setText(""+friends_details.get(i));
            i=i+1;
            spent.setTextSize(10);
            tbr.addView(paid);
        }

    }
}