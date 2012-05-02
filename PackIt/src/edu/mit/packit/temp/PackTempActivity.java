//package edu.mit.packit.temp;
//
//import edu.mit.packit.R;
//import android.app.TabActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TabHost;
//import android.widget.TextView;
//
///**
// * TabActivity containing the tabs for each of the categories of items the user can pack
// * @author Steph
// *
// */
//public class PackTempActivity extends TabActivity {
//	
//	public static final int SHIRTS = 0;
//	public static final int JACKETS = 1;
//	public static final int PANTS = 2;
//	public static final int FORMAL_WEAR = 3;
//	public static final int WINTER_GEAR = 4;
//	public static final int UNDERWEAR = 5;
//	public static final int MISC = 6;
//	
//	public static final String CATEGORY = "category";
//	
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.pack_tablayout);
//    	
//        TabHost tabHost = getTabHost();  // The activity TabHost
//        
//        addTab(tabHost, PackTempActivity.SHIRTS);
//        addTab(tabHost, PackTempActivity.JACKETS);
//        addTab(tabHost, PackTempActivity.PANTS);
//        addTab(tabHost, PackTempActivity.FORMAL_WEAR);
//        addTab(tabHost, PackTempActivity.WINTER_GEAR);
//        addTab(tabHost, PackTempActivity.UNDERWEAR);
//        addTab(tabHost, PackTempActivity.MISC);
//        
//        tabHost.setCurrentTab(0);
//        
//    }
//    
//    /**
//     * Sets the tab view
//     * @param tabHost
//     * @param label
//     * @param drawableId
//     */
//    private void addTab(TabHost tabHost, int type) {
//    	Intent intent = new Intent(this, ItemTempActivity.class);
//    	String label_string;
//    	int drawableId;
//    	switch (type) {
//    	case SHIRTS: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.SHIRTS);
//    	label_string = "Shirts";
//    	drawableId = R.drawable.category_shirt_48x75;
//    			break;
//    	case JACKETS: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.JACKETS);
//    	label_string = "Jackets";
//    	drawableId = R.drawable.category_outerwear_48x75;
//    			break;
//    	case PANTS: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.PANTS);
//    	label_string = "Pants";
//    	drawableId = R.drawable.category_pants_48x75;
//    		break;
//    	case FORMAL_WEAR: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.FORMAL_WEAR);
//    	label_string = "Formal wear";
//    	drawableId = R.drawable.category_formal_48x75;
//    		break;
//    	case WINTER_GEAR: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.WINTER_GEAR);
//    	label_string = "Winter gear";
//    	drawableId = R.drawable.category_access_48x75;
//    		break;
//    	case UNDERWEAR: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.UNDERWEAR);
//    	label_string = "Underwear";
//    	drawableId = R.drawable.category_underwear_48x75;
//    		break;
//    	case MISC: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.MISC);
//    	label_string = "Misc";
//    	drawableId = R.drawable.category_misc_48x75;
//    		break;
//    	default: intent.putExtra(PackTempActivity.CATEGORY, PackTempActivity.SHIRTS);
//    	drawableId = R.drawable.category_underwear_48x75;
//    	label_string = "Shirts";
//    			break;
//    	}
//
//    	TabHost.TabSpec spec = tabHost.newTabSpec(""+type);
//
//    	View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
//
//    	
//    	ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
//    	icon.setImageResource(drawableId);
//    	icon.setVisibility(View.VISIBLE);
//
//    	spec.setIndicator(tabIndicator);
//    	spec.setContent(intent);
//
//    	tabHost.addTab(spec);
//    	
//    }
//}
