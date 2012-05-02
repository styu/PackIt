package edu.mit.packit;

import edu.mit.packit.temp.PackTempActivity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class PackActivity extends TabActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.packlayout);
    	
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
        
    }
}
