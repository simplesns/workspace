package freehand.neandroid.Input;

import freehand.neandroid.util.Timer;
import android.view.KeyEvent;

public class InputKey {

	private static final int TIME_OUT = 200;
	public static final int NORMAL = 0;
	public static final int HOLDING = 1;
	public static final int CLICKED = 2;

	private int[] key;
	private Timer timer;

	public InputKey() {
		key = new int[KeyEvent.MAX_KEYCODE];
		reset();
		timer = new Timer();
	}

	public void reset() {
		for (int i = 0; i < key.length; i++) {
			key[i] = NORMAL;
		}
	}

	public void keyDown(int keyCode) {
		key[keyCode] = HOLDING;
	}

	public void keyUp(int keyCode) {
		key[keyCode] = CLICKED;
		timer.start();
	}

	public void clear(int keyCode) {
		key[keyCode] = NORMAL;
	}

	public int getKeyState(int keyCode) {
		if (key[keyCode] == CLICKED) {
			timer.update();
			if (timer.getDuration() > TIME_OUT) {
				clear(keyCode);
			}
		}
		return key[keyCode];
	}
}