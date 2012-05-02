package edu.mit.packit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetTripInfoActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settripinfolayout);
        
        Button go_button = (Button)findViewById(R.id.go_button);
        
        go_button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
							PackActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
