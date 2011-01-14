package freehand.neandroid.ui;

import freehand.neandroid.Input.InputSystem;
import freehand.neandroid.Input.InputTouch;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;

public class ButtonUI implements Drawable {

	public static final int NORMAL = 0;
	public static final int CLICKED = 1;
	private static final int CLICK_DURATION = 250;

	private Bitmap bitmap;
	private int state;
	private int time;
	private boolean enableClick;
	private boolean visible;
	private ClickListener listener;
	private DrawPosition drawPosition;

	public ButtonUI() {
		drawPosition = new DrawPosition();
		state = NORMAL;
		time = 0;
		enableClick = true;
		visible = true;
	}

	public void recycleImage() {
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
	}

	public void setEnableClick(boolean enableClick) {
		this.enableClick = enableClick;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}

	public void setPosition(float x, float y) {
		drawPosition.setPosition(x, y);
	}

	public void setAlign(int align) {
		drawPosition.setAlign(align);
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		drawPosition.setDimension(bitmap.getWidth() / 2, bitmap.getHeight());
	}

	@Override
	public void draw(Canvas canvas) {
		if ((visible) && (bitmap != null)) {
			if (!bitmap.isRecycled()) {
				Rect r = canvas.getClipBounds();
				PointF point = drawPosition.getDrawPosition();
				PointF dimension = drawPosition.getDimension();
				canvas.clipRect(point.x, point.y, point.x + dimension.x, point.y + dimension.y,
								Region.Op.REPLACE);
				if (state == NORMAL) {
					canvas.drawBitmap(bitmap, point.x, point.y, null);
				} else {
					canvas.drawBitmap(bitmap, point.x - bitmap.getWidth() / 2, point.y, null);
				}
				canvas.clipRect(r, Region.Op.REPLACE);
			}
		}
	}

	@Override
	public void update(int elapse) {
		if (state == NORMAL) {
			if (enableClick) {
				InputTouch touch = InputSystem.getInstance().getTouch();
				if (touch.getState() == InputTouch.TOUCHED) {
					PointF position = touch.getPosition();
					if (drawPosition.contain(position.x, position.y)) {
						clicked();
						touch.reset();
					}
				}
			}
		} else if (state == CLICKED) {
			time += elapse;
			if (time > CLICK_DURATION) {
				if (listener != null) {
					listener.onClick(this);
				}
				state = NORMAL;
			}
		}
	}

	public void clicked() {
		state = CLICKED;
		time = 0;
	}

}
