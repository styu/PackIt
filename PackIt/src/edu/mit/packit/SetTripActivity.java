package edu.mit.packit;
//
//import java.util.HashMap;
//
//import edu.mit.packit.db.TripDetails;
//import edu.mit.packit.db.TripInfoDataSource;
//import edu.mit.packit.db.TripSQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity that edits and saves the trip information
 * @author Steph
 *
 */
public class SetTripActivity extends Activity {

	public static final String TRIP_INFO = "tripinfo";
	public static final String TRIP_NAME = "tripname";
	
	
	public static final String TRANSPORTATION = "transportation";
	public static final String NONE = "";
	public static final String WALKING = "walking";
	public static final String CAR = "car";
	public static final String PLANE = "plane";
	public static final String DESTINATION = "destintation";
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.settriplayout);
	        
	        Button backButton = (Button) findViewById(R.id.back_button);
	        Button goButton = (Button) findViewById(R.id.go_button);
	        //transportation buttons
	        final Button walkButton = (Button) findViewById(R.id.walking_button);
	        final Button carButton = (Button) findViewById(R.id.car_button);
	        final Button planeButton = (Button) findViewById(R.id.plane_button);
	        
	        //get user preferences (if any)
	        //temporarily set to have only one trip
//	        SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
//	        final String trip_name = tripInfo.getString(SetTripActivity.TRIP_NAME, "");
	        
	        
	        //disabled for now
	        if (getIntent().hasExtra(PackItActivity.ACTIVITY_NAME) && getIntent().getStringExtra(PackItActivity.ACTIVITY_NAME).equals(PackItActivity.PACKIT_ACTIVITY)) {
	        	//Log.i("SetTripActivity", "HERE");
	        	//PackItActivity.datasource.deleteAllTrips();
	        }
	        
	        else {
	        	//TripDetails details = datasource.getTrip(trip_name);
	        	//TripDetails details = PackItActivity.datasource.getAllTrips();
	        	//if (details != null) {
	        		EditText destination_field = (EditText) findViewById(R.id.destination_field);
		        //	destination_field.setText(details.getLocation());
		        	destination_field.setText("Europe");
		        //	String transportation = details.getTransportation();
//		        	if (transportation.equals(SetTripActivity.WALKING)) {
//		        		walkButton.setBackgroundColor(android.graphics.Color.BLUE);
//		        	}
//		        	else if (transportation.equals(SetTripActivity.CAR)) {
		        		carButton.setBackgroundColor(android.graphics.Color.BLUE);
//		        	}
//		        	else if (transportation.equals(SetTripActivity.PLANE)) {
//		        		planeButton.setBackgroundColor(android.graphics.Color.BLUE);
		        //	}
		        	
	        	}
//	        	else {
//	        		Log.i("SetTripActivity", "Oh fuu");
//	        		
//	        	}
//	        }
	        backButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),
							PackItActivity.class);
					startActivity(intent);
				}
			});
	        
	        goButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),
							TripActivity.class);
					
					//set initial data...for prototyping purposes
					 if (getIntent().hasExtra(PackItActivity.ACTIVITY_NAME) && getIntent().getStringExtra(PackItActivity.ACTIVITY_NAME).equals(PackItActivity.PACKIT_ACTIVITY)) {
				        	setItems();
				     }
//					EditText destination_field = (EditText) findViewById(R.id.destination_field);
//					String destination = destination_field.getText().toString();
//					SharedPreferences tripInfo = getPreferences(MODE_PRIVATE);
//					String transportation = tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE);
//				    
//					HashMap<String, String> details = new HashMap<String, String>();
//					details.put(TripSQLiteHelper.TRIP_NAME, trip_name);
//					details.put(TripSQLiteHelper.LOCATION, destination);
//					details.put(TripSQLiteHelper.TRANSPORTATION, transportation);
//					details.put(TripSQLiteHelper.FROM_DATE, "");
//					details.put(TripSQLiteHelper.TO_DATE, "");
//					details.put(TripSQLiteHelper.GENDER, "");
//					
//					PackItActivity.datasource.createTrip(details);
//					
					startActivity(intent);
				}
			});
	        
	        //the following listeners will change the button highlighting for the transportation buttons
	        walkButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences tripInfo = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = tripInfo.edit();
					if (tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE).equals(SetTripActivity.WALKING)) {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE);
						walkButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					else {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.WALKING);
						walkButton.setBackgroundColor(android.graphics.Color.BLUE);
						carButton.setBackgroundColor(android.graphics.Color.LTGRAY);
						planeButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					editor.commit();
				}
			});
	        
	        carButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences tripInfo = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = tripInfo.edit();
					if (tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE).equals(SetTripActivity.CAR)) {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE);
						carButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					else {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.CAR);
						carButton.setBackgroundColor(android.graphics.Color.BLUE);
						walkButton.setBackgroundColor(android.graphics.Color.LTGRAY);
						planeButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					editor.commit();
				}
			});
	        
	        planeButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences tripInfo = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = tripInfo.edit();
					if (tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE).equals(SetTripActivity.PLANE)) {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE);
						planeButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					else {
						editor.putString(SetTripActivity.TRANSPORTATION, SetTripActivity.PLANE);
						planeButton.setBackgroundColor(android.graphics.Color.BLUE);
						carButton.setBackgroundColor(android.graphics.Color.LTGRAY);
						walkButton.setBackgroundColor(android.graphics.Color.LTGRAY);
					}
					editor.commit();
				}
			});
	        
	    }
	 /**
	  * Temp method to fake the data for the packing view
	  */
	 private void setItems() {
		SharedPreferences packingInfo = getSharedPreferences(Items.PACKING_INFO, MODE_PRIVATE);
     	SharedPreferences.Editor editor = packingInfo.edit();
     	
     	editor.putInt(Items.LONG_SHIRT_TOBRING, 4);
     	editor.putInt(Items.LONG_SHIRT_PACKED, 0);
     	editor.putBoolean(Items.LONG_SHIRT, true);
     	
     	editor.putInt(Items.SHORT_SHIRT_TOBRING, 0);
     	editor.putInt(Items.SHORT_SHIRT_PACKED, 0);
     	editor.putBoolean(Items.SHORT_SHIRT, true);
     	
     	editor.putInt(Items.HOODY_TOBRING, 2);
     	editor.putInt(Items.HOODY_PACKED, 0);
     	editor.putBoolean(Items.HOODY, true);
     	
     	editor.putInt(Items.JEANS_TOBRING, 3);
     	editor.putInt(Items.JEANS_PACKED, 0);
     	editor.putBoolean(Items.JEANS, true);
     	
     	editor.putInt(Items.TUX_TOBRING, 1);
     	editor.putInt(Items.TUX_PACKED, 0);
     	editor.putBoolean(Items.TUX, true);
     	
     	editor.putInt(Items.TIE_TOBRING, 1);
     	editor.putInt(Items.TIE_PACKED, 0);
     	editor.putBoolean(Items.TIE, true);
     	
     	editor.putInt(Items.SCARVES_TOBRING, 2);
     	editor.putInt(Items.SCARVES_PACKED, 0);
     	editor.putBoolean(Items.SCARVES, true);
     	
     	editor.putInt(Items.MITTENS_TOBRING, 2);
     	editor.putInt(Items.MITTENS_PACKED, 0);
     	editor.putBoolean(Items.MITTENS, true);
     	
     	editor.putInt(Items.BOXERS_TOBRING, 6);
     	editor.putInt(Items.BOXERS_PACKED, 0);
     	editor.putBoolean(Items.BOXERS, true);
     	
     	editor.putBoolean(Items.LAPTOP, true);
     	editor.putInt(Items.LAPTOP_TOBRING, 1);
     	editor.putInt(Items.LAPTOP_PACKED, 0);
     	
     	editor.putInt(Items.BAGPIPES_TOBRING, 0);
     	editor.putInt(Items.BAGPIPES_PACKED, 0);
     	editor.putBoolean(Items.BAGPIPES, false);
     	
     	editor.commit();
	 }
}
