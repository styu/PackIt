package edu.mit.packit;

import edu.mit.packit.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

/***
 * Activity for weather information
 * @author Steph
 *
 */
public class Weather extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.weatherlayout);
	        
	        // Create a LinearLayout in which to add the ImageView
	        LinearLayout mLinearLayout = new LinearLayout(this);
	     // Instantiate an ImageView and define its properties
	        ImageView i = new ImageView(this);
	        i.setImageResource(R.drawable.weather_1);
	        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
	        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
	        LayoutParams.WRAP_CONTENT));

	        // Add the ImageView to the layout and set the layout as the content view
	        mLinearLayout.addView(i);
	        setContentView(mLinearLayout);
	        
	    }
}
