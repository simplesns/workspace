package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.os.Bundle;

public class WordSearchActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.wordsearch);
		setGameArea();
	}
	
	private void setGameArea(){
		//load grid
		//load list
	}
}
