package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.data.PuzzleSelectData;
import codegears.DEPuzzles.ui.NumPad;
import codegears.DEPuzzles.ui.NumPadListener;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class NumberCrunchActivity extends Activity implements NumPadListener {
	
	private NumPad numPad;
	private Button hint;
	private Button reset;
	private LinearLayout game;
	private String mission;
	private String[] quiz;
	public static final String EXTRA_FILE = "file";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.numbercrunch);
		//numPad = (NumPad)this.findViewById(R.id.TableNamPad);
		//numPad.setNumPadListener(this);
		game = (LinearLayout) findViewById(R.id.LinearNumberCrunchArea);
	
		setGameArea();
	}
	
	private void setGameArea(){
		// load text file
		// loop create ui_numbercronchgame
		mission = this.getIntent().getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		
		quiz = DataBuilder.createNumberCrunchQuizFromAsset(this,"NumberCrunchPuzzle/quiz/"+mission+".txt");
		
		for(int i = 0; i < 9; i++){
			if(i == 0){
				
			}else {
				
			}
		}
		//get first 3 array 
		//loop set questionLayout until end of array
			//use first 3 array to find answer (forth)
			//create questionlayout
			//game.addView(questionLayout);
			//remember answer (forth);
			//get first 2 array
			//combine remember answer with first 2 array
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