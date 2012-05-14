package edu.mit.packit;

import java.util.Calendar;
import java.util.HashMap;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SetTripInfoActivity extends Activity {

	private EditText duration_from; 
	private EditText duration_to; 
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID_FROM = 0;
    static final int DATE_DIALOG_ID_TO = 1;
    
    private static final String TAG = "SetTripInfoActivity";
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackItActivity.datasource.open();
        setContentView(R.layout.settripinfolayout);
        
        PackItActivity.datasource.open();
        
        final String trip_name = getIntent().getStringExtra(Info.TRIP_NAME);
        
        Button go_button = (Button)findViewById(R.id.go_button);
        Button back_button = (Button) findViewById(R.id.back_button);
        go_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		        SharedPreferences.Editor prefs_editor = prefs.edit();
		        prefs_editor.putString(TripSQLiteHelper.TRIP_NAME, trip_name);
		        prefs_editor.commit();
				HashMap<String, String> details = new HashMap<String, String>();
				details.put(TripSQLiteHelper.TRIP_NAME, trip_name);
				
				EditText destination_field = (EditText) ((Activity) v.getContext()).findViewById(R.id.destination_field);
				details.put(TripSQLiteHelper.LOCATION, destination_field.getText().toString());
				
				// TODO add calendar widget to get date
				
				EditText from_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.from_date);
				String start_date = from_date.getText().toString();
				details.put(TripSQLiteHelper.FROM_DATE, start_date);
				
				// TODO add calendar widget to get date
				
				EditText to_date = (EditText) ((Activity) v.getContext()).findViewById(R.id.to_date);
				String end_date = to_date.getText().toString();
				details.put(TripSQLiteHelper.TO_DATE, end_date);
				
				String gender = prefs.getString(TripSQLiteHelper.GENDER, "");
				details.put(TripSQLiteHelper.GENDER, gender);
				
				String transportation = prefs.getString(TripSQLiteHelper.TRANSPORTATION, "");
				details.put(TripSQLiteHelper.TRANSPORTATION, transportation);
				
				long duration = Info.getDateDifference(Info.getDate(start_date), Info.getDate(end_date));

				LoadDataTask task = new LoadDataTask(details, duration, gender);
				task.execute();
//				PackItActivity.datasource.createTrip(details, Info.getDBEntries(Info.getItemListOne(gender), Math.abs(duration)));
//				
//				
//				Intent intent = new Intent(v.getContext(),
//							PackActivity.class);
//				startActivity(intent);
//				finish();
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
        
        final Button walk_button = (Button) findViewById(R.id.walking_button);
        final Button car_button = (Button) findViewById(R.id.car_button);
        final Button fly_button = (Button) findViewById(R.id.plane_button);
        
        final Button male_button = (Button) findViewById(R.id.male);
        final Button female_button = (Button) findViewById(R.id.female);
        
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

        duration_from = (EditText) findViewById(R.id.from_date);
        duration_to = (EditText) findViewById(R.id.to_date);
       
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
       
    // get the current date
       final Calendar c = Calendar.getInstance();
       mYear = c.get(Calendar.YEAR);
       mMonth = c.get(Calendar.MONTH);
       mDay = c.get(Calendar.DAY_OF_MONTH);
       
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
            return new DatePickerDialog(this,
                        dateSetListenerFrom,
                        mYear, mMonth, mDay);
        case DATE_DIALOG_ID_TO: 
        	return new DatePickerDialog(this, dateSetListenerTo, mYear, mMonth, mDay); 
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
            
    public class LoadDataTask extends AsyncTask<Void, Void, Void> {
    	private ProgressDialog dialog;
    	private long duration;
    	private HashMap<String, String> details;
    	private String gender;
    	
    	public LoadDataTask(HashMap<String, String> details, long duration, String gender) {
    		this.duration = duration;
    		this.details = details;
    		this.gender = gender;
        }

    	public void onPreExecute() {
    		Log.i(TAG, "LoadDataTask onPreExecute");
            dialog = ProgressDialog.show(SetTripInfoActivity.this, "",
                    "Creating trip please wait...", true);
            TextView text = (TextView) dialog.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
        }

         public Void doInBackground(Void... unused) {
        	 PackItActivity.datasource.createTrip(details, Info.getDBEntries(Info.getItemListOne(gender), Math.abs(duration)));
			return null;
         }

         public void onPostExecute(Void unused) {
        	 dialog.dismiss();
        		
 			Intent intent = new Intent(SetTripInfoActivity.this,
 							PackActivity.class);
 				startActivity(intent);
 			finish();
         }
    }
}
