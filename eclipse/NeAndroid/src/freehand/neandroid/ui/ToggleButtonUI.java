package freehand.neandroid.ui;

import freehand.neandroid.Input.InputSystem;
import freehand.neandroid.Input.InputTouch;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;

public class ToggleButtonUI implements Drawable {

	private Bitmap bitmap;
	private boolean enableClick;
	private boolean visible;
	private boolean check;
	private ClickListener listener;
	private DrawPosition drawPosition;

	public ToggleButtonUI() {
		drawPosition = new DrawPosition();
		enableClick = true;
		visible = true;
		check = false;
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

	public void clicked() {
		check = !check;
	}
	
	public void setClicked(boolean clicked){
		check = clicked;
	}

	@Override
	public void update(int elapse) {
		if (enableClick) {
			InputTouch touch = InputSystem.getInstance().getTouch();
			if (touch.getState() == InputTouch.TOUCHED) {
				PointF position = touch.getPosition();
				if (drawPosition.contain(position.x, position.y)) {
					clicked();
					touch.reset();
					if (listener != null) {
						listener.onClick(this);
					}
				}
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		if ((visible) && (bitmap != null)) {
			Rect r = canvas.getClipBounds();
			PointF point = drawPosition.getDrawPosition();
			PointF dimension = drawPosition.getDimension();
			canvas.clipRect(point.x, point.y, point.x + dimension.x, point.y + dimension.y,
							Region.Op.REPLACE);
			if (!check) {
				canvas.drawBitmap(bitmap, point.x, point.y, null);
			} else {
				canvas.drawBitmap(bitmap, point.x - bitmap.getWidth() / 2, point.y, null);
			}
			canvas.clipRect(r, Region.Op.REPLACE);
		}
	}

}
