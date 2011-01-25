package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CrosswordsTile extends LinearLayout {

	private boolean black;
	private char result;

	private TextView number;
	private TextView text;
	private LinearLayout inner;

	public CrosswordsTile(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_crosswordstile, this);
		black = false;
		number = (TextView) findViewById(R.id.CrosswordsTileNumber);
		number.setText("");
		text = (TextView) findViewById(R.id.CrosswordsTileText);
		text.setText("");
		inner = (LinearLayout)findViewById(R.id.CrosswordsTileInner);
	}

	public void setBlack(boolean black) {
		this.black = black;
		inner.setBackgroundColor(0xFF000000);
	}

	public void setResult(char result) {
		this.result = result;
		text.setText(String.valueOf(result));
	}

}
