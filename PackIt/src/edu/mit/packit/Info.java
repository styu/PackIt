package edu.mit.packit;

import java.util.HashMap;

public class Info {

	public static final String TRIP_NAME = "trip_name";
	public static final String MALE = "male";
	public static final String FEMALE = "female";
	
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
	
	public HashMap<String, Integer> getItemListOne(String gender) {
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		
		list.put(Info.CATEGORIES[Info.SHIRTS], R.drawable.shirts_longsleeveshirt);
		list.put(Info.CATEGORIES[Info.SHIRTS], R.drawable.shirts_tshirt);
		
		list.put(Info.CATEGORIES[Info.JACKETS], R.drawable.jackets_hoodies);
		
		if (gender.equals(Info.MALE)) {
			list.put(Info.CATEGORIES[Info.JACKETS], R.drawable.jackets_coat_male);
			list.put(Info.CATEGORIES[Info.PANTS], R.drawable.pants_jeans_male);
			list.put(Info.CATEGORIES[Info.UNDERGARMENTS], R.drawable.undergarment_underwear_male);
			list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_comb_male);
		}
		else if (gender.equals(Info.FEMALE)) {
			list.put(Info.CATEGORIES[Info.JACKETS], R.drawable.jackets_coat_female);
			list.put(Info.CATEGORIES[Info.PANTS], R.drawable.pants_jeans_female);
			list.put(Info.CATEGORIES[Info.UNDERGARMENTS], R.drawable.undergarment_underwear_female);
			list.put(Info.CATEGORIES[Info.UNDERGARMENTS], R.drawable.undergarment_bra);
			list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_comb_female);
		}
		
		list.put(Info.CATEGORIES[Info.PANTS], R.drawable.pants_ski_pants);
		list.put(Info.CATEGORIES[Info.ACCESSORIES], R.drawable.accessories_rainboots);
		list.put(Info.CATEGORIES[Info.ACCESSORIES], R.drawable.accessories_mittens);
		list.put(Info.CATEGORIES[Info.ACCESSORIES], R.drawable.accessories_scarf);
		list.put(Info.CATEGORIES[Info.ACCESSORIES], R.drawable.accessories_socks);
		
		list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_phone);
		list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_toothbrush);
		list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_toothpaste);
		list.put(Info.CATEGORIES[Info.MISC], R.drawable.misc_umbrella);
		return list;
	}
}
