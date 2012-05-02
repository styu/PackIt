package edu.mit.packit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PackItActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.startpagelayout);
        
        Button start_button = (Button) findViewById(R.id.start_button);
        
        start_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
				startActivity(intent);
			}
		});
    }
	
}
