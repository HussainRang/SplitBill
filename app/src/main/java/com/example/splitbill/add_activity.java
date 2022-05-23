package com.example.splitbill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class add_activity extends AppCompatActivity {

    Intent activity_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        activity_add = getIntent();
        table_id = activity_add.getIntExtra("Table_id",0);
        get_edit();
    }

    private int table_id;
    private ArrayList friends_name;
    private ArrayList<EditText> arr_et;
    public DataBaseHandler dbh = new DataBaseHandler(this);


    EditText act_name = findViewById(R.id.editTextActivityName);

    public void get_edit()
    {
        friends_name.clear();
        arr_et.clear();
        arr_et = new ArrayList<EditText>();

        friends_name = dbh.get_friends_name(table_id);

        LinearLayout ll = findViewById(R.id.ll_add_activity_main);

        for(int i=0;i<friends_name.size();i++)
        {
            LinearLayout ll_hor = new LinearLayout(this);
            ll_hor.setOrientation(LinearLayout.HORIZONTAL);
            ll_hor.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            TextView tv_name = new TextView(this);
            tv_name.setText(""+friends_name.get(i));
            tv_name.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            ll.addView(tv_name);

            EditText et_paid = new EditText(this);
            et_paid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            et_paid.setText("0");
            et_paid.setInputType(InputType.TYPE_CLASS_NUMBER);
            ll.addView(et_paid);
            arr_et.add(et_paid);

            EditText et_spent = new EditText(this);
            et_spent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            et_spent.setText("0");
            et_spent.setInputType(InputType.TYPE_CLASS_NUMBER);
            ll.addView(et_paid);
            arr_et.add(et_spent);

            ll_hor.addView(tv_name);
            ll_hor.addView(et_paid);
            ll_hor.addView(et_spent);
            ll.addView(ll_hor);
        }
    }

    public Boolean check_true()
    {
        for(int i=0;i<arr_et.size();i++)
            if(arr_et.get(i).getText().toString().trim().equals(""))
                return false;

            if(act_name.getText().toString().trim().equals(""))
                return false;

            return true;
    }

    public void DONE_PRESSED(View view)
    {
        Boolean check = check_true();

        if(check)
        {
            //Update the friends table
            int i=0;
            int id=0;
            while(i<arr_et.size())
            {
                int paid = Integer.parseInt(arr_et.get(i).getText().toString());
                i=i+1;
                int spent = Integer.parseInt(arr_et.get(i).getText().toString());
                i=i+1;

                dbh.Update_friends_row(table_id,id+1,paid,spent, (String) friends_name.get(id));
                id = id+1;
            }

            //enter the activity in activity table
            dbh.add_activity_to_activity_table(table_id,friends_name,arr_et,act_name.getText().toString().trim());

            //send activity name(act_name) final id of activity table time and date
            int max_id = dbh.get_max_id_activity_table(table_id);
            Intent result_intent = new Intent();
            result_intent.putExtra("Activity_name",act_name.getText().toString().trim());
            result_intent.putExtra("Max_id",max_id);
            result_intent.putExtra("Date",dbh.getDate(table_id,max_id));
            result_intent.putExtra("Time",dbh.getTime(table_id,max_id));
            setResult(RESULT_OK,result_intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Enter correct enteries", Toast.LENGTH_SHORT).show();
        }
    }

}