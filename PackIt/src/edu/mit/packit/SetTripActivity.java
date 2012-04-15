package edu.mit.packit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
	        SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
	        
	        
	        //sets the text field to the previous trip info, if going back to the activity
	        if (!tripInfo.getString(SetTripActivity.DESTINATION, SetTripActivity.NONE).equals(SetTripActivity.NONE)) {
	        	EditText destination_field = (EditText) findViewById(R.id.destination_field);
	        	destination_field.setText(tripInfo.getString(SetTripActivity.DESTINATION, SetTripActivity.NONE));
	        }
	        
	        //pre-highlights the button if already selected previously
	        if (!tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE).equals(SetTripActivity.NONE)) {
	        	String transportation = tripInfo.getString(SetTripActivity.TRANSPORTATION, SetTripActivity.NONE);
	        	if (transportation.equals(SetTripActivity.WALKING)) {
	        		walkButton.setBackgroundColor(android.graphics.Color.BLUE);
	        	}
	        	else if (transportation.equals(SetTripActivity.CAR)) {
	        		carButton.setBackgroundColor(android.graphics.Color.BLUE);
	        	}
	        	else if (transportation.equals(SetTripActivity.PLANE)) {
	        		planeButton.setBackgroundColor(android.graphics.Color.BLUE);
	        	}
	        }
	        
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
					
					EditText destination_field = (EditText) findViewById(R.id.destination_field);
					String destination = destination_field.getText().toString();
					
					 SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
				        SharedPreferences.Editor editor = tripInfo.edit();
				        editor.putString(SetTripActivity.DESTINATION, destination);
				        editor.commit();
				        
					startActivity(intent);
				}
			});
	        
	        //the following listeners will change the button highlighting for the transportation buttons
	        walkButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
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
					SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
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
					SharedPreferences tripInfo = getSharedPreferences(SetTripActivity.TRIP_INFO, MODE_PRIVATE);
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
	 
}
