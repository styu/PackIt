package edu.mit.packit.temp;

import edu.mit.packit.R;
import edu.mit.packit.db.TripInfoDataSource;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Main activity, contains a list of past trips and allows users to add new trips.
 * 
 * @author Steph
 *
 */
public class PackItActivity extends Activity {
    /** Called when the activity is first created. */
	
	public static final String ACTIVITY_NAME = "activity";
	public static final String PACKIT_ACTIVITY = "packitactivity";
	public static TripInfoDataSource datasource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       // TripInfoDataSource.set(this);
        Button continueButton = (Button) findViewById(R.id.continue_button);
        
        continueButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				EditText text_field = (EditText) findViewById(R.id.trip_name);
				String trip_name = text_field.getText().toString();
				
				if (trip_name != null && !trip_name.equals("")) {
					Intent intent = new Intent(v.getContext(),
							SetTripActivity.class);
					
					intent.putExtra(PackItActivity.ACTIVITY_NAME, PackItActivity.PACKIT_ACTIVITY);
					SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
					SharedPreferences.Editor editor = tripInfo.edit();
					editor.putString(SetTripActivity.TRIP_NAME, trip_name);
					editor.commit();
					startActivity(intent);
				}
			}
		});
        
        
    }
}