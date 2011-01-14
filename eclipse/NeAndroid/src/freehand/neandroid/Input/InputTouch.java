package freehand.neandroid.Input;

import freehand.neandroid.util.Timer;
import android.graphics.PointF;
import android.view.MotionEvent;

public class InputTouch {

	public static final int NORMAL = 0;
	public static final int TOUCHING = 1;
	public static final int TOUCHED = 2;
	private static final int TIME_OUT = 200;

	private PointF position;
	private int state;
	private Timer timer;

	public InputTouch() {
		position = new PointF();
		state = NORMAL;
		timer = new Timer();
	}

	public void reset() {
		timer.stop();
		state = NORMAL;
	}

	public void touch(MotionEvent event) {
		int action = event.getAction();
		position.set(event.getX(), event.getY());
		if ((action == MotionEvent.ACTION_DOWN) || (action == MotionEvent.ACTION_MOVE)) {
			state = TOUCHING;
		} else if (action == MotionEvent.ACTION_UP) {
			state = TOUCHED;
			timer.start();
		}
	}

	public PointF getPosition() {
		return position;
	}

	public int getState() {
		if (state == TOUCHED) {
			timer.update();
			if (timer.getDuration() > TIME_OUT) {
				reset();
			}
		}
		return state;
	}
}