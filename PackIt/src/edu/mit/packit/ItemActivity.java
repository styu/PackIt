package edu.mit.packit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.itemlayout);
	        
	        int type = this.getIntent().getIntExtra(PackActivity.CATEGORY, PackActivity.SHIRTS);
	        String category_type;
	        switch (type) {
	        case PackActivity.SHIRTS: category_type="shirts"; break;
	        case PackActivity.JACKETS: category_type = "jackets"; break;
	        case PackActivity.FORMAL_WEAR: category_type="formal wear"; break;
	        case PackActivity.WINTER_GEAR: category_type = "winter gear"; break;
	        case PackActivity.PANTS: category_type = "pants"; break;
	        case PackActivity.UNDERWEAR: category_type = "underwear"; break;
	        case PackActivity.MISC: category_type = "misc"; break;
	        default: category_type ="shirts"; break;
	        }
	        
	        TextView item_text = (TextView) findViewById(R.id.item_text);
	        item_text.setText(category_type);
	    }
}
