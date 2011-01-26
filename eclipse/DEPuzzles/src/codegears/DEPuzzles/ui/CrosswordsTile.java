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
	
	private int state;
	public static final int STATE_NORMAL = 0;
	public static final int STATE_WORDSELECTED = 1;
	public static final int STATE_TILESELECTED = 2;

	public CrosswordsTile(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_crosswordstile, this);
		state = STATE_NORMAL;
		black = false;
		number = (TextView) findViewById(R.id.CrosswordsTileNumber);
		number.setText("");
		text = (TextView) findViewById(R.id.CrosswordsTileText);
		text.setText("");
		inner = (LinearLayout) findViewById(R.id.CrosswordsTileInner);
	}

	public void setBlack(boolean black) {
		this.black = black;
		inner.setBackgroundColor(0xFF000000);
	}

	public void setResult(char result) {
		this.result = result;
		text.setText(String.valueOf(result));
	}
	
	public char getResult(){
		return result;
	}
	
	public void unselect(){
		setStateNormal();
	}
	
	public void wordSelected(){
		setStateWordSelected();
	}
	
	public void tileSelected(){
		setStateTileSelected();
	}
	
	private void setStateNormal(){
		state = STATE_NORMAL;
		inner.setBackgroundColor(0xFFFFFFFF);
	}
	private void setStateWordSelected(){
		state = STATE_WORDSELECTED;
		inner.setBackgroundColor(0xFFFF0000);
	}
	private void setStateTileSelected(){
		state = STATE_TILESELECTED;
		inner.setBackgroundColor(0xFFFFFF00);
	}

}
