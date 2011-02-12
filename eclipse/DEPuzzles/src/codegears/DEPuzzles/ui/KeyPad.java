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

	private KeyPadButton b_q;
	private KeyPadButton b_w;
	private KeyPadButton b_e;
	private KeyPadButton b_r;
	private KeyPadButton b_t;
	private KeyPadButton b_y;
	private KeyPadButton b_u;
	private KeyPadButton b_i;
	private KeyPadButton b_o;
	private KeyPadButton b_p;
	private KeyPadButton b_a;
	private KeyPadButton b_s;
	private KeyPadButton b_d;
	private KeyPadButton b_f;
	private KeyPadButton b_g;
	private KeyPadButton b_h;
	private KeyPadButton b_j;
	private KeyPadButton b_k;
	private KeyPadButton b_l;
	private Button b_mode;
	private KeyPadButton b_z;
	private KeyPadButton b_x;
	private KeyPadButton b_c;
	private KeyPadButton b_v;
	private KeyPadButton b_b;
	private KeyPadButton b_n;
	private KeyPadButton b_m;
	private Button b_back;
	private KeyPadListener listener;
	private int mode; // CrosswordsTile.TEXT

	public KeyPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.ui_keypad, this);
		b_q = (KeyPadButton) findViewById(R.id.KeyPadQ);
		b_q.setOnClickListener(this);
		b_q.setKey("Q");
		b_w = (KeyPadButton) findViewById(R.id.KeyPadW);
		b_w.setOnClickListener(this);
		b_w.setKey("W");
		b_e = (KeyPadButton) findViewById(R.id.KeyPadE);
		b_e.setOnClickListener(this);
		b_e.setKey("E");
		b_r = (KeyPadButton) findViewById(R.id.KeyPadR);
		b_r.setOnClickListener(this);
		b_r.setKey("R");
		b_t = (KeyPadButton) findViewById(R.id.KeyPadT);
		b_t.setOnClickListener(this);
		b_t.setKey("T");
		b_y = (KeyPadButton) findViewById(R.id.KeyPadY);
		b_y.setOnClickListener(this);
		b_y.setKey("Y");
		b_u = (KeyPadButton) findViewById(R.id.KeyPadU);
		b_u.setOnClickListener(this);
		b_u.setKey("U");
		b_i = (KeyPadButton) findViewById(R.id.KeyPadI);
		b_i.setOnClickListener(this);
		b_i.setKey("I");
		b_o = (KeyPadButton) findViewById(R.id.KeyPadO);
		b_o.setOnClickListener(this);
		b_o.setKey("O");
		b_p = (KeyPadButton) findViewById(R.id.KeyPadP);
		b_p.setOnClickListener(this);
		b_p.setKey("P");
		b_a = (KeyPadButton) findViewById(R.id.KeyPadA);
		b_a.setOnClickListener(this);
		b_a.setKey("A");
		b_s = (KeyPadButton) findViewById(R.id.KeyPadS);
		b_s.setOnClickListener(this);
		b_s.setKey("S");
		b_d = (KeyPadButton) findViewById(R.id.KeyPadD);
		b_d.setOnClickListener(this);
		b_d.setKey("D");
		b_f = (KeyPadButton) findViewById(R.id.KeyPadF);
		b_f.setOnClickListener(this);
		b_f.setKey("F");
		b_g = (KeyPadButton) findViewById(R.id.KeyPadG);
		b_g.setOnClickListener(this);
		b_g.setKey("G");
		b_h = (KeyPadButton) findViewById(R.id.KeyPadH);
		b_h.setOnClickListener(this);
		b_h.setKey("H");
		b_j = (KeyPadButton) findViewById(R.id.KeyPadJ);
		b_j.setOnClickListener(this);
		b_j.setKey("J");
		b_k = (KeyPadButton) findViewById(R.id.KeyPadK);
		b_k.setOnClickListener(this);
		b_k.setKey("K");
		b_l = (KeyPadButton) findViewById(R.id.KeyPadL);
		b_l.setOnClickListener(this);
		b_l.setKey("L");
		mode = CrosswordsTile.TEXT_PEN;
		b_mode = (Button) findViewById(R.id.KeyPadMode);
		b_mode.setOnClickListener(this);
		b_z = (KeyPadButton) findViewById(R.id.KeyPadZ);
		b_z.setOnClickListener(this);
		b_z.setKey("Z");
		b_x = (KeyPadButton) findViewById(R.id.KeyPadX);
		b_x.setOnClickListener(this);
		b_x.setKey("X");
		b_c = (KeyPadButton) findViewById(R.id.KeyPadC);
		b_c.setOnClickListener(this);
		b_c.setKey("C");
		b_v = (KeyPadButton) findViewById(R.id.KeyPadV);
		b_v.setOnClickListener(this);
		b_v.setKey("V");
		b_b = (KeyPadButton) findViewById(R.id.KeyPadB);
		b_b.setOnClickListener(this);
		b_b.setKey("B");
		b_n = (KeyPadButton) findViewById(R.id.KeyPadN);
		b_n.setOnClickListener(this);
		b_n.setKey("N");
		b_m = (KeyPadButton) findViewById(R.id.KeyPadM);
		b_m.setOnClickListener(this);
		b_m.setKey("M");
		b_back = (Button) findViewById(R.id.KeyPadBack);
		b_back.setOnClickListener(this);
	}

	public void setKeyPadListener(KeyPadListener listener) {
		this.listener = listener;
	}

	public int getNumber(String key) {
		KeyPadButton b = findButtonByKey(key);
		if (b != null) {
			return b.getNumber();
		}
		return 0;
	}

	public KeyPadButton findButtonByKey(String key) {
		if (key.equals("Q")) {
			return b_q;
		} else if (key.equals("W")) {
			return b_w;
		} else if (key.equals("E")) {
			return b_e;
		} else if (key.equals("R")) {
			return b_r;
		} else if (key.equals("T")) {
			return b_t;
		} else if (key.equals("Y")) {
			return b_y;
		} else if (key.equals("U")) {
			return b_u;
		} else if (key.equals("I")) {
			return b_i;
		} else if (key.equals("O")) {
			return b_o;
		} else if (key.equals("P")) {
			return b_p;
		} else if (key.equals("A")) {
			return b_a;
		} else if (key.equals("S")) {
			return b_s;
		} else if (key.equals("D")) {
			return b_d;
		} else if (key.equals("F")) {
			return b_f;
		} else if (key.equals("G")) {
			return b_g;
		} else if (key.equals("H")) {
			return b_h;
		} else if (key.equals("J")) {
			return b_j;
		} else if (key.equals("K")) {
			return b_k;
		} else if (key.equals("L")) {
			return b_l;
		} else if (key.equals("Z")) {
			return b_z;
		} else if (key.equals("X")) {
			return b_x;
		} else if (key.equals("C")) {
			return b_c;
		} else if (key.equals("V")) {
			return b_v;
		} else if (key.equals("B")) {
			return b_b;
		} else if (key.equals("N")) {
			return b_n;
		} else if (key.equals("M")) {
			return b_m;
		}
		return null;
	}

	public void setNumber(String key, int number) {
		KeyPadButton b = findButtonByKey(key);
		if (b != null) {
			b.setNumber(number);
		}
	}

	public void clear() {
		setNumber("Q", 0);
		setNumber("W", 0);
		setNumber("E", 0);
		setNumber("R", 0);
		setNumber("T", 0);
		setNumber("Y", 0);
		setNumber("U", 0);
		setNumber("I", 0);
		setNumber("O", 0);
		setNumber("P", 0);
		setNumber("A", 0);
		setNumber("S", 0);
		setNumber("D", 0);
		setNumber("F", 0);
		setNumber("G", 0);
		setNumber("H", 0);
		setNumber("J", 0);
		setNumber("K", 0);
		setNumber("L", 0);
		setNumber("Z", 0);
		setNumber("X", 0);
		setNumber("C", 0);
		setNumber("V", 0);
		setNumber("B", 0);
		setNumber("N", 0);
		setNumber("M", 0);
	}

	public String getRandomEmptyKey() {
		String key = "";
		if (b_q.getNumber() == 0) {
			key += "Q";
		}
		if (b_w.getNumber() == 0) {
			key += "W";
		}
		if (b_e.getNumber() == 0) {
			key += "E";
		}
		if (b_r.getNumber() == 0) {
			key += "R";
		}
		if (b_t.getNumber() == 0) {
			key += "T";
		}
		if (b_y.getNumber() == 0) {
			key += "Y";
		}
		if (b_u.getNumber() == 0) {
			key += "U";
		}
		if (b_i.getNumber() == 0) {
			key += "I";
		}
		if (b_o.getNumber() == 0) {
			key += "O";
		}
		if (b_p.getNumber() == 0) {
			key += "P";
		}
		if (b_a.getNumber() == 0) {
			key += "A";
		}
		if (b_s.getNumber() == 0) {
			key += "S";
		}
		if (b_d.getNumber() == 0) {
			key += "D";
		}
		if (b_f.getNumber() == 0) {
			key += "F";
		}
		if (b_g.getNumber() == 0) {
			key += "G";
		}
		if (b_h.getNumber() == 0) {
			key += "H";
		}
		if (b_j.getNumber() == 0) {
			key += "J";
		}
		if (b_k.getNumber() == 0) {
			key += "K";
		}
		if (b_l.getNumber() == 0) {
			key += "L";
		}
		if (b_z.getNumber() == 0) {
			key += "Z";
		}
		if (b_x.getNumber() == 0) {
			key += "X";
		}
		if (b_c.getNumber() == 0) {
			key += "C";
		}
		if (b_v.getNumber() == 0) {
			key += "V";
		}
		if (b_b.getNumber() == 0) {
			key += "B";
		}
		if (b_n.getNumber() == 0) {
			key += "N";
		}
		if (b_m.getNumber() == 0) {
			key += "M";
		}
		if (key.length() == 0) {
			return null;
		} else {
			int ran = (int) (Math.random() * key.length());
			return String.valueOf(key.charAt(ran));
		}
	}

	public void press(String key) {
		KeyPadButton b = findButtonByKey(key);
		if (b != null) {
			onClick(b);
		} else {
			if (key.equals("back")) {
				onClick(b_back);
			} else if (key.equals("mode")) {
				onClick(b_mode);
			}
		}
	}

	@Override
	public void onClick(View v) {
		String key = "";
		if (v instanceof KeyPadButton) {
			key = ((KeyPadButton) v).getKey();
		}
		if (v.equals(b_mode)) {
			if (mode == CrosswordsTile.TEXT_PEN) {
				mode = CrosswordsTile.TEXT_PENCIL;
				b_mode.setBackgroundResource(R.drawable.keypad_pencil);
			} else if (mode == CrosswordsTile.TEXT_PENCIL) {
				mode = CrosswordsTile.TEXT_MULTI;
				b_mode.setBackgroundResource(R.drawable.keypad_multi);
			} else if (mode == CrosswordsTile.TEXT_MULTI) {
				mode = CrosswordsTile.TEXT_PEN;
				b_mode.setBackgroundResource(R.drawable.keypad_pen);
			}
			return;
		} else if (v.equals(b_back)) {
			key = KEY_BACK;
		}
		listener.onKeyPad(mode, key);
	}

}
