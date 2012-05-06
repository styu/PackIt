package edu.mit.packit;

import java.util.List;

import edu.mit.packit.db.TripDetails;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainMenuActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenulayout);
        
        PackItActivity.datasource.open();
        List<String> trips = PackItActivity.datasource.getAllTripNames();
        RelativeLayout trip_list = (RelativeLayout) findViewById(R.id.trip_list);
		LayoutInflater vi = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int cur_id = 0;
        for (String trip_name : trips) {
        	LinearLayout cur_trip = (LinearLayout) vi.inflate(R.layout.trip_item, null);
        	TextView trip_text = (TextView) cur_trip.findViewById(R.id.trip_name);
        	trip_text.setText(trip_name);
        	cur_trip.setId(cur_id);
        	RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        	if (cur_id != 0) {
                q.addRule(RelativeLayout.BELOW, cur_id - 1);
        	}
        	cur_trip.setLayoutParams(q);
        	cur_id++;
        	trip_list.addView(cur_trip);
        }
//        setListAdapter(new ArrayAdapter<String>(this, R.layout.trip_item, trips));
//        
//        ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
        
        Button continue_button = (Button) findViewById(R.id.continue_button);
        ImageView settings_button = (ImageView) findViewById(R.id.settings_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        
        // TODO add list of past trips
        
        continue_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				EditText trip_field = (EditText) ((Activity) v.getContext()).findViewById(R.id.trip_name_field);
				String trip_name = trip_field.getText().toString();
				if (trip_name != null && trip_name.length() > 0) {
					Intent intent = new Intent(v.getContext(),
							SetTripInfoActivity.class);
					intent.putExtra(Info.TRIP_NAME, trip_name);
					startActivity(intent);
				}
			}
		});
        settings_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						SettingsActivity.class);
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
	
	protected void onResume() {
		PackItActivity.datasource.open();
		super.onResume();
	}
	
	protected void onPause() {
		PackItActivity.datasource.close();
		super.onPause();
	}
}
