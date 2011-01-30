package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyPad extends LinearLayout implements OnClickListener {

	public static final String KEY_BACK = "back";
	
	private Button b_q;
	private Button b_w;
	private Button b_e;
	private Button b_r;
	private Button b_t;
	private Button b_y;
	private Button b_u;
	private Button b_i;
	private Button b_o;
	private Button b_p;
	private Button b_a;
	private Button b_s;
	private Button b_d;
	private Button b_f;
	private Button b_g;
	private Button b_h;
	private Button b_j;
	private Button b_k;
	private Button b_l;
	private Button b_mode;
	private Button b_z;
	private Button b_x;
	private Button b_c;
	private Button b_v;
	private Button b_b;
	private Button b_n;
	private Button b_m;
	private Button b_back;
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
		b_r = (Button) findViewById(R.id.KeyPadR);
		b_r.setOnClickListener(this);
		b_t = (Button) findViewById(R.id.KeyPadT);
		b_t.setOnClickListener(this);
		b_y = (Button) findViewById(R.id.KeyPadY);
		b_y.setOnClickListener(this);
		b_u = (Button) findViewById(R.id.KeyPadU);
		b_u.setOnClickListener(this);
		b_i = (Button) findViewById(R.id.KeyPadI);
		b_i.setOnClickListener(this);
		b_o = (Button) findViewById(R.id.KeyPadO);
		b_o.setOnClickListener(this);
		b_p = (Button) findViewById(R.id.KeyPadP);
		b_p.setOnClickListener(this);
		b_a = (Button) findViewById(R.id.KeyPadA);
		b_a.setOnClickListener(this);
		b_s = (Button) findViewById(R.id.KeyPadS);
		b_s.setOnClickListener(this);
		b_d = (Button) findViewById(R.id.KeyPadD);
		b_d.setOnClickListener(this);
		b_f = (Button) findViewById(R.id.KeyPadF);
		b_f.setOnClickListener(this);
		b_g = (Button) findViewById(R.id.KeyPadG);
		b_g.setOnClickListener(this);
		b_h = (Button) findViewById(R.id.KeyPadH);
		b_h.setOnClickListener(this);
		b_j = (Button) findViewById(R.id.KeyPadJ);
		b_j.setOnClickListener(this);
		b_k = (Button) findViewById(R.id.KeyPadK);
		b_k.setOnClickListener(this);
		b_l = (Button) findViewById(R.id.KeyPadL);
		b_l.setOnClickListener(this);
		mode = CrosswordsTile.TEXT_PEN;
		b_mode = (Button) findViewById(R.id.KeyPadMode);
		b_mode.setOnClickListener(this);
		b_z = (Button) findViewById(R.id.KeyPadZ);
		b_z.setOnClickListener(this);
		b_x = (Button) findViewById(R.id.KeyPadX);
		b_x.setOnClickListener(this);
		b_c = (Button) findViewById(R.id.KeyPadC);
		b_c.setOnClickListener(this);
		b_v = (Button) findViewById(R.id.KeyPadV);
		b_v.setOnClickListener(this);
		b_b = (Button) findViewById(R.id.KeyPadB);
		b_b.setOnClickListener(this);
		b_n = (Button) findViewById(R.id.KeyPadN);
		b_n.setOnClickListener(this);
		b_m = (Button) findViewById(R.id.KeyPadM);
		b_m.setOnClickListener(this);
		b_back = (Button) findViewById(R.id.KeyPadBack);
		b_back.setOnClickListener(this);

	}

	public void setKeyPadListener(KeyPadListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		String key = "";
		if (v.equals(b_q)) {
			key = "Q";
		} else if (v.equals(b_w)) {
			key = "W";
		} else if (v.equals(b_e)) {
			key = "E";
		} else if (v.equals(b_r)) {
			key = "R";
		} else if (v.equals(b_t)) {
			key = "T";
		} else if (v.equals(b_y)) {
			key = "Y";
		} else if (v.equals(b_u)) {
			key = "U";
		} else if (v.equals(b_i)) {
			key = "I";
		} else if (v.equals(b_o)) {
			key = "O";
		} else if (v.equals(b_p)) {
			key = "P";
		} else if (v.equals(b_a)) {
			key = "A";
		} else if (v.equals(b_s)) {
			key = "S";
		} else if (v.equals(b_d)) {
			key = "D";
		} else if (v.equals(b_f)) {
			key = "F";
		} else if (v.equals(b_g)) {
			key = "G";
		} else if (v.equals(b_h)) {
			key = "H";
		} else if (v.equals(b_j)) {
			key = "J";
		} else if (v.equals(b_k)) {
			key = "K";
		} else if (v.equals(b_l)) {
			key = "L";
		} else if (v.equals(b_mode)) {
			if (mode == CrosswordsTile.TEXT_PEN) {
				mode = CrosswordsTile.TEXT_PENCIL;
				b_mode.setText("-");
			} else if (mode == CrosswordsTile.TEXT_PENCIL) {
				mode = CrosswordsTile.TEXT_MULTI;
				b_mode.setText("*");
			} else if (mode == CrosswordsTile.TEXT_MULTI) {
				mode = CrosswordsTile.TEXT_PEN;
				b_mode.setText("+");
			}
			return;
		} else if (v.equals(b_z)) {
			key = "Z";
		} else if (v.equals(b_x)) {
			key = "X";
		} else if (v.equals(b_c)) {
			key = "C";
		} else if (v.equals(b_v)) {
			key = "V";
		} else if (v.equals(b_b)) {
			key = "B";
		} else if (v.equals(b_n)) {
			key = "N";
		} else if (v.equals(b_m)) {
			key = "M";
		} else if (v.equals(b_back)) {
			key = KEY_BACK;
		}
		listener.onKeyPad(mode, key);
	}

}
