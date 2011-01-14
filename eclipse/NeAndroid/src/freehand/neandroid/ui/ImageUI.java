package freehand.neandroid.ui;

import freehand.neandroid.Input.InputSystem;
import freehand.neandroid.Input.InputTouch;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class ImageUI implements Drawable {

	protected Bitmap bitmap;
	protected DrawPosition drawPosition;
	protected boolean visible;
	private ClickListener listener;
	private boolean enableClick;

	public ImageUI() {
		drawPosition = new DrawPosition();
		enableClick = false;
		visible = true;
	}

	public ImageUI(Bitmap bitmap) {
		this();
		setBitmap(bitmap);
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

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		drawPosition.setDimension(bitmap.getWidth(), bitmap.getHeight());
	}
	
	public PointF getDimension(){
		return drawPosition.getDimension();
	}

	public void recycleImage() {
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
	}

	public void setPosition(float x, float y) {
		if (bitmap == null) {
			drawPosition.setPosition(x, y);
		} else {
			drawPosition.set(x, y, bitmap.getWidth(), bitmap.getHeight());
		}
	}
	
	public PointF getPosition(){
		return drawPosition.getPosition();
	}

	public void setAlign(int align) {
		drawPosition.setAlign(align);
	}

	@Override
	public void draw(Canvas canvas) {
		if ((bitmap != null) && (visible)) {
			if (!bitmap.isRecycled()) {
				PointF point = drawPosition.getDrawPosition();
				canvas.drawBitmap(bitmap, point.x, point.y, null);
			}
		}
	}

	@Override
	public void update(int elapse) {
		if (enableClick) {
			InputTouch touch = InputSystem.getInstance().getTouch();
			if (touch.getState() == InputTouch.TOUCHED) {
				PointF position = touch.getPosition();
				if (drawPosition.contain(position.x, position.y)) {
					touch.reset();
					listener.onClick(this);
				}
			}
		}
	}
}