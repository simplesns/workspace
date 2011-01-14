package freehand.neandroid;

import freehand.neandroid.Input.InputSystem;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/* Game Activity is use for control the Screen for game.
 * It set full screen automatically during onCreate()
 */
public abstract class GameActivity extends Activity {

	private InputSystem inputSystem;
	private GameSurfaceView surface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullscreen(this);
		inputSystem = InputSystem.getInstance();
		System.gc();
	}

	/*
	 * set function is using to set View and Screen to GameActivity Call this
	 * function during onCreate() once
	 */
	protected void set(GameSurfaceView surface, Screen screen) {
		this.surface = surface;
		screen.setActive(true);
		surface.setScreen(screen);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (surface != null) {
			surface.pause();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (surface != null) {
			surface.resume();
		}
	}

	public void onPause() {
		super.onPause();
		if (surface != null) {
			surface.stopProcessing();
		}
	}

	public void onDestroy() {
		super.onDestroy();
		if (surface != null) {
			surface.destroy();
			surface = null;
		}
	}

	public void end() {
		if (surface != null) {
			surface.pause();
			surface.destroy();
		}
	}

	/*
	 * setFullscreen() is using for set fullscreen to any activity this function
	 * can be called without subclassing GameActivity
	 */
	public static void setFullscreen(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		inputSystem.keyDown(keyCode);
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		inputSystem.keyUp(keyCode);
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		inputSystem.touch(event);
		return false;
	}

	public abstract void changeScreen(int requestCode);
}
