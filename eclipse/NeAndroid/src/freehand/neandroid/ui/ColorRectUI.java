package freehand.neandroid.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/* ColorRectUI is use for draw rectangle for specific color
 */
public class ColorRectUI implements Drawable {

	private DrawPosition drawPosition;
	private boolean visible;
	private Paint paint;

	public ColorRectUI() {
		drawPosition = new DrawPosition();
		visible = true;
		paint = new Paint();
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	public void set(float x, float y, float w, float h) {
		drawPosition.set(x, y, w, h);
	}

	public void setAlign(int align) {
		drawPosition.setAlign(align);
	}

	@Override
	public void draw(Canvas canvas) {
		if (visible) {
			PointF point = drawPosition.getDrawPosition();
			PointF dimension = drawPosition.getDimension();
			canvas.drawRect(point.x, point.y, point.x + dimension.x, point.y + dimension.y, paint);
		}
	}

	@Override
	public void update(int elapse) {
	}

}
