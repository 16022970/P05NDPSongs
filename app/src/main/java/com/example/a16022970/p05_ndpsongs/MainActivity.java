package com.example.a16022970.p05_ndpsongs;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.AbstractSequentialList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert;
    Button btnShow;

    EditText etTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShow = (Button) findViewById(R.id.btnShow);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);

        rg = (RadioGroup) findViewById(R.id.rg);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();

                int stars;
                //selected radiobutton from radiogroup

                int selectedradio = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedradio);

                if (rb.equals("0")) {
                    stars = 1;
                } else if (rb.equals("1")) {
                    stars = 2;

                } else if (rb.equals("2")) {
                    stars = 3;

                } else if (rb.equals("3")) {
                    stars = 4;

                } else {
                    stars = 5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title, singers, Integer.parseInt(year), stars);
                dbh.close();
                if (row_affected != -1) {

                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ShowActivity.class);
                startActivity(i);
            }
        });
    }
}
