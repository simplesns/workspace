package codegears.DEPuzzles.ui;

import freehand.neandroid.util.Timer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.Button;

public class TimerButton extends Button {

	private Timer timer;
	private TimerThread thread;

	public TimerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer = new Timer();
	}
	
	public void addTime(int time){
		timer.increaseDuration(time);
	}
	
	public void save(SharedPreferences.Editor editor){
		editor.putInt("timer", timer.getDuration());
	}
	
	public void load(SharedPreferences sPreferences){
		stop();
		start();
		int loadData = sPreferences.getInt("timer", 0);
		timer.increaseDuration(loadData);
	}
	
	public void reset(){
		timer.stop();
	}

	public void start() {
		if (thread != null) {
			thread.setStop();
		}
		thread = new TimerThread(timer);
		timer.start();
		thread.start();
	}

	public void stop() {
		if (thread != null) {
			thread.setStop();
			thread = null;
		}
	}

	public int getTime() {
		return timer.getDuration();
	}

	public void setTime(final int time) {
		TimerButton.this.post(new Runnable() {
			@Override
			public void run() {
				String minute = String.valueOf(time / 60000);
				int temp = time % 60000;
				String second = String.valueOf(temp / 1000);
				if (second.length() == 1) {
					second = "0" + second;
				}
				temp = time % 1000;
				String mili = String.valueOf(temp / 10);
				if (mili.length() == 1) {
					mili = "0" + mili;
				}
				TimerButton.this.setText(minute + ":" + second + ":" + mili);
				TimerButton.this.invalidate();
			}

		});
	}

	private class TimerThread extends Thread {

		private Timer timer;
		private boolean stop;

		public TimerThread(Timer timer) {
			stop = false;
			this.timer = timer;
		}

		public void setStop() {
			stop = true;
		}

		@Override
		public void run() {
			while (!stop) {
				timer.update();
				TimerButton.this.setTime(timer.getDuration());
				try {
					sleep(99);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}