package edu.mit.packit.db;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private static final String TAG = "TripInfoDataSource";
	
	private String[] tripinfo_cols = { TripSQLiteHelper.COLUMN_ID,
			TripSQLiteHelper.TRIP_NAME,
			TripSQLiteHelper.LOCATION,
			TripSQLiteHelper.TRANSPORTATION,
			TripSQLiteHelper.GENDER,
			TripSQLiteHelper.FROM_DATE,
			TripSQLiteHelper.TO_DATE };
	
	private String[] tripitem_cols = { TripSQLiteHelper.COLUMN_ID,
			TripSQLiteHelper.TRIP_ID,
			TripSQLiteHelper.ITEM,
			TripSQLiteHelper.CATEGORY,
			TripSQLiteHelper.PACKED,
			TripSQLiteHelper.UNPACKED 
	};
	
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
		//database.setTransactionSuccessful();
		//database.endTransaction();
		dbHelper.close();
	}
	
	public void createTrip(HashMap<String, String> details, ArrayList<ContentValues> items) {
		ContentValues values = new ContentValues();
		for (String info : details.keySet()) {
			values.put(info, details.get(info));
		}
		long insertId = database.insert(TripSQLiteHelper.TABLE_TRIPINFO, null, values);
		for (ContentValues item_values : items) {
			item_values.put(TripSQLiteHelper.TRIP_ID, (int)(insertId));
			database.insert(TripSQLiteHelper.TABLE_ITEMS, null, item_values);
		}
		//Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
//		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.TRIP_NAME + " = \'" + details.get(TripSQLiteHelper.TRIP_NAME)+"\'", null, null, null, null);
//		Log.i(TAG, ""+cursor.moveToFirst());
		// Log.i("TripInfoDataSource", cursor.getString(0));
//		cursor.close();
	}
	
	public void deleteTrip(TripDetails trip) {
		long id = trip.getId();
		database.delete(TripSQLiteHelper.TABLE_TRIPINFO, TripSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public void deleteAllTrips() {
		dbHelper.deleteAllTrips(database);
	}
	
	public List<String> getAllTripNames() {
		List<String> trip_names = new ArrayList<String>();

		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO,
				tripinfo_cols, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			TripDetails detail = cursorToTrip(cursor);
			trip_names.add(detail.getTripName());
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return trip_names;
	}
	
	public List<TripDetails> getAllTrips() {
		List<TripDetails> details = new ArrayList<TripDetails>();

		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO,
				tripinfo_cols, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			TripDetails detail = cursorToTrip(cursor);
			details.add(detail);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return details;
	}
	
	public TripDetails getTrip(String trip_name) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, tripinfo_cols, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
		if (cursor.moveToFirst()) {
			TripDetails details = new TripDetails();
			details.setId(cursor.getLong(0));
			details.setTripName(cursor.getString(1));
			details.setLocation(cursor.getString(2));
			details.setTransportation(cursor.getString(3));
			details.setGender(cursor.getString(4));
			details.setFromDate(cursor.getString(5));
			details.setToDate(cursor.getString(6));
			
			return details;
		}
		
		return null;
	}
	
	private TripDetails cursorToTrip(Cursor cursor) {
		TripDetails details = new TripDetails();
		details.setId(cursor.getLong(0));
		details.setTripName(cursor.getString(1));
		details.setLocation(cursor.getString(2));
		details.setTransportation(cursor.getString(3));
		details.setFromDate(cursor.getString(4));
		details.setToDate(cursor.getString(5));
		return details;
	}
	
	private ItemDetails cursorToItem(Cursor cursor) {
		ItemDetails details = new ItemDetails();
		details.setId(cursor.getLong(0));
		details.setTripId(cursor.getLong(1));
		details.setItem(cursor.getInt(2));
		details.setCategory(cursor.getString(3));
		details.setPacked(cursor.getInt(4));
		details.setUnpacked(cursor.getInt(5));
		return details;
	}
	public ArrayList<ItemDetails> getCategoryItems(String trip_name, String category) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, new String[] {TripSQLiteHelper.COLUMN_ID}, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
//		Log.i(TAG, ""+cursor.moveToFirst());
		cursor.moveToFirst();
		ArrayList<ItemDetails> items = new ArrayList<ItemDetails>();
		if (cursor.moveToFirst()) {
			long trip_id = cursor.getLong(0);
			Cursor itemCursor = database.query(TripSQLiteHelper.TABLE_ITEMS, 
					tripitem_cols, 
					TripSQLiteHelper.TRIP_ID + " = ?  AND " + TripSQLiteHelper.CATEGORY + " = ?", 
					new String[] { ""+ trip_id, category}, 
					null, null, null);
			itemCursor.moveToFirst();
//			Log.i(TAG, ""+itemCursor.moveToFirst());
			while (!itemCursor.isAfterLast()) {
				ItemDetails detail = cursorToItem(itemCursor);
				items.add(detail);
				itemCursor.moveToNext();
			}
		}
		return items;
	}
	
	public ArrayList<ItemDetails> getTripItems(String trip_name) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, new String[] {TripSQLiteHelper.COLUMN_ID}, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
//		Log.i(TAG, ""+cursor.moveToFirst());
		ArrayList<ItemDetails> items = new ArrayList<ItemDetails>();
		if (cursor.moveToFirst()) {
			long trip_id = cursor.getLong(0);
			Cursor itemCursor = database.query(TripSQLiteHelper.TABLE_ITEMS, tripitem_cols, TripSQLiteHelper.TRIP_ID + " = " + trip_id, null, null, null, null);
			Log.i(TAG, ""+itemCursor.moveToFirst());
			while (!itemCursor.isAfterLast()) {
				ItemDetails detail = cursorToItem(itemCursor);
				items.add(detail);
				itemCursor.moveToNext();
			}
		}
		return items;
	}
	
	public String packItem(String trip_name, int item, int amount) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, new String[] {TripSQLiteHelper.COLUMN_ID}, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
		if (cursor.moveToFirst()) {
			long trip_id = cursor.getLong(0);
			Cursor itemCursor = database.query(TripSQLiteHelper.TABLE_ITEMS, 
					tripitem_cols, 
					TripSQLiteHelper.TRIP_ID + " = ?  AND " + TripSQLiteHelper.ITEM + " = ?", 
					new String[] { ""+ trip_id, "" + item}, 
					null, null, null);
			if (itemCursor.moveToFirst()) {
				int packed;
				if ( (packed =  itemCursor.getInt(4) + amount) >= 0) {
					int unpacked = itemCursor.getInt(5) - amount;
					ContentValues values = new ContentValues();
					values.put(TripSQLiteHelper.PACKED, packed);
					values.put(TripSQLiteHelper.UNPACKED, unpacked);
					int rows = database.update(TripSQLiteHelper.TABLE_ITEMS, 
									values, 
									TripSQLiteHelper.TRIP_ID + " = ? AND " + TripSQLiteHelper.ITEM + " = ?", 
									new String[] { ""+ trip_id, "" + item});
					if (rows > 0) return "" + unpacked + " " + packed;
				}
				return "-3";
			}
			return "-2";
		}
		return "-1";
	}
	
	public String addItem(String trip_name, int item, int amount) {
		Cursor cursor = database.query(TripSQLiteHelper.TABLE_TRIPINFO, new String[] {TripSQLiteHelper.COLUMN_ID}, TripSQLiteHelper.TRIP_NAME + " = \'" + trip_name +"\'", null, null, null, null);
		if (cursor.moveToFirst()) {
			long trip_id = cursor.getLong(0);
			Cursor itemCursor = database.query(TripSQLiteHelper.TABLE_ITEMS, 
					tripitem_cols, 
					TripSQLiteHelper.TRIP_ID + " = ?  AND " + TripSQLiteHelper.ITEM + " = ?", 
					new String[] { ""+ trip_id, "" + item}, 
					null, null, null);
			if (itemCursor.moveToFirst()) {
				int unpacked = itemCursor.getInt(5) + amount;
				ContentValues values = new ContentValues();
				values.put(TripSQLiteHelper.UNPACKED, unpacked);
				int rows = database.update(TripSQLiteHelper.TABLE_ITEMS, 
								values, 
								TripSQLiteHelper.TRIP_ID + " = ? AND " + TripSQLiteHelper.ITEM + " = ?", 
								new String[] { ""+ trip_id, "" + item});
				if (rows > 0) return "" + unpacked;
			}
		}
		return "-1";
	}
}
