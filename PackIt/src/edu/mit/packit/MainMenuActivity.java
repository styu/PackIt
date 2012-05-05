package edu.mit.packit;

import java.util.List;

import edu.mit.packit.db.TripDetails;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class MainMenuActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenulayout);
        
        PackItActivity.datasource.open();
        List<String> trips = PackItActivity.datasource.getAllTripNames();
        setListAdapter(new ArrayAdapter<String>(this, R.layout.trip_item, trips));
        
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        Button continue_button = (Button) findViewById(R.id.continue_button);
        ImageView settings_button = (ImageView) findViewById(R.id.settings_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        
        // TODO add list of past trips
        
        continue_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				EditText trip_field = (EditText) ((Activity) v.getContext()).findViewById(R.id.trip_name);
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
