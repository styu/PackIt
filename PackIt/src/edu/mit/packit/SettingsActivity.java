package edu.mit.packit;

import java.util.HashMap;
import java.util.List;

import edu.mit.packit.db.TripDetails;
import edu.mit.packit.db.TripSQLiteHelper;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private static final String TAG = "SettingsActivity";
	
	private EditText duration_from; 
	private EditText duration_to; 
    private int mFromYear;
    private int mFromMonth;
    private int mFromDay;
    
    private int mToYear;
    private int mToMonth;
    private int mToDay;

    private int mYear;
    private int mMonth;
    private int mDay;
    
    static final int DATE_DIALOG_ID_FROM = 0;
    static final int DATE_DIALOG_ID_TO = 1;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslayout);
        
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        
        final Button walk_button = (Button) findViewById(R.id.walking_button);
        final Button car_button = (Button) findViewById(R.id.car_button);
        final Button fly_button = (Button) findViewById(R.id.plane_button);
        
        final Button male_button = (Button) findViewById(R.id.male);
        final Button female_button = (Button) findViewById(R.id.female);
        
        duration_from = (EditText) findViewById(R.id.from_date);
        duration_to = (EditText) findViewById(R.id.to_date);
     // Array of choices
//        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

        // Selection of the spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        PackItActivity.datasource.open();
        final List<String> trips = PackItActivity.datasource.getAllTripNames();

//        int index = trips.indexOf(trip_name);
//
//        Collections.swap(trips, index, 0);
        
        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, trips);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        spinner.setAdapter(spinnerArrayAdapter);
 
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				String trip_name = trips.get(position);
				TripDetails details = PackItActivity.datasource.getTrip(trip_name);
				
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        
		        prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, details.getTransportation());
		        prefs_editor.putString(TripSQLiteHelper.GENDER, details.getGender());
		        prefs_editor.putString(TripSQLiteHelper.TRIP_NAME, details.getTripName());
		        
		        prefs_editor.commit();
		        
		        EditText destination_field = (EditText) findViewById(R.id.destination_field);
		        destination_field.setText(details.getLocation());
		       
		        if (details.getTransportation().equals(Info.WALK)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground);
		        	car_button.setBackgroundResource(R.drawable.ct_road_o);
		        	fly_button.setBackgroundResource(R.drawable.ct_air_o);
		        }
		        
		        else if (details.getTransportation().equals(Info.CAR)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground_o);
		        	car_button.setBackgroundResource(R.drawable.ct_road);
		        	fly_button.setBackgroundResource(R.drawable.ct_air_o);
		        }
		        
		        else if (details.getTransportation().equals(Info.PLANE)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground_o);
		        	car_button.setBackgroundResource(R.drawable.ct_road_o);
		        	fly_button.setBackgroundResource(R.drawable.ct_air);
		        }
		        
		        if (details.getGender().equals(Info.MALE)) {
		        	male_button.setBackgroundResource(R.drawable.ct_male);
		        	female_button.setBackgroundResource(R.drawable.ct_female_o);
		        }
		        else if (details.getGender().equals(Info.FEMALE)) {
		        	male_button.setBackgroundResource(R.drawable.ct_male_o);
		        	female_button.setBackgroundResource(R.drawable.ct_female);
		        }
		        
		        String[] from_date = details.getFromDate().trim().split("-");
		        mFromMonth = Integer.parseInt(from_date[0]) - 1;
		        mFromDay = Integer.parseInt(from_date[1]);
		        mFromYear = Integer.parseInt(from_date[2]);
		        
		        String[] to_date = details.getToDate().trim().split("-");
		        mToMonth = Integer.parseInt(to_date[0]) - 1;
		        mToDay = Integer.parseInt(to_date[1]);
		        mToYear = Integer.parseInt(to_date[2]);
		        
		        duration_from.setText(details.getFromDate());
		        duration_to.setText(details.getToDate());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				 SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
			     String trip_name = prefs.getString(TripSQLiteHelper.TRIP_NAME, null);
			     if (trip_name != null && trips.indexOf(trip_name) >= 0) {
			       	spinner.setSelection(trips.indexOf(trip_name));
			     }
			}
        });
        
        // set initially set one
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
        
        Button save_button = (Button) findViewById(R.id.save_button);
        
        save_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String trip_name  = (String) spinner.getSelectedItem();
		        prefs_editor.putString(TripSQLiteHelper.TRIP_NAME, trip_name);
		        prefs_editor.commit();
				HashMap<String, String> details = new HashMap<String, String>();
				details.put(TripSQLiteHelper.TRIP_NAME, trip_name);
				
				boolean isValid = true;
				
				EditText destination_field = (EditText) ((Activity) v.getContext()).findViewById(R.id.destination_field);
				String destination = destination_field.getText().toString();
				if (destination != null && destination.length() > 0) {
					details.put(TripSQLiteHelper.LOCATION, destination);
				}
				else {
					isValid = false;
				}
				
				// TODO add calendar widget to get date
				
				EditText from_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.from_date);
				String start_date = from_date.getText().toString();
				if (isValid && start_date != null && start_date.length() > 0) {
					details.put(TripSQLiteHelper.FROM_DATE, start_date);
				}
				else {
					isValid = false;
				}
				
				// TODO add calendar widget to get date
				
				EditText to_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.to_date);
				String end_date = to_date.getText().toString();
				if (isValid && end_date != null && end_date.length() > 0) {
					details.put(TripSQLiteHelper.TO_DATE, end_date);
				}
				else {
					isValid = false;
				}
				
				String gender = prefs.getString(TripSQLiteHelper.GENDER, "");
				if (isValid && gender != null && gender.length() > 0) {
					details.put(TripSQLiteHelper.GENDER, gender);
				}
				else {
					isValid = false;
				}
				
				String transportation = prefs.getString(TripSQLiteHelper.TRANSPORTATION, "");
				
				if (isValid && transportation != null && transportation.length() > 0) {
					details.put(TripSQLiteHelper.TRANSPORTATION, transportation);
				}
				else {
					isValid = false;
				}
				
				
				
				if (isValid) {
					long duration = Info.getDateDifference(Info.getDate(start_date), Info.getDate(end_date));
					SaveDataTask task = new SaveDataTask(details, trip_name, duration, gender);
					task.execute();
				}
				else {
					Toast toast = Toast.makeText(v.getContext(), "Please complete all fields", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
        SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE); 
        SharedPreferences.Editor prefs_editor = prefs.edit();
        prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, null);
        prefs_editor.putString(TripSQLiteHelper.GENDER, null);
        prefs_editor.commit();
        
        walk_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String cur_trans = prefs.getString(TripSQLiteHelper.TRANSPORTATION, null);
		        if (cur_trans == null) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.WALK);
		        }
		        else if (cur_trans.equals(Info.WALK)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, null);
		        }
		        else if (cur_trans.equals(Info.CAR)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground);
		        	car_button.setBackgroundResource(R.drawable.ct_road_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.WALK);
		        }
		        else if (cur_trans.equals(Info.PLANE)) {
		        	walk_button.setBackgroundResource(R.drawable.ct_ground);
		        	fly_button.setBackgroundResource(R.drawable.ct_air_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.WALK);
		        }
		        prefs_editor.commit();
			}
		});
        
        car_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String cur_trans = prefs.getString(TripSQLiteHelper.TRANSPORTATION, null);
		        if (cur_trans == null) {
		        	car_button.setBackgroundResource(R.drawable.ct_road);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.CAR);
		        }
		        else if (cur_trans.equals(Info.WALK)) {
		        	car_button.setBackgroundResource(R.drawable.ct_road);
		        	walk_button.setBackgroundResource(R.drawable.ct_ground_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.CAR);
		        }
		        else if (cur_trans.equals(Info.CAR)) {
		        	car_button.setBackgroundResource(R.drawable.ct_road_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, null);
		        }
		        else if (cur_trans.equals(Info.PLANE)) {
		        	car_button.setBackgroundResource(R.drawable.ct_road);
		        	fly_button.setBackgroundResource(R.drawable.ct_air_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.CAR);
		        }
		        prefs_editor.commit();
			}
		});
        
        fly_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String cur_trans = prefs.getString(TripSQLiteHelper.TRANSPORTATION, null);
		        if (cur_trans == null) {
		        	fly_button.setBackgroundResource(R.drawable.ct_air);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.PLANE);
		        }
		        else if (cur_trans.equals(Info.WALK)) {
		        	fly_button.setBackgroundResource(R.drawable.ct_air);
		        	walk_button.setBackgroundResource(R.drawable.ct_ground_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.PLANE);
		        }
		        else if (cur_trans.equals(Info.CAR)) {
		        	fly_button.setBackgroundResource(R.drawable.ct_air);
		        	car_button.setBackgroundResource(R.drawable.ct_road_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, Info.PLANE);
		        }
		        else if (cur_trans.equals(Info.PLANE)) {
		        	fly_button.setBackgroundResource(R.drawable.ct_air_o);
		        	prefs_editor.putString(TripSQLiteHelper.TRANSPORTATION, null);
		        }
		        prefs_editor.commit();
			}
		});
        
        male_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String cur_gender = prefs.getString(TripSQLiteHelper.GENDER, null);
		        
		        if (cur_gender == null) {
		        	male_button.setBackgroundResource(R.drawable.ct_male);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, Info.MALE);
		        }
		        else if (cur_gender.equals(Info.MALE)) {
		        	male_button.setBackgroundResource(R.drawable.ct_male_o);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, null);
		        }
		        else if (cur_gender.equals(Info.FEMALE)) {
		        	male_button.setBackgroundResource(R.drawable.ct_male);
		        	female_button.setBackgroundResource(R.drawable.ct_female_o);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, Info.MALE);
		        }
		        prefs_editor.commit();
			}
		});
        
        female_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        String cur_gender = prefs.getString(TripSQLiteHelper.GENDER, null);
		        
		        if (cur_gender == null) {
		        	female_button.setBackgroundResource(R.drawable.ct_female);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, Info.FEMALE);
		        }
		        else if (cur_gender.equals(Info.MALE)) {
		        	female_button.setBackgroundResource(R.drawable.ct_female);
		        	male_button.setBackgroundResource(R.drawable.ct_male_o);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, Info.FEMALE);
		        }
		        else if (cur_gender.equals(Info.FEMALE)) {
		        	female_button.setBackgroundResource(R.drawable.ct_female_o);
		        	prefs_editor.putString(TripSQLiteHelper.GENDER, null);
		        }
		        prefs_editor.commit();
			}
		});
       
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
    	// add a click listener to the button
       duration_from.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_FROM);
            }
        });
       
   	// add a click listener to the button
       duration_to.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID_TO);
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
	
	// updates the date in the TextView
    private void updateDisplay(EditText textbox) {
        textbox.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID_FROM:
        	mYear = mFromYear;
        	mMonth = mFromMonth;
        	mDay = mFromDay;
            return new DatePickerDialog(this,
                        dateSetListenerFrom,
                        mFromYear, mFromMonth, mFromDay);
        case DATE_DIALOG_ID_TO: 
        	mYear = mToYear;
        	mMonth = mToMonth;
        	mDay = mToDay;
        	return new DatePickerDialog(this, dateSetListenerTo, mToYear, mToMonth, mToDay); 
        }
        return null;
    }
    
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener dateSetListenerFrom =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay(duration_from);
                }
            };
            
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener dateSetListenerTo =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay(duration_to);
                }
            };
            
     
	public class SaveDataTask extends AsyncTask<Void, Void, Integer> {
    	private ProgressDialog dialog;
    	private String trip_name;
    	private long duration;
    	private HashMap<String, String> details;
    	private String gender;
    	
    	public SaveDataTask(HashMap<String, String> details, String trip_name, long duration, String gender) {
    		this.trip_name = trip_name;
    		this.duration = duration;
    		this.details = details;
    		this.gender = gender;
        }

    	public void onPreExecute() {
    		Log.i(TAG, "LoadDataTask onPreExecute");
            dialog = ProgressDialog.show(SettingsActivity.this, "",
                    "Saving trip information and reloading data please wait...", true);
            TextView text = (TextView) dialog.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
        }

         public Integer doInBackground(Void... unused) {
        	int data_type = PackItActivity.datasource.getTripDataType(trip_name);
        	if (data_type == -1) {
        		int random = (int)( Math.random() * 10);
        		data_type = (random <= 5) ? 1 : 2;
        	}
        	details.put(TripSQLiteHelper.DATA_TYPE, "" + data_type);
        	Integer rowsEdited = new Integer(PackItActivity.datasource.editTrip(details, Info.getDBEntries(gender, duration, data_type), trip_name));
			return rowsEdited;
         }

         public void onPostExecute(Integer rowsEdited) {
        	 dialog.dismiss();
        	 if (rowsEdited < 0) {
        		 Toast.makeText(getApplicationContext(),
                         "Error getting data, please try again later",
                         Toast.LENGTH_SHORT).show();
        	 }
         }
    }
}
