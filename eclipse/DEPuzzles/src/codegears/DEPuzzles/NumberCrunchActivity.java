package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.os.Bundle;

public class NumberCrunchActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.numbercrunch);
	}
}