package edu.mit.packit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TripSQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "trip.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_TRIPINFO = "tripinfo";
	public static final String COLUMN_ID = "_id";
	
	//trip info table fields
	public static final String TRIP_NAME = "tripname";
	public static final String LOCATION = "location";
	public static final String TRANSPORTATION = "transportation";
	public static final String GENDER = "gender";
	public static final String FROM_DATE = "from_date";
	public static final String TO_DATE = "to_date";
	
	//Trip info database creation sql statement
	private static final String TRIPINFO_DB_CREATE = "create table " +
			TABLE_TRIPINFO + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + TRIP_NAME
		    + " text unique not null, " + LOCATION 
		    + " text not null, " + TRANSPORTATION
		    + " text not null, " + GENDER
		    + " text not null,  " + FROM_DATE
		    + " text not null, " + TO_DATE
		    + " text not null);";
	
	public TripSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void deleteAllTrips(SQLiteDatabase db) {
		onUpgrade(db, DATABASE_VERSION, DATABASE_VERSION + 1);
	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(TRIPINFO_DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TripSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPINFO);
		onCreate(db);
	}

}
