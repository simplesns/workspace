package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class NumPad extends LinearLayout implements OnClickListener {

	public static final int NUMPAD_0 = 0;
	public static final int NUMPAD_1 = 1;
	public static final int NUMPAD_2 = 2;
	public static final int NUMPAD_3 = 3;
	public static final int NUMPAD_4 = 4;
	public static final int NUMPAD_5 = 5;
	public static final int NUMPAD_6 = 6;
	public static final int NUMPAD_7 = 7;
	public static final int NUMPAD_8 = 8;
	public static final int NUMPAD_9 = 9;
	public static final int NUMPAD_x = 10;

	private Button[] button;
	private NumPadListener listener;

	public NumPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.numbercrunch, this);

		button[0] = (Button) findViewById(R.id.ButtonNum0);
		button[1] = (Button) findViewById(R.id.ButtonNum1);
		button[2] = (Button) findViewById(R.id.ButtonNum2);
		button[3] = (Button) findViewById(R.id.ButtonNum3);
		button[4] = (Button) findViewById(R.id.ButtonNum4);
		button[5] = (Button) findViewById(R.id.ButtonNum5);
		button[6] = (Button) findViewById(R.id.ButtonNum6);
		button[7] = (Button) findViewById(R.id.ButtonNum7);
		button[8] = (Button) findViewById(R.id.ButtonNum8);
		button[9] = (Button) findViewById(R.id.ButtonNum9);
		button[10] = (Button) findViewById(R.id.ButtonNumDelete);

		for (int i = 0; i < button.length; i++) {
			button[i].setOnClickListener(this);
		}
	}

	public void setNumPadListener(NumPadListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View view) {
		for (int i = 0; i < button.length; i++) {
			Button b = button[i];
			if (b.equals(view)) {
				if (listener != null) {
					if (b.equals(button[i])) {
						listener.onNumPadClicked(i);
					}
				}
			}
		}
	}
}
