package edu.mit.packit;

import edu.mit.packit.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Activity that takes care of the editing and removing of items
 * @author Steph
 *
 */
public class ItemActivity extends Activity {

	private static final int LAPTOP_ID = 0;
	private static final int LAPTOP_PACK_ID = 1;
	private static final int LAPTOP_BRING_ID = 2;
	public static final String EDIT_MODE = "editmode_on";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(PackActivity.CATEGORY, PackActivity.SHIRTS);
	        
	        SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = editToggle.edit();
			editor.putBoolean(ItemActivity.EDIT_MODE, false);
			editor.commit();
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
//	        Button edit_button = (Button) findViewById(R.id.edit_button);
	        Button add_button = (Button) findViewById(R.id.add_item_button);
	        final ImageView edit_shelf_button = (ImageView) findViewById(R.id.edit_shelf_button);
	        
	        final RelativeLayout packView = (RelativeLayout) findViewById(R.id.pack_view);
			final RelativeLayout addView = (RelativeLayout) findViewById(R.id.add_view);
//
//	        edit_button.setOnClickListener(new View.OnClickListener() {
//				
//				public void onClick(View v) {
//					Intent intent = new Intent(v.getContext(),
//							SetTripInfoActivity.class);
//					
//					startActivity(intent);
//				}
//			});
	        
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
	 public void onResume(Bundle savedInstanceState) {
		 int type = this.getIntent().getIntExtra(PackActivity.CATEGORY, PackActivity.SHIRTS);

		 Log.i("ItemActivity", "here");
	 }
}
