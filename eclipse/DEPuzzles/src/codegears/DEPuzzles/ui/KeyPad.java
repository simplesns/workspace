package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class KeyPad extends LinearLayout {

	public KeyPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.ui_keypad, this);
	}

}
