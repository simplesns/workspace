package codegears.DEPuzzles;

import codegears.DEPuzzles.ui.CrosswordsBoard;
import codegears.DEPuzzles.util.DataBuilder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import freehand.neandroid.GameActivity;

public class CrosswordsActivity extends Activity {

	private CrosswordsBoard board;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.crosswords);
		board = (CrosswordsBoard)findViewById(R.id.CrosswordsBoard);
		setGameArea();
	}

	private void setGameArea(){
		Intent i = getIntent();
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createCrosswordsGridFromAsset(this, "CrosswordsPuzzle/" + file + ".grid.txt");
		board.setBoardData(grid);
//		String[][] clues = DataBuilder.createCrosswordsClueFromAsset(this, "CrosswordsPuzzle/" + file + ".clues.txt");
	}

}