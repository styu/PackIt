package edu.mit.packit.db;
import java.util.HashMap;

import edu.mit.packit.PackItActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class TripInfoDataSource {

	private SQLiteDatabase database;
	private TripSQLiteHelper dbHelper;
	
	private String[] tripinfo_cols = { TripSQLiteHelper.COLUMN_ID,
			TripSQLiteHelper.TRIP_NAME,
			TripSQLiteHelper.LOCATION,
			TripSQLiteHelper.TRANSPORTATION,
			TripSQLiteHelper.GENDER,
			TripSQLiteHelper.FROM_DATE,
			TripSQLiteHelper.TO_DATE };
	
	public static void set(Context context) {
		if (PackItActivity.datasource == null) {
			PackItActivity.datasource = new TripInfoDataSource(context.getApplicationContext());
		}
		
	}
	private TripInfoDataSource(Context context) {
		dbHelper = new TripSQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		database.setTransactionSuccessful();
		database.endTransaction();
		dbHelper.close();
	}
	
	public void createTrip(HashMap<String, String> details) {
		ContentValues values = new ContentValues();
		for (String info : details.keySet()) {
			values.put(info, details.get(info));
		}
		long insertId = database.insert(TripSQLiteHelper.TABLE_TRIPINFO, null, values);
		//Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.TRIP_NAME + " = \'" + details.get(TripSQLiteHelper.TRIP_NAME)+"\'", null, null, null, null);
		cursor.moveToFirst();
		Log.i("TripInfoDataSource", cursor.getString(1));
		cursor.close();
	}
	
	public void deleteTrip(TripDetails trip) {
		long id = trip.getId();
		database.delete(TripSQLiteHelper.TABLE_TRIPINFO, TripSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public void deleteAllTrips() {
		dbHelper.deleteAllTrips(database);
	}
	
	public TripDetails getAllTrips() {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, null, null, null, null, null);
		
		if (!cursor.moveToFirst()) {
			TripDetails details = new TripDetails();
			details.setId(cursor.getLong(0));
			details.setTripName(cursor.getString(1));
			details.setLocation(cursor.getString(2));
			details.setTransportation(cursor.getString(3));
			details.setFromDate(cursor.getString(4));
			details.setToDate(cursor.getString(5));
			
			return details;
		}
		
		return null;
	}
	public TripDetails getTrip(String trip_name) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
		
		if (!cursor.moveToFirst()) {
			TripDetails details = new TripDetails();
			details.setId(cursor.getLong(0));
			details.setTripName(cursor.getString(1));
			details.setLocation(cursor.getString(2));
			details.setTransportation(cursor.getString(3));
			details.setFromDate(cursor.getString(4));
			details.setToDate(cursor.getString(5));
			
			return details;
		}
		
		return null;
	}
}
