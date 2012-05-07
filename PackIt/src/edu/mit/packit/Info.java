package edu.mit.packit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import edu.mit.packit.db.TripSQLiteHelper;

import android.content.ContentValues;

public class Info {

	public static final String TRIP_NAME = "trip_name";
	public static final String MALE = "male";
	public static final String FEMALE = "female";
	
	public static final String WALK = "walking";
	public static final String CAR = "car";
	public static final String PLANE = "plane";
	
	public static final int YEAR = 2;
	public static final int MONTH = 0;
	public static final int DAY = 1;
	
	public static final int WEEK = 7;
	public static final int TWO_WEEKS = 14;
	public static final int ONE_MONTH = 28;
	
	public static final String[] CATEGORIES = {
		"SHIRTS",
		"JACKETS",
		"PANTS",
		"FORMAL",
		"ACCESSORIES",
		"UNDERGARMENTS",
		"MISC"
	};
	
	public static final int SHIRTS = 0;
	public static final int JACKETS = 1;
	public static final int PANTS = 2;
	public static final int FORMAL = 3;
	public static final int ACCESSORIES = 4;
	public static final int UNDERGARMENTS = 5;
	public static final int MISC = 6;
	
	/** 
	 * cities: 
-guy to alaska to fish: winter coat, light jacket, long sleeve shirt, pants,  jeans, rain boots, boxers, cell phone, umbrella

item he wants to add: fishing rod 

-girl going to australia (for sydney opera house): tshirt, shorts, light  jacket, jeans, dresses (formal), panties, bra, cell phone, sunblock,  sunglasses,

item she wants to add: ipod, camera
	 */
	
	public static HashMap<Integer, String> getItemListOne(String gender) {
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		
		list.put(R.drawable.shirts_longsleeveshirt, Info.CATEGORIES[Info.SHIRTS]);
		list.put(R.drawable.shirts_tshirt, Info.CATEGORIES[Info.SHIRTS]);
		
		list.put(R.drawable.jackets_hoodies, Info.CATEGORIES[Info.JACKETS]);
		
		if (gender.equals(Info.MALE)) {
			list.put(R.drawable.jackets_coat_male, Info.CATEGORIES[Info.JACKETS]);
			list.put(R.drawable.pants_jeans_male, Info.CATEGORIES[Info.PANTS]);
			list.put(R.drawable.undergarment_underwear_male, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.misc_comb_male, Info.CATEGORIES[Info.MISC]);
		}
		else if (gender.equals(Info.FEMALE)) {
			list.put(R.drawable.jackets_coat_female, Info.CATEGORIES[Info.JACKETS]);
			list.put(R.drawable.pants_jeans_female, Info.CATEGORIES[Info.PANTS]);
			list.put(R.drawable.undergarment_underwear_female, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.undergarment_bra, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.misc_comb_female, Info.CATEGORIES[Info.MISC]);
		}
		
		list.put(R.drawable.pants_ski_pants, Info.CATEGORIES[Info.PANTS]);
		list.put(R.drawable.accessories_rainboots, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_mittens, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_scarf, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_socks, Info.CATEGORIES[Info.ACCESSORIES]);
		
		list.put(R.drawable.misc_phone, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_toothbrush, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_toothpaste, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_umbrella, Info.CATEGORIES[Info.MISC]);
		return list;
	}
	
	public static ArrayList<ContentValues> getDBEntries(HashMap<Integer, String> item_list, long duration) {
		ArrayList<ContentValues> entries = new ArrayList<ContentValues>();
		for (Integer key : item_list.keySet()) {
			String category = item_list.get(key);
			ContentValues values = new ContentValues();
			values.put(TripSQLiteHelper.ITEM, key);
			values.put(TripSQLiteHelper.CATEGORY, category);
			values.put(TripSQLiteHelper.PACKED, 0);
			values.put(TripSQLiteHelper.UNPACKED, Info.getQuantity(key, category, duration));
			entries.add(values);
		}
		return entries;
	}
	
	public static int getQuantity(int item, String category, long duration) {
		if (category.equals(Info.UNDERGARMENTS) || category.equals(Info.SHIRTS)) {
			if (duration <= Info.WEEK) {
				return (int) (duration);
			}
			if (duration <= Info.TWO_WEEKS) {
				return Info.WEEK;
			}
			else {
				return Info.TWO_WEEKS;
			}
		}
		if (category.equals(Info.ACCESSORIES)) {
			if (item == R.drawable.accessories_socks) {
				if (duration <= Info.WEEK) {
					return (int) (duration);
				}
				if (duration <= Info.TWO_WEEKS) {
					return Info.WEEK;
				}
				else {
					return Info.TWO_WEEKS;
				}
			}
			else {
				return 1;
			}
		}
		if (category.equals(Info.MISC)) {
			// TODO add if statement chcecking for added items
			return 1;
		}
		if (category.equals(Info.JACKETS)) {
			return 1;
		}
		if (category.equals(Info.PANTS)) {
			if (duration <= Info.WEEK) {
				return (int) (duration/2);
			}
			if (duration <= Info.TWO_WEEKS) {
				return Info.WEEK /2;
			}
			else {
				return Info.WEEK;
			}
		}
		
		return 0;
	}
	
	public static long getDateDifference(Calendar startDate, Calendar endDate) {
		if (startDate.before(endDate)) {
		  Calendar date = (Calendar) startDate.clone();  
		  long daysBetween = 0;  
		  while (date.before(endDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		  }  
		  return daysBetween;  
		}
		return -1;
	}
	
	public static Calendar getDate(String date) {
		String[] date_fields = date.split("-");
		return new GregorianCalendar(Integer.parseInt(date_fields[Info.YEAR].trim()), 
				Integer.parseInt(date_fields[Info.MONTH].trim()), 
				Integer.parseInt(date_fields[Info.DAY].trim()));
	}
}
