package freehand.neandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private GameThread gThread;

	public GameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
	}

	public void setScreen(Screen screen) {
		if (gThread == null) {
			gThread = new GameThread(screen);
			gThread.start();
		} else {
			gThread.setScreen(screen);
		}
	}

	public void pause() {
		gThread.pause();
	}

	public void resume() {
		gThread.restart();
	}

	public void stopProcessing() {
		gThread.stopProcessing();
	}

	public void destroy() {
		gThread = null;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gThread.setSurfaceHolder(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

}
