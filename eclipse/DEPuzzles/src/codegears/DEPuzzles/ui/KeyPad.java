package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyPad extends LinearLayout implements OnClickListener {

	private Button b_q;
	private Button b_w;
	private Button b_e;
	private Button b_mode;
	private KeyPadListener listener;
	private int mode; // CrosswordsTile.TEXT

	public KeyPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.ui_keypad, this);
		b_q = (Button) findViewById(R.id.KeyPadQ);
		b_q.setOnClickListener(this);
		b_w = (Button) findViewById(R.id.KeyPadW);
		b_w.setOnClickListener(this);
		b_e = (Button) findViewById(R.id.KeyPadE);
		b_e.setOnClickListener(this);
		mode = CrosswordsTile.TEXT_PEN;
		b_mode = (Button) findViewById(R.id.KeyPadMode);
		b_mode.setOnClickListener(this);
	}

	public void setKeyPadListener(KeyPadListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		String key = "";
		if (v.equals(b_q)) {
			key = "Q";
		} else if (v.equals(b_e)) {
			key = "E";
		} else if (v.equals(b_w)) {
			key = "W";
		} else if (v.equals(b_mode)) {
			if(mode == CrosswordsTile.TEXT_PEN){
				mode = CrosswordsTile.TEXT_PENCIL;
				b_mode.setText("-");
			} else if(mode == CrosswordsTile.TEXT_PENCIL){
				mode = CrosswordsTile.TEXT_MULTI;
				b_mode.setText("*");
			} else if(mode == CrosswordsTile.TEXT_MULTI){
				mode = CrosswordsTile.TEXT_PEN;
				b_mode.setText("+");
			}
			return;
		}
		listener.onKeyPad(mode, key);
	}

}
