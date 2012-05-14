package edu.mit.packit.temp;
//package edu.mit.packit.temp;
//
//import edu.mit.packit.R;
//import android.app.TabActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TabHost;
//import android.widget.TextView;
//
///**
// * Activity that contains the Packing and Trip info tabs
// * @author Steph
// *
// */
//public class TripActivity extends TabActivity {
//
//	public static final int PACK = 0;
//	public static final int TRIP_INFO = 1;
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.trip_tablayout);
//    	
//        TabHost tabHost = getTabHost();  // The activity TabHost
//        
//        addTab(tabHost, TripActivity.PACK);
//        addTab(tabHost, TripActivity.TRIP_INFO);
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
//    	Intent intent;
//    	String label_string;
//    	switch (type) {
//    	case PACK: intent = new Intent(this, PackTempActivity.class); 
//    	label_string = "Packing";
//    			break;
//    	case TRIP_INFO: intent = new Intent(this, TripInfoActivity.class); 
//    	label_string = "Trip info";
//    			break;
//    	default: intent = new Intent(this, PackTempActivity.class); 
//    	label_string = "Packing";
//    			break;
//    	}
//
//    	TabHost.TabSpec spec = tabHost.newTabSpec(""+type);
//
//    	View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tabs_bg, null);
//
//    	TextView label = (TextView) tabIndicator.findViewById(R.id.tabsText);
//    	label.setText(label_string);
//
//    	spec.setIndicator(tabIndicator);
//    	spec.setContent(intent);
//
//    	tabHost.addTab(spec);
//    }
//}
