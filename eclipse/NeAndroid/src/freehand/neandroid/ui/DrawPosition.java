package freehand.neandroid.ui;

import android.graphics.PointF;

/* DrawPosition is using to store
 * - position as user input
 * - dimension of the object
 * - alignment value
 * then calculate the position of top, left corner which use to draw the object
 */
public class DrawPosition {

	public static final int LEFT = 0x0000;
	public static final int HCENTER = 0x0001;
	public static final int RIGHT = 0x0010;
	public static final int TOP = 0x0000;
	public static final int VCENTER = 0x0100;
	public static final int BOTTOM = 0x1000;
	private int align;
	private PointF position;
	private PointF dimension;
	private PointF drawPosition;

	public DrawPosition() {
		drawPosition = new PointF();
		position = new PointF();
		dimension = new PointF();
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
		calculate();
	}

	public void setDimension(float w, float h) {
		dimension.x = w;
		dimension.y = h;
		calculate();
	}

	public void set(float x, float y, float w, float h) {
		setPosition(x, y);
		setDimension(w, h);
		calculate();
	}

	private void calculate() {
		if (this.getHAlign() == LEFT) {
			drawPosition.x = position.x;
		} else if (this.getHAlign() == HCENTER) {
			drawPosition.x = position.x - dimension.x / 2;
		} else if (this.getHAlign() == RIGHT) {
			drawPosition.x = position.x - dimension.x;
		}
		if (this.getVAlign() == TOP) {
			drawPosition.y = position.y;
		} else if (this.getVAlign() == VCENTER) {
			drawPosition.y = position.y - dimension.y / 2;
		} else if (this.getVAlign() == BOTTOM) {
			drawPosition.y = position.y - dimension.y;
		}
	}

	public void setAlign(int align) {
		this.align = align;
		calculate();
	}

	public int getAlign() {
		return align;
	}

	public int getHAlign() {
		return align & 0x0011;
	}

	public int getVAlign() {
		return align & 0x1100;
	}

	public PointF getDrawPosition() {
		return drawPosition;
	}

	public PointF getPosition() {
		return position;
	}

	public PointF getDimension() {
		return dimension;
	}

	public boolean contain(float x, float y) {
		if ((drawPosition.x < x) && (x < drawPosition.x + dimension.x) && (drawPosition.y < y)
						&& (y < drawPosition.y + dimension.y)) {
			return true;
		}
		return false;
	}
}
