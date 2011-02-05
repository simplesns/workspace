package codegears.DEPuzzles.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordSearchBoard extends LinearLayout {

	private LinearLayout[] line;
	private WordSearchTile[] tile;

	public WordSearchBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(LinearLayout.VERTICAL);
		line = new LinearLayout[10];
		tile = new WordSearchTile[10 * 10];
		for (int i = 0; i < 10; i++) {
			line[i] = new LinearLayout(context);
			for (int j = 0; j < 10; j++) {
				tile[(i * 10) + j] = new WordSearchTile(context);
				tile[(i * 10) + j].setText("A");
				line[i].addView(tile[(i * 10) + j], new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			}
			this.addView(line[i], new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		}
		this.invalidate();
		setWillNotDraw(false);
	}

	public void setBoardData(String[] grid) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				tile[(i * 10) + j].setText(String.valueOf(grid[i].charAt(j)));
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("Board Touch");
		return true;
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(0xFFFF0000);
		canvas.drawRect(new Rect(-20, -20, 2000, 2000), p);
	}
}