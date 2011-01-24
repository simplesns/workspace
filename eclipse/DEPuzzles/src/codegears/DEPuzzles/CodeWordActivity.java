package codegears.DEPuzzles;

import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CodeWordActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.codeword);
		setGameArea();
	}

	private void setGameArea(){
	}
}
