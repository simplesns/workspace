package freehand.neandroid.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/* BorderTextUI is decorated from TextUI in order to draw border of TextUI
 * ToDo: Change TextUI to Drawable so BorderTextUI can draw border to any Drawable object.
 */
public class BorderTextUI implements Drawable {

	private TextUI textUI;
	private float borderSize;
	private int textColor;
	private int borderColor;
	private PointF position;

	public BorderTextUI(TextUI textUI) {
		this.textUI = textUI;
		borderSize = 1;
	}

	public void setBorderSize(float size) {
		borderSize = size;
	}

	public void setBorderColor(int color) {
		borderColor = color;
	}

	@Override
	public void draw(Canvas canvas) {
		position = textUI.getPosition();
		float x = position.x;
		float y = position.y;
		Paint p = textUI.getPaint();
		textColor = p.getColor();
		p.setColor(borderColor);
		textUI.setPosition(x - borderSize, y - borderSize);
		textUI.draw(canvas);
		textUI.setPosition(x + borderSize, y - borderSize);
		textUI.draw(canvas);
		textUI.setPosition(x - borderSize, y + borderSize);
		textUI.draw(canvas);
		textUI.setPosition(x + borderSize, y + borderSize);
		textUI.draw(canvas);
		p.setColor(textColor);
		textUI.setPosition(x, y);
		textUI.draw(canvas);
	}

	@Override
	public void update(int elapse) {
		textUI.update(elapse);
	}

}
