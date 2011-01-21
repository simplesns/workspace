package codegears.DEPuzzles;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class NumberCrunchGame extends LinearLayout implements OnClickListener {

	public NumberCrunchGame(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_numbercrunchgame, this);
	}

	@Override
	public void onClick(View arg0) {
		
	}		
	
}
