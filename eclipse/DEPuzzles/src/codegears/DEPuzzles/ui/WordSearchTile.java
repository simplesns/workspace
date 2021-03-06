package codegears.DEPuzzles.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

public class WordSearchTile extends TextView {

	public WordSearchTile(Context context) {
		super(context);
		setGravity(Gravity.CENTER);
		setBackgroundColor(0xFFFFFFFF);
		setTextColor(0xFF000000);
		setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
}
