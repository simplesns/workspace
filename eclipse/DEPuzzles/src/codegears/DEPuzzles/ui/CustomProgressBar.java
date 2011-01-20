package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomProgressBar extends LinearLayout {

	private LinearLayout left;
	private LinearLayout right;
	private float percentage;

	public CustomProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.ui_customprogressbar, this);
		left = (LinearLayout) findViewById(R.id.CustomProgressLeft);
		right = (LinearLayout) findViewById(R.id.CustomProgressRight);
	}

	public void setPercentage(float p) {
		p = Math.max(p, 0);
		percentage = Math.min(p, 100);
		left.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, percentage * 100));
		right.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT,
				100 - (percentage * 100)));
	}

}
