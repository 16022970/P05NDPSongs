package com.example.a16022970.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RevisionNotesArrayAdapter extends ArrayAdapter<Note> {
	Context context;
	ArrayList<Note> notes;
	int resource;
	TextView tvYear;
	TextView tvTitle;
	TextView tvSinger;
	ImageView iv1, iv2, iv3, iv4, iv5;

	public RevisionNotesArrayAdapter(Context context, int resource, ArrayList<Note> notes) {
		super(context, resource, notes);
		this.context = context;
		this.notes = notes;
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(resource, parent, false);

		//Match the UI components with Java variables
		tvYear = (TextView)rowView.findViewById(R.id.tvYear);
		tvTitle = (TextView)rowView.findViewById(R.id.tvTitle);
		tvSinger = (TextView)rowView.findViewById(R.id.tvSinger);

		iv1 = (ImageView)rowView.findViewById(R.id.iv1);
		iv2 = (ImageView)rowView.findViewById(R.id.iv2);
		iv3 = (ImageView)rowView.findViewById(R.id.iv3);
		iv4 = (ImageView)rowView.findViewById(R.id.iv4);
		iv5 = (ImageView)rowView.findViewById(R.id.iv5);

		Note note = notes.get(position);

		tvYear.setText(String.valueOf(note.getYear()));
		tvTitle.setText(note.getTitle());
		tvSinger.setText(note.getSingers());
        int stars;

		stars = note.getStars();


		//Check if the property for starts >= 5, if so, "light" up the stars
        if (stars == 1) {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (stars == 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);

        } else if (stars == 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (stars == 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else{
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
		return rowView;
	}



}
