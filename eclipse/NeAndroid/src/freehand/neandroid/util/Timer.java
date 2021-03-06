package freehand.neandroid.util;

public class Timer {

	private int duration;
	private int elapse;
	private long currentTime;
	private long previousTime;
	private boolean active;

	public Timer() {
		active = false;
	}

	public int getDuration() {
		return duration;
	}
	
	public void increaseDuration(int amount){
		duration += amount;
	}

	public int getElapse() {
		return elapse;
	}
	
	public void start() {
		currentTime = System.currentTimeMillis();
		elapse = 0;
		active = true;
	}

	public void update() {
		if (active) {
			previousTime = currentTime;
			currentTime = System.currentTimeMillis();
			elapse = (int) (currentTime - previousTime);
			duration += elapse;
		}
	}
	

	public void stop() {
		duration = 0;
		elapse = 0;
		active = false;
	}

	public void pause() {
		active = false;
	}
}
