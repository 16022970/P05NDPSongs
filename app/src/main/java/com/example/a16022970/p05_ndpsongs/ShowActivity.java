package com.example.a16022970.p05_ndpsongs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Note> al;
    ArrayAdapter<Note> aa;
    DBHelper db;
    Button btnShow5stars;
    ArrayList<Note> starfive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = (ListView) findViewById(R.id.lv);
        btnShow5stars = (Button)findViewById(R.id.btnShow5Stars);

        db = new DBHelper(this);
        db.getWritableDatabase();
        al = db.getAllNotes();
        db.close();

        aa = new RevisionNotesArrayAdapter(ShowActivity.this, R.layout.row, al);
        aa.setNotifyOnChange(true);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ShowActivity.this,ModifyActivity.class);
                Note notes = al.get(position);
                int id = notes.get_id();
                String title = notes.getTitle();
                String singer = notes.getSingers();
                int year = notes.getYear();
                int stars = notes.getStars();
                Note target = new Note(id, title,singer,year,stars);
                i.putExtra("data", target);
                startActivityForResult(i, 9);
            }
        });

        btnShow5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starfive = db.get5stars(5);
                aa = new RevisionNotesArrayAdapter(ShowActivity.this, R.layout.row, starfive);
                aa.setNotifyOnChange(true);
                lv.setAdapter(aa);
            }
        });


    }


    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            lv.performClick();
        }
    }
}
