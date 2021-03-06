package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KeyPadButton extends LinearLayout {

	private TextView number;
	private TextView key;

	public KeyPadButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.ui_keypadbutton, this);
		number = (TextView) findViewById(R.id.KeyPadButtonNumber);
		key = (TextView) findViewById(R.id.KeyPadButtonText);
		number.setVisibility(View.GONE);
		number.setText("0");
		invalidate();
	}

	public void setKey(String key) {
		this.key.setText(key);
	}
	
	public String getKey(){
		return key.getText().toString();
	}

	public int getNumber() {
		return Integer.parseInt(number.getText().toString());
	}

	public void setNumber(int num) {
		if (num == 0) {
			number.setText("0");
			number.setVisibility(View.INVISIBLE);
		} else {
			number.setVisibility(View.VISIBLE);
			number.setText(String.valueOf(num));
		}
	}

}
