package edu.mit.packit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetTripActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.settriplayout);
	        
	        Button backButton = (Button) findViewById(R.id.back_button);
	        Button goButton = (Button) findViewById(R.id.go_button);
	        
	        backButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),
							PackItActivity.class);
					startActivity(intent);
				}
			});
	        
	        goButton.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),
							TripActivity.class);
					startActivity(intent);
				}
			});
	        
	    }
	 
}
