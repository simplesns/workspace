package freehand.neandroid.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class TextUI implements Drawable {

	private String text;
	private Paint paint;
	private PointF position;
	private boolean visible;
	
	public TextUI(){
		position = new PointF();
		visible = true;
		this.text = "";
	}

	public TextUI(String text) {
		this();
		this.text = text;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	public void setText(String text){
		this.text = text;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	
	public Paint getPaint(){
		return paint;
	}
	
	public PointF getPosition(){
		return position;
	}

	@Override
	public void draw(Canvas canvas) {
		if (visible) {
			canvas.drawText(text, position.x, position.y, paint);
		}
	}

	@Override
	public void update(int elapse) {
	}

}
