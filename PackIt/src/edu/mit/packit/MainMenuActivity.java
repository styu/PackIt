package edu.mit.packit;

import edu.mit.packit.temp.SetTripActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenulayout);
        
        Button continue_button = (Button)findViewById(R.id.continue_button);
        
        continue_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
							SetTripInfoActivity.class);
				startActivity(intent);
			}
		});
	}
}
