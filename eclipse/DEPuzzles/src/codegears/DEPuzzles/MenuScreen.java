package codegears.DEPuzzles;

import android.graphics.Paint;
import android.view.KeyEvent;
import freehand.neandroid.GameActivity;
import freehand.neandroid.Screen;
import freehand.neandroid.Input.InputKey;
import freehand.neandroid.Input.InputSystem;
import freehand.neandroid.Input.InputTouch;
import freehand.neandroid.ui.BorderTextUI;
import freehand.neandroid.ui.ButtonUI;
import freehand.neandroid.ui.TextUI;

public class MenuScreen extends Screen {
	
	public MenuScreen(GameActivity activity) {
		super(activity);
		InputSystem iSystem = InputSystem.getInstance();
		InputKey iKey = iSystem.getKey();
		int i = iKey.getKeyState(KeyEvent.KEYCODE_HOME);
		InputTouch iTouch = iSystem.getTouch();
	}
}