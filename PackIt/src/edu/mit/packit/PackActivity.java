package edu.mit.packit;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class PackActivity extends TabActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.packlayout);
    	
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        ImageView settings_button = (ImageView) findViewById(R.id.settings_button);
        ImageView trip_button = (ImageView) findViewById(R.id.info_button);
        
        home_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						MainMenuActivity.class);
				startActivity(intent);
			}
		});
        settings_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						SettingsActivity.class);
				startActivity(intent);
			}
		});
        trip_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						TripInfoActivity.class);
				startActivity(intent);
			}
		});
        
        TabHost tabHost = getTabHost();  // The activity TabHost
        
        addTab(tabHost, Info.SHIRTS);
        addTab(tabHost, Info.JACKETS);
        addTab(tabHost, Info.PANTS);
        addTab(tabHost, Info.FORMAL);
        addTab(tabHost, Info.ACCESSORIES);
        addTab(tabHost, Info.UNDERGARMENTS);
        addTab(tabHost, Info.MISC);
        
        tabHost.setCurrentTab(0);
        
    }
	
	private void addTab(TabHost tabHost, int type) {
    	Intent intent = new Intent(this, ItemActivity.class);
    	int drawableId;
    	switch (type) {
    	case Info.SHIRTS: intent.putExtra(Info.CATEGORY, Info.SHIRTS);
    	drawableId = R.drawable.shirts_tab_bg_selector;
    			break;
    	case Info.JACKETS: intent.putExtra(Info.CATEGORY, Info.JACKETS);
    	drawableId = R.drawable.jackets_tab_bg_selector;
    			break;
    	case Info.PANTS: intent.putExtra(Info.CATEGORY, Info.PANTS);
    	drawableId = R.drawable.pants_tab_bg_selector;
    		break;
    	case Info.FORMAL: intent.putExtra(Info.CATEGORY, Info.FORMAL);
    	drawableId = R.drawable.formal_tab_bg_selector;
    		break;
    	case Info.ACCESSORIES: intent.putExtra(Info.CATEGORY, Info.ACCESSORIES);
    	drawableId = R.drawable.accessories_tab_bg_selector;
    		break;
    	case Info.UNDERGARMENTS: intent.putExtra(Info.CATEGORY, Info.UNDERGARMENTS);
    	drawableId = R.drawable.undergarments_tab_bg_selector;
    		break;
    	case Info.MISC: intent.putExtra(Info.CATEGORY, Info.MISC);
    	drawableId = R.drawable.misc_tab_bg_selector;
    		break;
    	default: intent.putExtra(Info.CATEGORY, Info.SHIRTS);
    	drawableId = R.drawable.shirts_tab_bg_selector;
    			break;
    	}

    	TabHost.TabSpec spec = tabHost.newTabSpec(""+type);

    	View tabIndicator = LayoutInflater.from(this).inflate(R.layout.category_tabs_bg, null);
    	LinearLayout tab = (LinearLayout) tabIndicator.findViewById(R.id.tabsLayout);
    	tab.setBackgroundResource(drawableId);
    	//View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);

    	
//    	ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
//    	icon.setImageResource(drawableId);
//    	icon.setVisibility(View.VISIBLE);

    	spec.setIndicator(tabIndicator);
    	spec.setContent(intent);

    	tabHost.addTab(spec);
    	
    }
}
