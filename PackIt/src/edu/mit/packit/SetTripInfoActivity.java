package edu.mit.packit;

import java.util.HashMap;

import edu.mit.packit.db.TripSQLiteHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SetTripInfoActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackItActivity.datasource.open();
        setContentView(R.layout.settripinfolayout);
        
        PackItActivity.datasource.open();
        
        final String trip_name = getIntent().getStringExtra(Info.TRIP_NAME);
        SharedPreferences prefs = getSharedPreferences("trip_name", MODE_PRIVATE);
        SharedPreferences.Editor prefs_editor = prefs.edit();
        prefs_editor.putString("name", trip_name);
        prefs_editor.commit();
        Button go_button = (Button)findViewById(R.id.go_button);
        Button back_button = (Button) findViewById(R.id.back_button);
        go_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				HashMap<String, String> details = new HashMap<String, String>();
				details.put(TripSQLiteHelper.TRIP_NAME, trip_name);
				
				EditText destination_field = (EditText) ((Activity) v.getContext()).findViewById(R.id.destination_field);
				details.put(TripSQLiteHelper.LOCATION, destination_field.getText().toString());
				
				// TODO add calendar widget to get date
				
				EditText from_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.from_date);
				details.put(TripSQLiteHelper.FROM_DATE, from_date.getText().toString());
				
				// TODO add calendar widget to get date
				
				EditText to_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.to_date);
				details.put(TripSQLiteHelper.TO_DATE, to_date.getText().toString());
				
				// TODO add actual values
				details.put(TripSQLiteHelper.GENDER, "male");
				details.put(TripSQLiteHelper.TRANSPORTATION, "car");
				PackItActivity.datasource.createTrip(details);
				
				Intent intent = new Intent(v.getContext(),
							PackActivity.class);
				startActivity(intent);
			}
		});
        back_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						MainMenuActivity.class);
				startActivity(intent);
				finish();
			}
		});
        
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        ImageView settings_button = (ImageView) findViewById(R.id.settings_button);
        
        home_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						MainMenuActivity.class);
				startActivity(intent);
			}
		});
        pack_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						PackActivity.class);
				startActivity(intent);
			}
		});
        trip_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						TripInfoActivity.class);
				startActivity(intent);
			}
		});
        settings_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						SettingsActivity.class);
				startActivity(intent);
			}
		});
	}
	
	protected void onResume() {
		PackItActivity.datasource.open();
		super.onResume();
	}
	
	protected void onPause() {
		PackItActivity.datasource.close();
		super.onPause();
	}
}
