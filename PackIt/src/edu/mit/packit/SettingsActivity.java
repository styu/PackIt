package edu.mit.packit;

import edu.mit.packit.db.TripDetails;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslayout);
        
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        
        TextView test_text = (TextView) findViewById(R.id.info);
        
        SharedPreferences prefs = getSharedPreferences("trip_name", MODE_PRIVATE);
        String trip_name = prefs.getString("name", "");
        
        PackItActivity.datasource.open();
        if (!trip_name.equals("")) {
        	 TripDetails trip = PackItActivity.datasource.getTrip(trip_name);
        	 test_text.setText(trip.getTripName() + " " + trip.getLocation());
        }
       
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
	}
}
