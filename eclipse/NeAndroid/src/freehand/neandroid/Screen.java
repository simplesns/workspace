package freehand.neandroid;

import java.util.Vector;

import android.content.Intent;
import android.graphics.Canvas;
import freehand.neandroid.ui.Drawable;

public class Screen {

	protected GameActivity activity;
	private boolean active;
	private Vector<Drawable> drawObject;

	public Screen(GameActivity activity) {
		this.activity = activity;
		active = true;
		drawObject = new Vector<Drawable>();
	}
	
	public void setGameActivity(GameActivity activity){
		this.activity = activity;
	}
	
	public void setActive(boolean b){
		active = b;
	}

	public void launchActivity(Class<?> cls, int requestCode) {
		Intent intent = new Intent(activity, cls);
		if (requestCode >= 0) {
			activity.startActivityForResult(intent, requestCode);
		} else {
			activity.startActivity(intent);
		}
	}

	public void add(Drawable object) {
		drawObject.add(object);
	}

	public void remove(Drawable object) {
		drawObject.remove(object);
	}

	public boolean getActive() {
		return active;
	}

	public void end() {
		active = false;
		activity.finish();
	}

	public void update(int elapse) {
		for (Drawable draw : drawObject) {
			draw.update(elapse);
		}
	}

	public void draw(Canvas canvas) {
		for (Drawable draw : drawObject) {
			draw.draw(canvas);
		}
	}
}
