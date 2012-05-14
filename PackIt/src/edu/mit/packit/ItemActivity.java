package edu.mit.packit;

import java.util.ArrayList;

import edu.mit.packit.R;
import edu.mit.packit.db.ItemDetails;
import edu.mit.packit.db.TripInfoDataSource;
import edu.mit.packit.db.TripSQLiteHelper;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity that takes care of the editing and removing of items
 * @author Steph
 *
 */
public class ItemActivity extends Activity {

	private final static String TAG = "ItemActivity";
	public static final String EDIT_MODE = "editmode_on";
	private RelativeLayout packView;
	private RelativeLayout addView;
	private ImageView edit_shelf_button;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(Info.CATEGORY, Info.SHIRTS);
	        
	        SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
	        final String trip_name = prefs.getString(TripSQLiteHelper.TRIP_NAME, null);
	        
//	        SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
//			SharedPreferences.Editor editor = editToggle.edit();
//			editor.putBoolean(ItemActivity.EDIT_MODE, false);
//			editor.commit();
			
			TripInfoDataSource.set(getApplicationContext());
			PackItActivity.datasource.open();
			
			setContent(type, trip_name);
	        Button add_button = (Button) findViewById(R.id.add_item_button);
	        edit_shelf_button = (ImageView) findViewById(R.id.edit_shelf_button);
	        
	        packView = (RelativeLayout) findViewById(R.id.pack_view);
			addView = (RelativeLayout) findViewById(R.id.add_view);
			
			SharedPreferences editToggle = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
			if (editToggle.getBoolean(ItemActivity.EDIT_MODE, false)) {
				packView.setVisibility(View.GONE);
				addView.setVisibility(View.VISIBLE);
				edit_shelf_button.setImageResource(R.drawable.exit_btn_25x40);
			}
			else {
				addView.setVisibility(View.GONE);
				packView.setVisibility(View.VISIBLE);
				edit_shelf_button.setImageResource(R.drawable.edit_btn_48x40);
			}
			
			final ImageView backpack_open = (ImageView) packView.findViewById(R.id.backpack);
			final ImageView backpack_closed = (ImageView) addView.findViewById(R.id.backpack);
	        edit_shelf_button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					SharedPreferences editToggle = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
//					SharedPreferences editToggle = getPreferences(MODE_PRIVATE);
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
	        
	        backpack_open.setOnClickListener(new View.OnClickListener() {
				
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
	        
	        backpack_closed.setOnClickListener(new View.OnClickListener() {
				
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
	         add_button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					EditText new_item = (EditText) addView.findViewById(R.id.new_item_field);
					EditText amount_field = (EditText) addView.findViewById(R.id.number_item_field);
					
					int drawable = Info.getNewItem(new_item.getText().toString());
					
					if (drawable < 0) {
						Log.i(TAG, "item not in database or already on shelf");
						Toast toast = Toast.makeText(v.getContext(), "Item not in database or already on shelf", Toast.LENGTH_SHORT);
						toast.show();
					}
					else {
						View bring_items_view = (View) findViewById(R.id.bringitems_view);
						try {
							int amount = Integer.parseInt(amount_field.getText().toString());
							RelativeLayout unpacked_item = (RelativeLayout) bring_items_view.findViewById((drawable & Info.ID_CONST) + Info.UNPACKED_ITEMS);
							String num = PackItActivity.datasource.addItem(trip_name, drawable, amount);
							if (Integer.parseInt(num) > 0) {
								if (unpacked_item != null) {
									unpacked_item.setVisibility(View.VISIBLE);
									TextView item_text = (TextView) unpacked_item.findViewById(R.id.item_text);
									item_text.setText(num);
								}
								Log.i(TAG, "adding new item");
								Toast toast = Toast.makeText(v.getContext(), "Item added to " + Info.getCategory(drawable) + " category.", Toast.LENGTH_SHORT);
								toast.show();
							}
						} catch (NumberFormatException e) {
							Toast toast = Toast.makeText(v.getContext(), "Please enter a valid number", Toast.LENGTH_SHORT);
							toast.show();
						}
						
					}
				}
			});
	    }
	 protected void onResume() {
//		 Log.i(TAG, "here");
		PackItActivity.datasource.open();
		int type = this.getIntent().getIntExtra(Info.CATEGORY, Info.SHIRTS);
		SharedPreferences prefs = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
        String trip_name = prefs.getString(TripSQLiteHelper.TRIP_NAME, null);
        
        SharedPreferences editToggle = getSharedPreferences(TripSQLiteHelper.TABLE_TRIPINFO, MODE_PRIVATE);
		if (editToggle.getBoolean(ItemActivity.EDIT_MODE, false)) {
			packView.setVisibility(View.GONE);
			addView.setVisibility(View.VISIBLE);
			edit_shelf_button.setImageResource(R.drawable.exit_btn_25x40);
		}
		else {
			addView.setVisibility(View.GONE);
			packView.setVisibility(View.VISIBLE);
			edit_shelf_button.setImageResource(R.drawable.edit_btn_48x40);
		}
		setContent(type, trip_name);
		super.onResume();
	}
		
	protected void onPause() {
		PackItActivity.datasource.close();
		super.onPause();
	}
	 
	 public void setContent(int type, final String trip_name) {
		 ArrayList<ItemDetails> items = PackItActivity.datasource.getCategoryItems(trip_name, Info.CATEGORIES[type]);
		 final View bring_items_view = (View) findViewById(R.id.bringitems_view);
		 final View packed_items_view = (View) findViewById(R.id.packeditems_view);
		 final View add_items_view = (View) findViewById(R.id.additems_view);
		 
		 LayoutInflater vi = (LayoutInflater) this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 for (ItemDetails i : items) {
			 if (i.getUnpacked() > 0) {
				 final int item_id = i.getItem();
				 RelativeLayout item_unpacked = (RelativeLayout) bring_items_view.findViewById((item_id & Info.ID_CONST) + Info.UNPACKED_ITEMS);
				 final RelativeLayout item;
				 if (item_unpacked == null) {
					item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
					item.setId((item_id & Info.ID_CONST) + Info.UNPACKED_ITEMS);
					 ImageView image = (ImageView) item.findViewById(R.id.item_image);
					 image.setImageResource(item_id);
					 TextView text = (TextView) item.findViewById(R.id.item_text);
					 text.setText(""+ i.getUnpacked());
					 ((LinearLayout) bring_items_view).addView(item);
					 item.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView text = (TextView) item.findViewById(R.id.item_text);
								int cur_value = Integer.parseInt(text.getText().toString());
								int id = item.getId();
								
								if (cur_value > 0) {
									RelativeLayout packed_item = (RelativeLayout) packed_items_view.findViewById((id & Info.ID_CONST) + Info.PACKED_ITEMS);
									String[] nums = PackItActivity.datasource.packItem(trip_name, item_id, 1).split(" ");
//									Log.i(TAG, Arrays.toString(nums));
									TextView item_text = (TextView) packed_item.findViewById(R.id.item_text);
									if (Integer.parseInt(item_text.getText().toString()) == 0) {
										item_text.setText(nums[Info.PACKED_INDEX]);
										packed_item.setVisibility(View.VISIBLE);
									}
									else {
										item_text.setText(nums[Info.PACKED_INDEX]);
									}
									text.setText(nums[Info.UNPACKED_INDEX]);
									if (Integer.parseInt(nums[Info.UNPACKED_INDEX]) == 0) {
										item.setVisibility(View.GONE);
									}
								}
							}
						});
					 
//					 	item.setOnTouchListener(new View.OnTouchListener() {
//							
//							public boolean onTouch(View v, MotionEvent event) {
//								ClipData data = ClipData.newPlainText("", "");
//								DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
//										v);
//								v.startDrag(data, shadowBuilder, v, 0);
//								v.setVisibility(View.GONE);
//								return true;
//							}
//						});
				 }
				 else {	 
					item = item_unpacked;
					TextView text = (TextView) item.findViewById(R.id.item_text);
					text.setText(""+ i.getUnpacked());
					if (Integer.parseInt(text.getText().toString()) > 0) {
						item.setVisibility(View.VISIBLE);
					}
				 }
			 }
		 }
		 
		 ArrayList<ItemDetails> allItems = PackItActivity.datasource.getTripItems(trip_name);
		 for (ItemDetails i : allItems) {
			 if (i.getUnpacked() > 0 || i.getPacked() > 0) {
				 final int item_id = i.getItem();
				 RelativeLayout item_add = (RelativeLayout) add_items_view.findViewById((item_id & Info.ID_CONST) + Info.ADD_ITEMS);
				 final RelativeLayout item;
				 if (item_add == null) {
					 item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
					 ImageView image = (ImageView) item.findViewById(R.id.item_image);
					 image.setImageResource(item_id);
					 TextView text = (TextView) item.findViewById(R.id.item_text);
					 text.setText("+");
					 ((LinearLayout) add_items_view).addView(item);
					 
					 item.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {								
								RelativeLayout unpacked_item = (RelativeLayout) bring_items_view.findViewById((item_id & Info.ID_CONST) + Info.UNPACKED_ITEMS);
								String num = PackItActivity.datasource.addItem(trip_name, item_id, 1);
								Log.i(TAG, num);
								if (Integer.parseInt(num) > 0 && unpacked_item != null) {
									unpacked_item.setVisibility(View.VISIBLE);
									TextView item_text = (TextView) unpacked_item.findViewById(R.id.item_text);
									item_text.setText(num);
								}
							}
						});
				 }
				 
			 }
			 
			 if (i.getPacked() >= 0) {
				 final int item_id = i.getItem();
				 RelativeLayout item_packed = (RelativeLayout) packed_items_view.findViewById((item_id & Info.ID_CONST) + Info.PACKED_ITEMS);
				 final RelativeLayout item;
				 if (item_packed == null) {
					 item = (RelativeLayout) vi.inflate(R.layout.objectlayout, null);
					 ImageView image = (ImageView) item.findViewById(R.id.item_image);
					 image.setImageResource(item_id);
					 item.setId((item_id & Info.ID_CONST) + Info.PACKED_ITEMS);
//					 Log.i(TAG, ""+ ((i.getItem() & Info.ID_CONST) + Info.PACKED_ITEMS));
					 TextView text = (TextView) item.findViewById(R.id.item_text);
					 text.setText(""+ i.getPacked());
					 ((LinearLayout) packed_items_view).addView(item);
					 
					 item.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView text = (TextView) item.findViewById(R.id.item_text);
								int cur_value = Integer.parseInt(text.getText().toString());
								int id = item.getId();
								
								if (cur_value > 0) {
									RelativeLayout unpacked_item = (RelativeLayout) bring_items_view.findViewById((id & Info.ID_CONST) + Info.UNPACKED_ITEMS);
									String[] nums = PackItActivity.datasource.packItem(trip_name, item_id, -1).split(" ");
									if (unpacked_item != null) {
//										Log.i(TAG, Arrays.toString(nums));
										unpacked_item.setVisibility(View.VISIBLE);
										TextView item_text = (TextView) unpacked_item.findViewById(R.id.item_text);
										if (Integer.parseInt(item_text.getText().toString()) == 0) {
											item_text.setText(nums[Info.UNPACKED_INDEX]);
											unpacked_item.setVisibility(View.VISIBLE);
										}
										else {
											item_text.setText(nums[Info.UNPACKED_INDEX]);
										}
										
									}
									text.setText(nums[Info.PACKED_INDEX]);
									if (Integer.parseInt(nums[Info.PACKED_INDEX]) == 0) {
										item.setVisibility(View.GONE);
									}
								}
							}
						});
				 }
				 else {	 
					 item = item_packed;
					 TextView text = (TextView) item.findViewById(R.id.item_text);
					 text.setText(""+ i.getPacked());
				 }
				 
				 if (i.getPacked() == 0) {
					 item.setVisibility(View.GONE);
				 }
				 else {
					 item.setVisibility(View.VISIBLE);
				 }
			 }
		 }
	 }
}
