package codegears.DEPuzzles;

import codegears.DEPuzzles.data.CrosswordsClue;
import codegears.DEPuzzles.data.CrosswordsWord;
import codegears.DEPuzzles.ui.CrosswordsBoard;
import codegears.DEPuzzles.ui.CrosswordsTile;
import codegears.DEPuzzles.ui.CrosswordsTileListener;
import codegears.DEPuzzles.ui.KeyPad;
import codegears.DEPuzzles.ui.KeyPadListener;
import codegears.DEPuzzles.util.DataBuilder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import freehand.neandroid.GameActivity;

public class CrosswordsActivity extends Activity implements OnClickListener,
		CrosswordsTileListener, KeyPadListener {

	private CrosswordsBoard board;
	private CrosswordsClue clue;
	private CrosswordsWord currentWord;
	private CrosswordsTile currentTile;
	private KeyPad keyPad;
	private Button left;
	private Button hintBar;
	private Button right;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.crosswords);
		board = (CrosswordsBoard) findViewById(R.id.CrosswordsBoard);
		keyPad = (KeyPad) findViewById(R.id.CrosswordsKeyPad);
		keyPad.setKeyPadListener(this);
		CrosswordsTile[] tile = board.getTile();
		for (CrosswordsTile t : tile) {
			t.setCrosswordsTileListener(this);
		}
		left = (Button) findViewById(R.id.CrosswordsLeft);
		left.setOnClickListener(this);
		hintBar = (Button) findViewById(R.id.CrosswordsHintBar);
		hintBar.setOnClickListener(this);
		right = (Button) findViewById(R.id.CrosswordsRight);
		right.setOnClickListener(this);
		setGameArea();
	}

	private void setGameArea() {
		Intent i = getIntent();
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createCrosswordsGridFromAsset(this,
				"CrosswordsPuzzle/" + file + ".grid.txt");
		board.setBoardData(grid);
		clue = DataBuilder.createCrosswordsClueFromAsset(this, "CrosswordsPuzzle/"
				+ file + ".clues.txt");
		clue.scanBoard(board);
		currentWord = clue.getFirstWord();
		currentWord.selected();
		currentTile = currentWord.getFirstTile();
		hintBar.setText(currentWord.getClue());
		currentTile.tileSelected();
	}

	@Override
	public void onClick(View view) {
		if (left.equals(view)) {
			currentWord.unselect();
			currentWord = clue.getPreviousWord(currentWord);
			currentWord.selected();
			currentTile = currentWord.getFirstTile();
			currentTile.tileSelected();
			hintBar.setText(currentWord.getClue());
		} else if (hintBar.equals(view)) {
			currentWord.unselect();
			currentTile = clue.getHintBarClickedTile(board, currentWord, currentTile);
			currentWord = clue.getHintBarClickedWord(board, currentWord, currentTile);
			currentWord.selected();
			currentTile.tileSelected();
			hintBar.setText(currentWord.getClue());
		} else if (right.equals(view)) {
			currentWord.unselect();
			currentWord = clue.getNextWord(currentWord);
			currentWord.selected();
			currentTile = currentWord.getFirstTile();
			currentTile.tileSelected();
			hintBar.setText(currentWord.getClue());
		}
	}

	@Override
	public void onTouch(CrosswordsTile view) {
		currentWord.unselect();
		currentTile = view;
		currentWord = clue.getWordFromTouch(currentWord, currentTile);
		currentWord.selected();
		currentTile.tileSelected();
		hintBar.setText(currentWord.getClue());
	}

	@Override
	public void onKeyPad(int mode, String key) {
		currentTile.setText(mode, key);
	}

}