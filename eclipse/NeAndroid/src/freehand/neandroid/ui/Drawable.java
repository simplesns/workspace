package freehand.neandroid.ui;

import android.graphics.Canvas;

public interface Drawable {

	public abstract void update(int elapse);

	public abstract void draw(Canvas canvas);
}
