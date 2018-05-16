package com.example.a16022970.p05_ndpsongs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    Button btnUpdate,btnCancel,btnDelete;
    EditText titleUpdate,singerUpdate,yearUpdate;
    RadioGroup rgUpdate;
    TextView tvId;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        titleUpdate = (EditText)findViewById(R.id.etTitleUpdate);
        yearUpdate = (EditText)findViewById(R.id.etYearUpdate);
        singerUpdate = (EditText)findViewById(R.id.etSingersUpdate);

        tvId = (TextView)findViewById(R.id.tvId);

        rgUpdate = (RadioGroup)findViewById(R.id.rgUpdate) ;

        RadioButton rb1 = (RadioButton)findViewById(R.id.rb1);
        RadioButton rb2 = (RadioButton)findViewById(R.id.rb2);
        RadioButton rb3 = (RadioButton)findViewById(R.id.rb3);
        RadioButton rb4 = (RadioButton)findViewById(R.id.rb4);
        RadioButton rb5 = (RadioButton)findViewById(R.id.rb5);



        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        yearUpdate.setText(String.valueOf(data.getYear()));
        titleUpdate.setText(data.getTitle());
        singerUpdate.setText(data.getSingers());

        tvId.setText("ID: " + String.valueOf(data.get_id()));

        int updateStars = data.getStars();

        if (updateStars == 1){
            rb1.setChecked(true);
        }
        else if (updateStars ==2){
            rb2.setChecked(true);
        }
        else if (updateStars ==3){
            rb3.setChecked(true);
        }
        else if (updateStars ==4){
            rb4.setChecked(true);
        }
        else{
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setSingers(singerUpdate.getText().toString());
                data.setTitle(titleUpdate.getText().toString());
                data.setYear(Integer.parseInt(yearUpdate.getText().toString()));

                int selectedButtonId = rgUpdate.getCheckedRadioButtonId();

                RadioButton rbstars = (RadioButton)findViewById(selectedButtonId);
                String rbText = rbstars.getText().toString();
                int stars;
                if (rbText.equals("1")){
                    stars = 1;
                }
                else if(rbText.equals("2")){
                    stars = 2;
                }
                else if(rbText.equals("3")){
                    stars = 3;
                }
                else if(rbText.equals("4")){
                    stars = 4;
                }
                else{
                    stars = 5;
                }
                data.setStars(stars);
                dbh.updateNote(data);
                dbh.close();
                Toast.makeText(ModifyActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ModifyActivity.this,
                        ShowActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteNote(data.get_id());
                dbh.close();
                Intent i = new Intent(ModifyActivity.this,
                        ShowActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
