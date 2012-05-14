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
	
	public static final int PACKED_ITEMS = 0x5d010000;
	public static final int UNPACKED_ITEMS = 0x5d020000;
	public static final int ADD_ITEMS = 0x5d030000;
	public static final int ID_CONST = 0x0000FFFF;
	
	public static final int PACKED_INDEX = 1;
	public static final int UNPACKED_INDEX = 0;
	
	public static final int PACKING_TIP = 0;
	public static final int WEATHER = 1;
	public static final int REMINDERS = 2;
	
	public static final String CATEGORY = "category";
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
	
	private static HashMap<Integer, String> getItemList(String gender) {
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		
		list.put(R.drawable.shirts_longsleeveshirt, Info.CATEGORIES[Info.SHIRTS]);
		list.put(R.drawable.shirts_tshirt, Info.CATEGORIES[Info.SHIRTS]);
		
		list.put(R.drawable.jackets_hoodies, Info.CATEGORIES[Info.JACKETS]);
		
		if (gender.equals(Info.MALE)) {
			list.put(R.drawable.jackets_coat_male, Info.CATEGORIES[Info.JACKETS]);
			list.put(R.drawable.pants_jeans_male, Info.CATEGORIES[Info.PANTS]);
			list.put(R.drawable.undergarment_underwear_male, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.misc_comb_male, Info.CATEGORIES[Info.MISC]);
			list.put(R.drawable.formal_tie, Info.CATEGORIES[Info.FORMAL]);
			list.put(R.drawable.formal_tux, Info.CATEGORIES[Info.FORMAL]);
			list.put(R.drawable.pants_shorts_men, Info.CATEGORIES[Info.PANTS]);
		}
		else if (gender.equals(Info.FEMALE)) {
			list.put(R.drawable.jackets_coat_female, Info.CATEGORIES[Info.JACKETS]);
			list.put(R.drawable.pants_jeans_female, Info.CATEGORIES[Info.PANTS]);
			list.put(R.drawable.undergarment_underwear_female, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.undergarment_bra, Info.CATEGORIES[Info.UNDERGARMENTS]);
			list.put(R.drawable.misc_comb_female, Info.CATEGORIES[Info.MISC]);
			list.put(R.drawable.formal_dress, Info.CATEGORIES[Info.FORMAL]);
			list.put(R.drawable.pants_shorts_female, Info.CATEGORIES[Info.PANTS]);
		}
		
		list.put(R.drawable.pants_ski_pants, Info.CATEGORIES[Info.PANTS]);
		list.put(R.drawable.accessories_rainboots, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_mittens, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_scarf, Info.CATEGORIES[Info.ACCESSORIES]);
		list.put(R.drawable.accessories_socks, Info.CATEGORIES[Info.ACCESSORIES]);
		
		list.put(R.drawable.misc_camera, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_ipod, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_sunblock, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_sunglasses, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_phone, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_toothbrush, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_toothpaste, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_umbrella, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_fishingrod, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_laptop, Info.CATEGORIES[Info.MISC]);
		list.put(R.drawable.misc_bagpipes, Info.CATEGORIES[Info.MISC]);
		return list;
	}
	
	public static ArrayList<ContentValues> getDBEntries(String gender, long duration, int data_type) {
		HashMap<Integer, String> item_list =  getItemList(gender);
		ArrayList<ContentValues> entries = new ArrayList<ContentValues>();
		for (Integer key : item_list.keySet()) {
			String category = item_list.get(key);
			ContentValues values = new ContentValues();
			values.put(TripSQLiteHelper.ITEM, key);
			values.put(TripSQLiteHelper.CATEGORY, category);
			values.put(TripSQLiteHelper.PACKED, 0);
			values.put(TripSQLiteHelper.UNPACKED, Info.getQuantity(key, category, duration, data_type));
			entries.add(values);
		}
		return entries;
	}
	
	public static String getCategory(int item) {
		switch (item){
			case R.drawable.accessories_mittens: return "accessories";
			case R.drawable.accessories_rainboots: return "accessories";
			case R.drawable.accessories_scarf: return "accessories";
			case R.drawable.accessories_socks: return "accessories";
			case R.drawable.jackets_coat_female: return "jackets";
			case R.drawable.jackets_coat_male: return "jackets";
			case R.drawable.jackets_hoodies: return "jackets";
			case R.drawable.misc_bagpipes: return "miscellaneous";
			case R.drawable.misc_comb_female: return "miscellaneous";
			case R.drawable.misc_comb_male: return "miscellaneous";
			case R.drawable.misc_fishingrod: return "miscellaneous";
			case R.drawable.misc_laptop: return "miscellaneous";
			case R.drawable.misc_phone: return "miscellaneous";
			case R.drawable.misc_toothbrush: return "miscellaneous";
			case R.drawable.misc_toothpaste: return "miscellaneous";
			case R.drawable.misc_umbrella: return "miscellaneous";
			case R.drawable.pants_jeans_female: return "pants";
			case R.drawable.pants_jeans_male: return "pants";
			case R.drawable.pants_ski_pants: return "pants";
			case R.drawable.shirts_longsleeveshirt: return "shirts";
			case R.drawable.shirts_tshirt: return "shirts";
			case R.drawable.undergarment_bra: return "undergarments";
			case R.drawable.undergarment_underwear_female: return "undergarments";
			case R.drawable.undergarment_underwear_male: return "undergarments";
			default: return "miscellaneous";
		}
	}
	public static int getNewItem(String new_item) {
		String modified = new_item.replaceAll(" ", "").toUpperCase();
		
		if (modified.contains("FISHINGROD") || modified.contains("ROD") || modified.contains("FISHING") || modified.contains("FISH")) {
			return R.drawable.misc_fishingrod;
		}
		else if (modified.contains("COMPUTER") || modified.contains("MAC") || modified.contains("PC") || modified.contains("LAPTOP") || modified.contains("COMP")) {
			return R.drawable.misc_laptop;
		}
		else if (modified.contains("PIPES") || modified.contains("BAGPIPES")) {
			return R.drawable.misc_bagpipes;
		}
		return -1;
	}
	public static int getQuantity(int item, String category, long duration, int choice) {
		if (category.equals(Info.CATEGORIES[Info.UNDERGARMENTS])) {
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
		if (category.equals(Info.CATEGORIES[Info.SHIRTS])) {
			if (item == R.drawable.shirts_longsleeveshirt && choice == 2) {
				return 0;
			}
			else {
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
		}
		if (category.equals(Info.CATEGORIES[Info.ACCESSORIES])) {
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
				if (choice == 1) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
		if (category.equals(Info.CATEGORIES[Info.MISC])) {
			if (item == R.drawable.misc_fishingrod || item == R.drawable.misc_bagpipes || item == R.drawable.misc_laptop || item == R.drawable.misc_ipod || item == R.drawable.misc_camera) {
				return 0;
			}
			if ((item == R.drawable.misc_sunblock || item == R.drawable.misc_sunglasses) && choice == 1) {
				return 0;
			}
			return 1;
		}
		if (category.equals(Info.CATEGORIES[Info.JACKETS])) {
			if ((item == R.drawable.jackets_coat_female || item == R.drawable.jackets_coat_male) && choice == 2) {
				return 0;
			}
			return 1;
		}
		if (category.equals(Info.CATEGORIES[Info.PANTS])) {
			if (item == R.drawable.pants_ski_pants && choice == 2) {
				return 0;
			}
			if ((item == R.drawable.pants_shorts_female || item == R.drawable.pants_shorts_men) && choice == 1) {
				return 0;
			}
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
		if (category.equals(Info.CATEGORIES[Info.FORMAL])) {
			if (choice == 1) {
				return 0;
			}
			return 1;
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
