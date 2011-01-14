package freehand.neandroid.ui;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;

public class AnimationUI implements Drawable {

	private static final int NORMAL = 0;
	private static final int PLAYING = 1;
	private Bitmap bitmap;
	private int frameCount;
	private int state;
	private boolean animationLoop;
	private FrameOrder[] frameOrder;
	private int time;
	private AnimationListener listener;
	private Vector<Event> eventList;
	private DrawPosition drawPosition;

	private int animationDuration;

	public AnimationUI() {
		animationLoop = false;
		drawPosition = new DrawPosition();
		eventList = new Vector<Event>();
	}

	public AnimationUI(Bitmap bitmap, int frameCount) {
		this();
		set(bitmap, frameCount);
	}
	
	public void recycleImage(){
		if(bitmap != null){
			bitmap.recycle();
			bitmap = null;
		}
	}

	public void set(Bitmap bitmap, int frameCount) {
		this.bitmap = bitmap;
		this.frameCount = frameCount;
		drawPosition.setDimension(bitmap.getWidth() / frameCount, bitmap.getHeight());
		state = NORMAL;
		frameOrder = new FrameOrder[frameCount];
		for (int i = 0; i < frameOrder.length; i++) {
			frameOrder[i] = new FrameOrder();
			frameOrder[i].frameNumber = i;
			frameOrder[i].duration = 100;
		}
		calculateAnimationDuration();
	}

	public void setFrameOrder(FrameOrder[] frameOrder) {
		this.frameOrder = frameOrder;
		calculateAnimationDuration();
	}

	public void addEvent(String name, int time) {
		Event e = new Event(name, time);
		eventList.add(e);
	}

	public void setAnimationLoop(boolean loop) {
		animationLoop = loop;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setPosition(float x, float y) {
		drawPosition.setPosition(x, y);
	}

	public void setAlign(int align) {
		drawPosition.setAlign(align);
	}

	public void setFrameDuration(int duration) {
		for (int i = 0; i < frameOrder.length; i++) {
			frameOrder[i].duration = duration;
		}
		calculateAnimationDuration();
	}

	public void setFrameDuration(int[] duration) {
		for (int i = 0; i < frameOrder.length; i++) {
			if (i < duration.length) {
				frameOrder[i].duration = duration[i];
			}
		}
		calculateAnimationDuration();
	}

	private void calculateAnimationDuration() {
		animationDuration = 0;
		for (int i = 0; i < frameOrder.length; i++) {
			animationDuration += frameOrder[i].duration;
		}
	}
	
	public int getDuration(){
		return animationDuration;
	}

	public void setAnimationListener(AnimationListener listener) {
		this.listener = listener;
	}

	public void start() {
		state = PLAYING;
	}

	public void stop() {
		state = NORMAL;
	}

	public void reset() {
		time = 0;
	}

	public int getCurrentFrame() {
		if ((time > animationDuration) && (!animationLoop)) {
			return frameOrder[frameOrder.length - 1].frameNumber;
		}
		int loopTime = time % animationDuration;
		int i = 0;
		loopTime -= frameOrder[i].duration;
		while (loopTime > 0) {
			i++;
			loopTime -= frameOrder[i].duration;
		}
		return frameOrder[i].frameNumber;
	}

	@Override
	public void draw(Canvas canvas) {
		if (bitmap != null) {
			if (!bitmap.isRecycled()) {
				Rect r = canvas.getClipBounds();
				PointF point = drawPosition.getDrawPosition();
				PointF dimension = drawPosition.getDimension();
				int frame = getCurrentFrame();
				canvas.clipRect(point.x, point.y, point.x + dimension.x, point.y + dimension.y,
								Region.Op.REPLACE);
				canvas.drawBitmap(bitmap, point.x - (frame * bitmap.getWidth() / frameCount), point.y,
												null);
				canvas.clipRect(r, Region.Op.REPLACE);
			}
		}
	}

	@Override
	public void update(int elapse) {
		if (state == PLAYING) {
			if (!animationLoop) {
				if ((time < animationDuration) && (animationDuration < time + elapse)) {
					if (listener != null) {
						listener.animationEnd(this);
					}
				}
			}
			int loopTime = time % animationDuration;
			for (Event e : eventList) {
				if ((loopTime < e.time) && (e.time < loopTime + elapse)) {
					listener.trickerEvent(this, e.name);
				}
			}
			time += elapse;
		}
	}

	private class Event {
		public String name;
		public int time;

		public Event(String name, int time) {
			this.name = name;
			this.time = time;
		}
	}

	public static class FrameOrder {
		public FrameOrder() {
		}

		public FrameOrder(int number, int d) {
			frameNumber = number;
			duration = d;
		}

		public int frameNumber;
		public int duration;
	}
}