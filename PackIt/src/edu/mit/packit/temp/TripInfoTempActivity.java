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
// * Contains the three tabs for packing tips, weather, and reminders
// * @author Steph
// *
// */
//public class TripInfoActivity extends TabActivity {
//
//	public static final int PACKING_TIP = 0;
//	public static final int WEATHER = 1;
//	public static final int REMINDERS = 2;
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.tripinfo_tablayout);
//    	
//        TabHost tabHost = getTabHost();  // The activity TabHost
//        
//        addTab(tabHost, TripInfoActivity.PACKING_TIP);
//        addTab(tabHost, TripInfoActivity.WEATHER);
//        addTab(tabHost, TripInfoActivity.REMINDERS);
//        tabHost.setCurrentTab(0);
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
//    	case PACKING_TIP: intent = new Intent(this, PackingTip.class); 
//    	label_string = "Packing tips";
//    			break;
//    	case WEATHER: intent = new Intent(this, Weather.class); 
//    	label_string = "Weather";
//    			break;
//    	case REMINDERS: intent = new Intent(this, Reminders.class);
//    	label_string = "Reminders and stuff";
//    		break;
//    	default: intent = new Intent(this, PackTempActivity.class); 
//    	label_string = "Packing tips";
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
