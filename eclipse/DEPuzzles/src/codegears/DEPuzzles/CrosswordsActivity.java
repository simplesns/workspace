package codegears.DEPuzzles;

import android.app.Activity;
import android.os.Bundle;
import freehand.neandroid.GameActivity;

public class CrosswordsActivity extends Activity {

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