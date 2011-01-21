package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.data.PuzzleSelectData;
import codegears.DEPuzzles.ui.NumPad;
import codegears.DEPuzzles.ui.NumPadListener;
import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class NumberCrunchActivity extends Activity implements NumPadListener {
	
	private NumPad numPad;
	private Button hint;
	private Button reset;
	private LinearLayout game;
	private String mission;
	public static final String EXTRA_FILE = "file";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.numbercrunch);
		//numPad = (NumPad)this.findViewById(R.id.TableNamPad);
		//numPad.setNumPadListener(this);
		setGameArea();
	}
	
	private void setGameArea(){
		// load text file
		// loop create ui_numbercronchgame
		Intent i = getIntent();
		Bundle b = i.getExtras();
		mission = b.getString(EXTRA_FILE);
		if(mission.charAt(3) == 'e'){
			
		}else if(mission.charAt(3) == 'm'){
			
		}else if(mission.charAt(3) == 'h'){
			
		}
	}

	@Override
	public void onNumPadClicked(int keyCode) {
		//find cursor 
		//add text on that EditText
		//calculate for correct answer
		//if correct
			//cursor move or finish game
			//change ui_numbercrunchgame state
		
	}
}