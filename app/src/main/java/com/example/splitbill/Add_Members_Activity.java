package com.example.splitbill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_Members_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
    }

    private ArrayList arrlst = new ArrayList();
    private ArrayList<EditText> et_lst = new ArrayList();
    private int id = 0;

    public void ADD_MEMBER_button_click(View view)
    {
        // if the EditText is empty then do not get new EditText
        if(et_lst.get(et_lst.size() - 1).getText().toString().trim()=="")
        {
            Toast.makeText(this, "Enter name in last EditText", Toast.LENGTH_SHORT).show();
        }

        else
        {
            addMembers();
        }
    }


    public void addMembers()
    {
        //Taking Linear Layout by id
        LinearLayout ll_main = findViewById(R.id.Linear_layout_add_edit_text);

        //Creating Frame
        FrameLayout fr = new FrameLayout(this);
        //layout params for frames
        FrameLayout.LayoutParams params_fr = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        fr.setLayoutParams(params_fr);
        //frames cannot be clicked
        fr.setClickable(false);

        //Creating edit text
        EditText et = new EditText(this);
        et.setGravity(Gravity.LEFT);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint("Member Name");
        //Adding EditText to et_lst
        et_lst.add(et);
        et.setId(id);
        id++;

        //Creating Remove Button
        Button btn = new Button(this);
        //setting button text
        btn.setText("X");
        //Setting remove button id as 1+id of corresponding Edittext in the same frame
        btn.setId(id);
        id++;
        //Setting background colour as red for button
        btn.setBackgroundColor(getResources().getColor(R.color.red));
        //Setting orientation to right
        btn.setGravity(Gravity.RIGHT);
        //setting layout params
        btn.setLayoutParams(new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        //Setting OnClickListner Function
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function to remove view

                //Taking the id of the remove button
                int id = v.getId();
                id = id -1 ;
                EditText et = findViewById(id);
                et_lst.remove(et);

                LinearLayout ll_main = findViewById(R.id.Linear_layout_add_edit_text);
                ll_main.removeView((View) ((View) v.getParent()).getParent());
                Toast.makeText(Add_Members_Activity.this, "Et_lst size: "+et_lst.size(), Toast.LENGTH_SHORT).show();


            }
        });

        //Creating Space
        Space sp = new Space(this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
        sp.getLayoutParams().height = 15;

        //Creating Space
        Space sp_ = new Space(this);
        sp_.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
        sp_.getLayoutParams().height = 15;

        /*Testing
        String test = et.getText().toString();
        TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText(test);
        */

        //Adding to frame
        fr.addView(et);
        fr.addView(btn);
        //Adding them to linear layout
        ll_main.addView(sp);
        ll_main.addView(fr);
        ll_main.addView(sp_);

        Toast.makeText(this, "EditView added Successfully!!!", Toast.LENGTH_SHORT).show();

    }

    public Boolean check()
    {
        for(int i=0;i<et_lst.size();i=i+1)
        {
            if(et_lst.get(i).getText().toString().trim().equals(""))
            {
                return false;
            }
        }
        return true;
    }

    public void DONE_CLICKED(View view) {
        if(et_lst.size()!=0 && check())
        {
            //Enter friends name in arrlst and return
            for(int i=0;i< et_lst.size();i++)
                arrlst.add(et_lst.get(i).getText().toString().trim());

            //Enter a row in main_table and store the id
            //run SQL queries to create friends table and activities table
        }

        else
        {
            Toast.makeText(this, "NAME NOT ENTERED SOMEWHERE!!!", Toast.LENGTH_SHORT).show();
        }
    }
}