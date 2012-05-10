package edu.mit.packit;

import java.util.ArrayList;

import edu.mit.packit.R;
import edu.mit.packit.db.ItemDetails;
import edu.mit.packit.db.TripInfoDataSource;
import edu.mit.packit.db.TripSQLiteHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
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

	private final static String TAG = "ItemActivity";
	public static final String EDIT_MODE = "editmode_on";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(Info.CATEGORY, Info.SHIRTS);
	        
	        SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
	        String trip_name = prefs.getString(TripSQLiteHelper.TRIP_NAME, null);
	        
	        SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = editToggle.edit();
			editor.putBoolean(ItemActivity.EDIT_MODE, false);
			editor.commit();
			
			TripInfoDataSource.set(getApplicationContext());
			PackItActivity.datasource.open();
			
			setContent(type, trip_name);
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
	        
//	        setContent(type);
	        
//	        TextView item_text = (TextView) findViewById(R.id.item_text);
//	        item_text.setText(category_type);
//	        

	        Button add_button = (Button) findViewById(R.id.add_item_button);
	        final ImageView edit_shelf_button = (ImageView) findViewById(R.id.edit_shelf_button);
	        
	        final RelativeLayout packView = (RelativeLayout) findViewById(R.id.pack_view);
			final RelativeLayout addView = (RelativeLayout) findViewById(R.id.add_view);
	        
	        edit_shelf_button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = editToggle.edit();
					boolean isEditMode = editToggle.getBoolean(ItemActivity.EDIT_MODE, false);
					if (!isEditMode) {
						packView.setVisibility(View.GONE);
						addView.setVisibility(View.VISIBLE);
						edit_shelf_button.setImageResource(R.drawable.exit_btn_25x40);
					}
					else {
						addView.setVisibility(View.GONE);
						packView.setVisibility(View.VISIBLE);
						edit_shelf_button.setImageResource(R.drawable.edit_btn_48x40);
					}
					editor.putBoolean(ItemActivity.EDIT_MODE, !isEditMode);
					editor.commit();
				}
			});
//	         add_button.setOnClickListener(new View.OnClickListener() {
//				
//				public void onClick(View v) {
//					View laptop = findViewById(ItemActivity.LAPTOP_ID);
//					laptop.setVisibility(View.VISIBLE);
//					EditText text = (EditText) findViewById(R.id.new_item_field);
//					text.setText("");
//					text = (EditText) findViewById(R.id.number_item_field);
//					text.setText("");
//					
//					SharedPreferences pref = getSharedPreferences(Items.PACKING_INFO, MODE_PRIVATE);
//					SharedPreferences.Editor editor = pref.edit();
//					editor.putBoolean(Items.LAPTOP, true);
//					editor.commit();
//				}
//			});
	    }
	 protected void onResume() {
		 Log.i(TAG, "here");
		PackItActivity.datasource.open();
		// TODO refresh content
		super.onResume();
	}
		
	protected void onPause() {
		PackItActivity.datasource.close();
		super.onPause();
	}
	 
	 public void setContent(int type, String trip_name) {
		 ArrayList<ItemDetails> items = PackItActivity.datasource.getCategoryItems(trip_name, Info.CATEGORIES[type]);
		 View bring_items_view = (View) findViewById(R.id.bringitems_view);
		 View packed_items_view = (View) findViewById(R.id.packeditems_view);
		 View add_items_view = (View) findViewById(R.id.additems_view);
		 
		 LayoutInflater vi = (LayoutInflater) this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 for (ItemDetails i : items) {
			 if (i.getUnpacked() > 0) {
				 RelativeLayout item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
				 ImageView image = (ImageView) item.findViewById(R.id.item_image);
				 image.setImageResource(i.getItem());
				 TextView text = (TextView) item.findViewById(R.id.item_text);
				 text.setText(""+ i.getUnpacked());
				 ((LinearLayout) bring_items_view).addView(item);
				 
			 }
			 if (i.getPacked() > 0) {
				 RelativeLayout item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
				 ImageView image = (ImageView) item.findViewById(R.id.item_image);
				 image.setImageResource(i.getItem());
				 TextView text = (TextView) item.findViewById(R.id.item_text);
				 text.setText(""+ i.getPacked());
				 ((LinearLayout) packed_items_view).addView(item);
			 }
		 }
		 
		 ArrayList<ItemDetails> allItems = PackItActivity.datasource.getTripItems(trip_name);
		 for (ItemDetails i : allItems) {
			 if (i.getUnpacked() > 0 || i.getPacked() > 0) {
				 RelativeLayout item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
				 ImageView image = (ImageView) item.findViewById(R.id.item_image);
				 image.setImageResource(i.getItem());
				 TextView text = (TextView) item.findViewById(R.id.item_text);
				 text.setText("+");
				 ((LinearLayout) add_items_view).addView(item);
			 }
		 }
	 }
}
