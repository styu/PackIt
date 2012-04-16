package edu.mit.packit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Activity that takes care of the editing and removing of items
 * @author Steph
 *
 */
public class ItemActivity extends Activity {

	public static final String EDIT_MODE = "editmode_on";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(PackActivity.CATEGORY, PackActivity.SHIRTS);
//	        String category_type;
//	        switch (type) {
//	        case PackActivity.SHIRTS: category_type="shirts"; break;
//	        case PackActivity.JACKETS: category_type = "jackets"; break;
//	        case PackActivity.FORMAL_WEAR: category_type="formal wear"; break;
//	        case PackActivity.WINTER_GEAR: category_type = "winter gear"; break;
//	        case PackActivity.PANTS: category_type = "pants"; break;
//	        case PackActivity.UNDERWEAR: category_type = "underwear"; break;
//	        case PackActivity.MISC: category_type = "misc"; break;
//	        default: category_type ="shirts"; break;
//	        }
	        
	        setContent(type);
	        
//	        TextView item_text = (TextView) findViewById(R.id.item_text);
//	        item_text.setText(category_type);
//	        
	        Button edit_button = (Button) findViewById(R.id.edit_button);
	        final Button edit_shelf_button = (Button) findViewById(R.id.edit_shelf_button);
	        
	        final RelativeLayout packView = (RelativeLayout) findViewById(R.id.pack_view);
			final RelativeLayout addView = (RelativeLayout) findViewById(R.id.add_view);
			
	        edit_button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),
							SetTripActivity.class);
					
					startActivity(intent);
				}
			});
	        
	        edit_shelf_button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = editToggle.edit();
					boolean isEditMode = editToggle.getBoolean(ItemActivity.EDIT_MODE, false);
					if (!isEditMode) {
						packView.setVisibility(View.GONE);
						addView.setVisibility(View.VISIBLE);
						edit_shelf_button.setText("X");
					}
					else {
						addView.setVisibility(View.GONE);
						packView.setVisibility(View.VISIBLE);
						edit_shelf_button.setText("Edit");
					}
					editor.putBoolean(ItemActivity.EDIT_MODE, !isEditMode);
					editor.commit();
				}
			});
	        
	    }
	 
	 /**
	  * Initially sets the items based on the user preferences
	  * @param type
	  */
	 private void setContent(int type) {
		 final SharedPreferences items = getSharedPreferences(Items.PACKING_INFO, MODE_PRIVATE);
		
		 LinearLayout add_view = (LinearLayout) findViewById(R.id.additems_view);
		 if (items.getBoolean(Items.LONG_SHIRT, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View long_shirt = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) long_shirt.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.shirts_longsleeveshirt);
				TextView number = (TextView) long_shirt.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(long_shirt);
		 }
		 if (items.getBoolean(Items.SHORT_SHIRT, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View short_shirt = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) short_shirt.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.shirts_tshirt);
				TextView number = (TextView) short_shirt.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(short_shirt);
		 }
		 if (items.getBoolean(Items.HOODY, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View hoody = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) hoody.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.jackets_hoodies);
				TextView number = (TextView) hoody.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(hoody);
		 }
		 if (items.getBoolean(Items.TUX, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View tux = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) tux.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.formal_tux);
				TextView number = (TextView) tux.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(tux);
		 }
		 if (items.getBoolean(Items.MITTENS, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View mittens = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) mittens.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.winter_mittens);
				TextView number = (TextView) mittens.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(mittens);
		 }
		 if (items.getBoolean(Items.LAPTOP, false)) {
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View laptop = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) laptop.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.misc_laptop);
				TextView number = (TextView) laptop.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(laptop);
		 }
	        switch (type) {
	        case PackActivity.SHIRTS: 
	        	if (items.getBoolean(Items.LONG_SHIRT, false)) {
	        		if (items.getInt(Items.LONG_SHIRT_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			final View long_shirt = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) long_shirt.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_longsleeveshirt);
	        			TextView number = (TextView) long_shirt.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LONG_SHIRT_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(long_shirt);
		        		
//		        		image.setOnClickListener(new View.OnClickListener() {
//							
//							public void onClick(View v) {
//								TextView number = (TextView) long_shirt.findViewById(R.id.item_text);
//								
//								int cur_num = items.getInt(Items.LONG_SHIRT_TOBRING, 0);
//								if (cur_num > 0) {
//									SharedPreferences.Editor editor = items.edit();
//									editor.putInt(Items.LONG_SHIRT_TOBRING, cur_num - 1);
//									number.setText(cur_num - 1);
//									
//									int pack_num = items.getInt(Items.LONG_SHIRT_PACKED, 0);
//									editor.putInt(Items.LONG_SHIRT_PACKED, pack_num + 1);
//									
//									editor.commit();
//								}
//							}
//						});
	        		}
	        		if (items.getInt(Items.LONG_SHIRT_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View long_shirt = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) long_shirt.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_longsleeveshirt);
	        			TextView number = (TextView) long_shirt.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LONG_SHIRT_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(long_shirt);
	        		}
	        	}
	        	if (items.getBoolean(Items.SHORT_SHIRT, false)) {
	        		if (items.getInt(Items.SHORT_SHIRT_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View short_shirt = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) short_shirt.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_tshirt);
	        			TextView number = (TextView) short_shirt.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.SHORT_SHIRT_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(short_shirt);
	        		}
	        		if (items.getInt(Items.SHORT_SHIRT_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View short_shirt = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) short_shirt.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_tshirt);
	        			TextView number = (TextView) short_shirt.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.SHORT_SHIRT_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(short_shirt);
	        		}
	        	}
	        	break;
	        case PackActivity.JACKETS: 
	        	if (items.getBoolean(Items.HOODY, false)) {
	        		if (items.getInt(Items.HOODY_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View tux = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) tux.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.jackets_hoodies);
	        			TextView number = (TextView) tux.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.HOODY_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(tux);
	        		}
	        		if (items.getInt(Items.HOODY_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View tux = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) tux.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.jackets_hoodies);
	        			TextView number = (TextView) tux.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.HOODY_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(tux);
	        		}
	        	}
	        	break;
	        case PackActivity.FORMAL_WEAR:  
	        	if (items.getBoolean(Items.TUX, false)) {
	        		if (items.getInt(Items.TUX_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View hoody = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) hoody.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.formal_tux);
	        			TextView number = (TextView) hoody.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.HOODY_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(hoody);
	        		}
	        		if (items.getInt(Items.TUX_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View hoody = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) hoody.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.formal_tux);
	        			TextView number = (TextView) hoody.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.TUX_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(hoody);
	        		}
	        	}
	        	break;
	        case PackActivity.WINTER_GEAR:  
	        	if (items.getBoolean(Items.MITTENS, false)) {
	        		if (items.getInt(Items.MITTENS_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View mittens = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) mittens.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.winter_mittens);
	        			TextView number = (TextView) mittens.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.MITTENS_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(mittens);
	        		}
	        		if (items.getInt(Items.MITTENS_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View mittens = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) mittens.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.winter_mittens);
	        			TextView number = (TextView) mittens.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.MITTENS_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(mittens);
	        		}
	        	}
	        	break;
	        case PackActivity.PANTS: break;
	        case PackActivity.UNDERWEAR:  break;
	        case PackActivity.MISC:  
	        	if (items.getBoolean(Items.LAPTOP, false)) {
	        		if (items.getInt(Items.LAPTOP_TOBRING, 0) > 0 ) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View laptop = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) laptop.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.misc_laptop);
	        			TextView number = (TextView) laptop.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LAPTOP_TOBRING, 0));
		        		LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		        		bring_view.addView(laptop);
	        		}
	        		if (items.getInt(Items.LAPTOP_PACKED, 0) > 0) {
	        			LayoutInflater vi = (LayoutInflater) this
	        	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        			View laptop = vi.inflate(R.layout.objectlayout, null);
	        			ImageView image = (ImageView) laptop.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.misc_laptop);
	        			TextView number = (TextView) laptop.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LAPTOP_PACKED, 0));
		        		LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		        		packed_view.addView(laptop);
	        		}
	        	}
	        	break;
	        default:  break;
	        }
	 }
}
