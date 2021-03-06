package codegears.DEPuzzles;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class SharedResource {

	public static int screenW;
	public static int screenH;
	public static float scale;

	public SharedResource(Context context) {
		init(context);
	}

	public static void init(Context context) {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
						.getDefaultDisplay();
		screenW = display.getWidth();
		screenH = display.getHeight();
		scale = context.getResources().getDisplayMetrics().density;
	}
}

