package freehand.neandroid;

import freehand.neandroid.util.Timer;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	private Screen screen;
	private Timer timer;
	private SurfaceHolder holder;
	private boolean running;
	private boolean processing;

	public GameThread(Screen screen) {
		this.screen = screen;
		holder = null;
		running = true;
		processing = true;
		timer = new Timer();
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void setSurfaceHolder(SurfaceHolder holder) {
		this.holder = holder;
	}

	public void stopProcessing() {
		processing = false;
	}

	public void pause() {
		running = false;
		processing = false;
	}

	public void restart() {
		running = true;
		processing = true;
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void run() {
		timer.start();
		while (true) {
			timer.update();
			if (screen != null) {
				synchronized (screen) {
					// use another thread
					if (!screen.getActive()) {
						return;
					}
					if (processing) {
						screen.update(timer.getElapse());
					} else {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
					}
				}
				if (holder != null) {
					Canvas canvas = holder.lockCanvas();
					if (canvas != null) {
						synchronized (screen) {
							screen.draw(canvas);
						}
						holder.unlockCanvasAndPost(canvas);
					}
				}
			}
			if (running == false) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}