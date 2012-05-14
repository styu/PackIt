package edu.mit.packit;

import java.util.List;

import edu.mit.packit.db.TripInfoDataSource;
import edu.mit.packit.db.TripSQLiteHelper;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainMenuActivity extends ListActivity {

	private final static String TAG = "MainMenuActivity";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenulayout);
        TripInfoDataSource.set(getApplicationContext());
        PackItActivity.datasource.open();
        final List<String> trips = PackItActivity.datasource.getAllTripNames();

        Log.i(TAG, trips.toString());
        setListAdapter(new ArrayAdapter<String>(this, R.layout.trip_item, trips));
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg2)
            { 
        		String new_trip_name = trips.get(position);
        		SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        prefs_editor.putString(TripSQLiteHelper.TRIP_NAME, new_trip_name);
		        prefs_editor.commit();
		        
		        Intent intent = new Intent(arg1.getContext(), PackActivity.class);
		        startActivity(intent);
            }
        	
		});
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
