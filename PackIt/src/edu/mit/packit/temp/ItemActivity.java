package edu.mit.packit.temp;

import edu.mit.packit.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

	private static final int LAPTOP_ID = 0;
	private static final int LAPTOP_PACK_ID = 1;
	private static final int LAPTOP_BRING_ID = 2;
	public static final String EDIT_MODE = "editmode_on";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(PackTempActivity.CATEGORY, PackTempActivity.SHIRTS);
	        
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
	        
	        setContent(type);
	        
//	        TextView item_text = (TextView) findViewById(R.id.item_text);
//	        item_text.setText(category_type);
//	        
	        Button edit_button = (Button) findViewById(R.id.edit_button);
	        Button add_button = (Button) findViewById(R.id.add_item_button);
	        final ImageView edit_shelf_button = (ImageView) findViewById(R.id.edit_shelf_button);
	        
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
					View laptop = findViewById(ItemActivity.LAPTOP_ID);
					laptop.setVisibility(View.VISIBLE);
					EditText text = (EditText) findViewById(R.id.new_item_field);
					text.setText("");
					text = (EditText) findViewById(R.id.number_item_field);
					text.setText("");
					
					SharedPreferences pref = getSharedPreferences(Items.PACKING_INFO, MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.putBoolean(Items.LAPTOP, true);
					editor.commit();
				}
			});
	    }
	 public void onResume(Bundle savedInstanceState) {
		 int type = this.getIntent().getIntExtra(PackTempActivity.CATEGORY, PackTempActivity.SHIRTS);
		 setContent(type);
		 Log.i("ItemActivity", "here");
	 }
	 /**
	  * Initially sets the items based on the user preferences
	  * @param type
	  */
	 private void setContent(int type) {
		 final SharedPreferences items = getSharedPreferences(Items.PACKING_INFO, MODE_PRIVATE);
		
		 LinearLayout add_view = (LinearLayout) findViewById(R.id.additems_view);
		final LinearLayout bring_view = (LinearLayout) findViewById(R.id.bringitems_view);
		final LinearLayout packed_view = (LinearLayout) findViewById(R.id.packeditems_view);
		 final View long_shirt_add;
		 LayoutInflater vi_bring = (LayoutInflater) this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  		final View long_shirt_bring = vi_bring.inflate(R.layout.objectlayout, null);
  		LayoutInflater vi_pack = (LayoutInflater) this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  		final View long_shirt_pack = vi_pack.inflate(R.layout.objectlayout, null);
		 final View short_shirt_add;
			final View short_shirt_bring = vi_bring.inflate(R.layout.objectlayout, null);
			LayoutInflater vi_packed = (LayoutInflater) this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View short_shirt_pack = vi_packed.inflate(R.layout.objectlayout, null);
		final View tux = vi_packed.inflate(R.layout.objectlayout, null);
		final View mittens = vi_packed.inflate(R.layout.objectlayout, null);
		final View hoody_bring = vi_packed.inflate(R.layout.objectlayout, null);
		final View hoody_pack = vi_packed.inflate(R.layout.objectlayout, null);
		 final View mittens_add;
		 final View mittens_bring = vi_packed.inflate(R.layout.objectlayout, null);
		final View mittens_pack = vi_packed.inflate(R.layout.objectlayout, null);
		final View laptop_bring = vi_packed.inflate(R.layout.objectlayout, null);
		final View laptop_pack = vi_packed.inflate(R.layout.objectlayout, null);
		 if (items.getBoolean(Items.LONG_SHIRT, false)) {
			 
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 long_shirt_add = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) long_shirt_add.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.shirts_longsleeveshirt);
				TextView number = (TextView) long_shirt_add.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(long_shirt_add);
	 		if (items.getInt(Items.LONG_SHIRT_PACKED, 0) >= 0) {
    			ImageView image_pack = (ImageView) long_shirt_pack.findViewById(R.id.item_image);
    			image_pack.setImageResource(R.drawable.shirts_longsleeveshirt);
    			TextView pack_number = (TextView) long_shirt_pack.findViewById(R.id.item_text);
    			pack_number.setText(""+items.getInt(Items.LONG_SHIRT_PACKED, 0));
        		
        		packed_view.addView(long_shirt_pack);
//        		if (items.getInt(Items.LONG_SHIRT_PACKED, 0) == 0) {
//    				long_shirt_pack.setVisibility(View.GONE);
//    			}
        		image_pack.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						Log.i("ItemActivity", "CLICKING");
						TextView pack_number = (TextView) long_shirt_pack.findViewById(R.id.item_text);
						Log.i("ItemActivity", "unpacking");
						int cur_num = items.getInt(Items.LONG_SHIRT_PACKED, 0);
						if (cur_num > 0) {
							SharedPreferences.Editor editor = items.edit();
							editor.putInt(Items.LONG_SHIRT_PACKED, cur_num - 1);
							pack_number.setText(""+(cur_num - 1));
							
//							if (cur_num - 1 == 0) {
//								long_shirt_pack.setVisibility(View.GONE);
//							}
							TextView number = (TextView) long_shirt_bring.findViewById(R.id.item_text);
								int bring_num = items.getInt(Items.LONG_SHIRT_TOBRING, 0);
								number.setText(""+(bring_num + 1));
								editor.putInt(Items.LONG_SHIRT_TOBRING, bring_num + 1);
							editor.commit();
						}
					}
				});
    		}
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
	 		
	 		if (items.getInt(Items.SHORT_SHIRT_PACKED, 0) >= 0) {
    			ImageView image_pack = (ImageView) short_shirt_pack.findViewById(R.id.item_image);
    			image_pack.setImageResource(R.drawable.shirts_tshirt);
    			TextView pack_number = (TextView) short_shirt_pack.findViewById(R.id.item_text);
    			pack_number.setText(""+items.getInt(Items.SHORT_SHIRT_PACKED, 0));
        		packed_view.addView(short_shirt_pack);
//        		if (items.getInt(Items.SHORT_SHIRT_PACKED, 0) == 0) {
//    				short_shirt_pack.setVisibility(View.GONE);
//    			}
        		image_pack.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						TextView pack_number = (TextView) short_shirt_pack.findViewById(R.id.item_text);
						Log.i("ItemActivity", "unpacking");
						int cur_num = items.getInt(Items.SHORT_SHIRT_PACKED, 0);
						if (cur_num > 0) {
							SharedPreferences.Editor editor = items.edit();
							editor.putInt(Items.SHORT_SHIRT_PACKED, cur_num - 1);
							pack_number.setText(""+(cur_num - 1));
//							if (cur_num - 1 == 0) {
//								short_shirt_pack.setVisibility(View.GONE);
//							}
							TextView number = (TextView) short_shirt_bring.findViewById(R.id.item_text);
								int bring_num = items.getInt(Items.SHORT_SHIRT_TOBRING, 0);
								number.setText(""+(bring_num + 1));
								editor.putInt(Items.SHORT_SHIRT_TOBRING, bring_num + 1);
							editor.commit();
						}
					}
				});
    		}
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
	 		if (items.getInt(Items.HOODY_PACKED, 0) >= 0) {
    			ImageView image_pack = (ImageView) hoody_pack.findViewById(R.id.item_image);
    			image_pack.setImageResource(R.drawable.jackets_hoodies);
    			TextView pack_number = (TextView) hoody_pack.findViewById(R.id.item_text);
    			pack_number.setText(""+items.getInt(Items.HOODY_PACKED, 0));
        		packed_view.addView(hoody_pack);
//        		if (items.getInt(Items.HOODY_PACKED, 0) == 0) {
//    				hoody_pack.setVisibility(View.GONE);
//    			}
        		image_pack.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						TextView pack_number = (TextView) hoody_pack.findViewById(R.id.item_text);
						Log.i("ItemActivity", "unpacking");
						int cur_num = items.getInt(Items.HOODY_PACKED, 0);
						if (cur_num > 0) {
							SharedPreferences.Editor editor = items.edit();
							editor.putInt(Items.HOODY_PACKED, cur_num - 1);
							pack_number.setText(""+(cur_num - 1));
//							if (cur_num - 1 == 0) {
//								hoody_pack.setVisibility(View.GONE);
//							}
							TextView number = (TextView) hoody_bring.findViewById(R.id.item_text);
								int bring_num = items.getInt(Items.HOODY_TOBRING, 0);
								number.setText(""+(bring_num + 1));
								editor.putInt(Items.HOODY_TOBRING, bring_num + 1);
							editor.commit();
						}
					}
				});
    		}
		 }
		 if (items.getBoolean(Items.MITTENS, false)) {
	 		ImageView image = (ImageView) mittens.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.winter_mittens);
				TextView number = (TextView) mittens.findViewById(R.id.item_text);
				number.setText("+");
	 		add_view.addView(mittens);
	 		
	 		if (items.getInt(Items.MITTENS_PACKED, 0) >= 0) {
    			ImageView image_pack = (ImageView) mittens_pack.findViewById(R.id.item_image);
    			image_pack.setImageResource(R.drawable.winter_mittens);
    			TextView pack_number = (TextView) mittens_pack.findViewById(R.id.item_text);
    			pack_number.setText(""+items.getInt(Items.MITTENS_PACKED, 0));
        		packed_view.addView(mittens_pack);
//        		if (items.getInt(Items.MITTENS_PACKED, 0) == 0) {
//    				mittens_pack.setVisibility(View.GONE);
//    			}
        		image_pack.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						TextView pack_number = (TextView) mittens_pack.findViewById(R.id.item_text);
						Log.i("ItemActivity", "unpacking");
						int cur_num = items.getInt(Items.MITTENS_PACKED, 0);
						if (cur_num > 0) {
							SharedPreferences.Editor editor = items.edit();
							editor.putInt(Items.MITTENS_PACKED, cur_num - 1);
							pack_number.setText(""+(cur_num - 1));
//							if (cur_num - 1 == 0) {
//								mittens_pack.setVisibility(View.GONE);
//							}
							TextView number = (TextView) mittens_bring.findViewById(R.id.item_text);
								int bring_num = items.getInt(Items.MITTENS_TOBRING, 0);
								number.setText(""+(bring_num + 1));
								editor.putInt(Items.MITTENS_TOBRING, bring_num + 1);
							editor.commit();
						}
					}
				});
    		}
		 }
		 if (items.getBoolean(Items.LAPTOP, false) || !items.getBoolean(Items.LAPTOP, false)) {
			 boolean added = items.getBoolean(Items.LAPTOP, false);
			 LayoutInflater vi = (LayoutInflater) this
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		View laptop = vi.inflate(R.layout.objectlayout, null);
	 		ImageView image = (ImageView) laptop.findViewById(R.id.item_image);
				image.setImageResource(R.drawable.misc_laptop);
				TextView number = (TextView) laptop.findViewById(R.id.item_text);
				number.setText("+");
			if (!added) {
				laptop.setVisibility(View.GONE);
				laptop.setId(ItemActivity.LAPTOP_ID);
			}
	 		add_view.addView(laptop);
	 		
	 		if (items.getInt(Items.LAPTOP_PACKED, 0) >= 0) {
    			ImageView image_pack = (ImageView) laptop_pack.findViewById(R.id.item_image);
    			image_pack.setImageResource(R.drawable.misc_laptop);
    			TextView pack_number = (TextView) laptop_pack.findViewById(R.id.item_text);
    			pack_number.setText(""+items.getInt(Items.LAPTOP_PACKED, 0));
    			if (!added) {
    				laptop_pack.setVisibility(View.GONE);
    				laptop_pack.setId(ItemActivity.LAPTOP_PACK_ID);
    			}
        		packed_view.addView(laptop_pack);
        		
        		image_pack.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						TextView pack_number = (TextView) laptop_pack.findViewById(R.id.item_text);
						Log.i("ItemActivity", "unpacking");
						int cur_num = items.getInt(Items.LAPTOP_PACKED, 0);
						if (cur_num > 0) {
							SharedPreferences.Editor editor = items.edit();
							editor.putInt(Items.LAPTOP_PACKED, cur_num - 1);
							pack_number.setText(""+(cur_num - 1));
//							if (cur_num - 1 == 0) {
//								laptop_pack.setVisibility(View.GONE);
//							}
							TextView number = (TextView) laptop_bring.findViewById(R.id.item_text);
								int bring_num = items.getInt(Items.LAPTOP_TOBRING, 0);
								number.setText(""+(bring_num + 1));
								editor.putInt(Items.LAPTOP_TOBRING, bring_num + 1);
							editor.commit();
						}
					}
				});
    		}
		 }
	        switch (type) {
	        case PackTempActivity.SHIRTS: 
	        	if (items.getBoolean(Items.LONG_SHIRT, false)) {
	        		if (items.getInt(Items.LONG_SHIRT_TOBRING, 0) > 0 ) {
	        			ImageView image = (ImageView) long_shirt_bring.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_longsleeveshirt);
	        			TextView number = (TextView) long_shirt_bring.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LONG_SHIRT_TOBRING, 0));
		        		
		        		bring_view.addView(long_shirt_bring);
		        		
		        		image.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView number = (TextView) long_shirt_bring.findViewById(R.id.item_text);
								
								SharedPreferences editMode = getPreferences(MODE_PRIVATE);
								boolean isEdit = editMode.getBoolean(ItemActivity.EDIT_MODE, false);
								
								
								int cur_num = items.getInt(Items.LONG_SHIRT_TOBRING, 0);
								if (cur_num > 0) {
									SharedPreferences.Editor editor = items.edit();
									editor.putInt(Items.LONG_SHIRT_TOBRING, cur_num - 1);
									number.setText(""+(cur_num - 1));
									
									if (!isEdit) {
										TextView pack_number = (TextView) long_shirt_pack.findViewById(R.id.item_text);
										int pack_num = items.getInt(Items.LONG_SHIRT_PACKED, 0);
										pack_number.setText(""+(pack_num + 1));
										editor.putInt(Items.LONG_SHIRT_PACKED, pack_num + 1);
										//	long_shirt_pack.setVisibility(View.VISIBLE);
									}
									editor.commit();
								}
							}
						});
	        		}
	        	}
	        	if (items.getBoolean(Items.SHORT_SHIRT, false)) {
	        		if (items.getInt(Items.SHORT_SHIRT_TOBRING, 0) > 0 ) {
	        			ImageView image = (ImageView) short_shirt_bring.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.shirts_tshirt);
	        			TextView number = (TextView) short_shirt_bring.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.SHORT_SHIRT_TOBRING, 0));
		        		bring_view.addView(short_shirt_bring);
		        		
		        		image.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView number = (TextView) short_shirt_bring.findViewById(R.id.item_text);
								
								SharedPreferences editMode = getPreferences(MODE_PRIVATE);
								boolean isEdit = editMode.getBoolean(ItemActivity.EDIT_MODE, false);
								
								
								int cur_num = items.getInt(Items.SHORT_SHIRT_TOBRING, 0);
								if (cur_num > 0) {
									SharedPreferences.Editor editor = items.edit();
									editor.putInt(Items.SHORT_SHIRT_TOBRING, cur_num - 1);
									number.setText(""+(cur_num - 1));
									
									if (!isEdit) {
										TextView pack_number = (TextView) short_shirt_pack.findViewById(R.id.item_text);
										int pack_num = items.getInt(Items.SHORT_SHIRT_PACKED, 0);
										pack_number.setText(""+(pack_num + 1));
										editor.putInt(Items.SHORT_SHIRT_PACKED, pack_num + 1);
										//short_shirt_pack.setVisibility(View.VISIBLE);
									}
									editor.commit();
								}
							}
						});
	        		}
	        	}
	        	break;
	        case PackTempActivity.JACKETS: 
	        	if (items.getBoolean(Items.HOODY, false)) {
	        		if (items.getInt(Items.HOODY_TOBRING, 0) > 0 ) {
	        			ImageView image = (ImageView) hoody_bring.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.jackets_hoodies);
	        			TextView number = (TextView) hoody_bring.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.HOODY_TOBRING, 0));
		        		bring_view.addView(hoody_bring);
		        		
		        	   image.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView number = (TextView) hoody_bring.findViewById(R.id.item_text);
								
								SharedPreferences editMode = getPreferences(MODE_PRIVATE);
								boolean isEdit = editMode.getBoolean(ItemActivity.EDIT_MODE, false);
								
								
								int cur_num = items.getInt(Items.HOODY_TOBRING, 0);
								if (cur_num > 0) {
									SharedPreferences.Editor editor = items.edit();
									editor.putInt(Items.HOODY_TOBRING, cur_num - 1);
									number.setText(""+(cur_num - 1));
									
									if (!isEdit) {
										TextView pack_number = (TextView) hoody_pack.findViewById(R.id.item_text);
										int pack_num = items.getInt(Items.HOODY_PACKED, 0);
										pack_number.setText(""+(pack_num + 1));
										editor.putInt(Items.HOODY_PACKED, pack_num + 1);
										//hoody_pack.setVisibility(View.VISIBLE);
								
									}
									editor.commit();
								}
							}
						});
	        		}
	        	}
	        	break;
	        case PackTempActivity.FORMAL_WEAR:    	break;
	        case PackTempActivity.WINTER_GEAR:  
	        	if (items.getBoolean(Items.MITTENS, false)) {
	        		if (items.getInt(Items.MITTENS_TOBRING, 0) > 0 ) {
	        			ImageView image = (ImageView) mittens_bring.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.winter_mittens);
	        			TextView number = (TextView) mittens_bring.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.MITTENS_TOBRING, 0));
		        		bring_view.addView(mittens_bring);
		        		
		        		image.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView number = (TextView) mittens_bring.findViewById(R.id.item_text);
								
								SharedPreferences editMode = getPreferences(MODE_PRIVATE);
								boolean isEdit = editMode.getBoolean(ItemActivity.EDIT_MODE, false);
								
								
								int cur_num = items.getInt(Items.MITTENS_TOBRING, 0);
								if (cur_num > 0) {
									SharedPreferences.Editor editor = items.edit();
									editor.putInt(Items.MITTENS_TOBRING, cur_num - 1);
									number.setText(""+(cur_num - 1));
									
									if (!isEdit) {
										TextView pack_number = (TextView) mittens_pack.findViewById(R.id.item_text);
										int pack_num = items.getInt(Items.MITTENS_PACKED, 0);
										pack_number.setText(""+(pack_num + 1));
										editor.putInt(Items.MITTENS_PACKED, pack_num + 1);
										//mittens_pack.setVisibility(View.VISIBLE);
										
									}
									editor.commit();
								}
							}
						});
	        		}
	        	}
	        	break;
	        case PackTempActivity.PANTS: break;
	        case PackTempActivity.UNDERWEAR:  break;
	        case PackTempActivity.MISC:  
	        	if (items.getBoolean(Items.LAPTOP, false)) {
	        		if (items.getInt(Items.LAPTOP_TOBRING, 0) > 0 ) {
	        			ImageView image = (ImageView) laptop_bring.findViewById(R.id.item_image);
	        			image.setImageResource(R.drawable.misc_laptop);
	        			TextView number = (TextView) laptop_bring.findViewById(R.id.item_text);
	        			number.setText(""+items.getInt(Items.LAPTOP_TOBRING, 0));
		        		bring_view.addView(laptop_bring);
		        		
		        			image.setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								TextView number = (TextView) laptop_bring.findViewById(R.id.item_text);
								
								SharedPreferences editMode = getPreferences(MODE_PRIVATE);
								boolean isEdit = editMode.getBoolean(ItemActivity.EDIT_MODE, false);
								
								
								int cur_num = items.getInt(Items.LAPTOP_TOBRING, 0);
								if (cur_num > 0) {
									SharedPreferences.Editor editor = items.edit();
									editor.putInt(Items.LAPTOP_TOBRING, cur_num - 1);
									number.setText(""+(cur_num - 1));
									
									if (!isEdit) {
										TextView pack_number = (TextView) laptop_pack.findViewById(R.id.item_text);
										int pack_num = items.getInt(Items.LAPTOP_PACKED, 0);
										pack_number.setText(""+(pack_num + 1));
										editor.putInt(Items.LAPTOP_PACKED, pack_num + 1);
										//laptop_pack.setVisibility(View.VISIBLE);
										
									}
									editor.commit();
								}
							}
						});
	        		}
	        	
	        	}
	        	break;
	        default:  break;
	        }
	 }
}
