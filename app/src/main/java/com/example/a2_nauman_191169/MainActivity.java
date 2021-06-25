package com.example.a2_nauman_191169;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText_id, editText_name, editText_city, editText_age;
    Button button_save, button_view;
    TextView textView_viewData;
    SqliteDB_helper sqliteDB_helper;
    String allRecords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_id = findViewById(R.id.et_id);
        editText_name = findViewById(R.id.et_name);
        editText_city = findViewById(R.id.et_city);
        editText_age = findViewById(R.id.et_age);

        button_save = findViewById(R.id.btn_save);
        button_view = findViewById(R.id.btn_view);

        textView_viewData = findViewById(R.id.tv_viewData);

        sqliteDB_helper = new SqliteDB_helper(this);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText_id.getText().toString();
                String name = editText_name.getText().toString();
                String city = editText_city.getText().toString();
                String age = editText_age.getText().toString();

                boolean checkSaved = sqliteDB_helper.insertStudentData(id, name, city, age);
                if (checkSaved == true) {
                    Toast.makeText(MainActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Not Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDataOnTextView();
            }
        });


    }


    public void viewDataOnTextView(){
        allRecords = "";
        Cursor eachRecord_cursor = sqliteDB_helper.getStudentData();
        if(eachRecord_cursor.getCount() == 0){
            Toast.makeText(this, "No Record Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        while(eachRecord_cursor.moveToNext()){
            String eachRecord;
            eachRecord = "ID: " + eachRecord_cursor.getString(0) + "\n";
            eachRecord = eachRecord + "NAME: " + eachRecord_cursor.getString(1) + "\n";
            eachRecord = eachRecord + "CITY: " + eachRecord_cursor.getString(2) + "\n";
            eachRecord = eachRecord + "AGE: " + eachRecord_cursor.getString(3) + "\n";
            eachRecord = eachRecord + "-------------------------------------------------------\n";
            allRecords = allRecords + eachRecord;
        }
        textView_viewData.setText(allRecords);
    }

}