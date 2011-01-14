package freehand.neandroid.ui;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;

/* 
 * ImageTransformUI is subclass of ImageUI for use matrix
 * during draw
 */
public class ImageTransformUI extends ImageUI {

	private Matrix matrix;

	public ImageTransformUI() {
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	@Override
	public void draw(Canvas canvas) {
		if ((bitmap != null) && (visible)) {
			if (!bitmap.isRecycled()) {
				PointF point = drawPosition.getDrawPosition();
				matrix.postTranslate(point.x, point.y);
				canvas.drawBitmap(bitmap, matrix, null);
				matrix.postTranslate(-point.x, -point.y);
			}
		}
	}

	@Override
	public void update(int elapse) {
		// TODO Auto-generated method stub
		super.update(elapse);
	}

}
