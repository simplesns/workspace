package freehand.neandroid.Input;

import android.view.MotionEvent;

public class InputSystem {

	private static InputSystem instance;
	private InputKey key;
	private InputTouch touch;

	private InputSystem() {
		key = new InputKey();
		touch = new InputTouch();
	}

	public static InputSystem getInstance() {
		if (instance == null) {
			instance = new InputSystem();
		}
		return instance;
	}

	public void touch(MotionEvent event) {
		try {
				touch.touch(event);
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
	}

	public void keyUp(int keyCode) {
		key.keyUp(keyCode);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
	}

	public void keyDown(int keyCode) {
		key.keyDown(keyCode);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
	}

	public InputKey getKey() {
		return key;
	}

	public InputTouch getTouch() {
		return touch;
	}
}