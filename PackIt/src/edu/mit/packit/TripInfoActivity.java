package edu.mit.packit;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TripInfoActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripinfolayout);
        
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        ImageView settings_button = (ImageView) findViewById(R.id.settings_button);
        ImageView pack_button = (ImageView) findViewById(R.id.packing_button);
        
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
        pack_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						PackActivity.class);
				startActivity(intent);
			}
		});
        
        TabHost tabHost = getTabHost();  // The activity TabHost
        addTab(tabHost, Info.PACKING_TIP);
        addTab(tabHost, Info.WEATHER);
        addTab(tabHost, Info.REMINDERS);
        tabHost.setCurrentTab(0);
  }
  
  /**
   * Sets the tab view
   * @param tabHost
   * @param label
   * @param drawableId
   */
  private void addTab(TabHost tabHost, int type) {
  	Intent intent;
  	String label_string;
  	switch (type) {
  	case Info.PACKING_TIP: intent = new Intent(this, PackingTip.class); 
  	label_string = "Packing tips";
  			break;
  	case Info.WEATHER: intent = new Intent(this, Weather.class); 
  	label_string = "Weather";
  			break;
  	case Info.REMINDERS: intent = new Intent(this, Reminders.class);
  	label_string = "Reminders and stuff";
  		break;
  	default: intent = new Intent(this, PackingTip.class); 
  	label_string = "Packing tips";
  			break;
  	}

  	TabHost.TabSpec spec = tabHost.newTabSpec(""+type);

  	View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tabs_bg, null);

  	TextView label = (TextView) tabIndicator.findViewById(R.id.tabsText);
  	label.setText(label_string);

  	spec.setIndicator(tabIndicator);
  	spec.setContent(intent);

  	tabHost.addTab(spec);
  }
}
