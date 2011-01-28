package codegears.DEPuzzles;

import codegears.DEPuzzles.data.CrosswordsClue;
import codegears.DEPuzzles.data.CrosswordsWord;
import codegears.DEPuzzles.ui.CompleteDialogListener;
import codegears.DEPuzzles.ui.CrosswordsBoard;
import codegears.DEPuzzles.ui.CrosswordsTile;
import codegears.DEPuzzles.ui.CrosswordsTileListener;
import codegears.DEPuzzles.ui.KeyPad;
import codegears.DEPuzzles.ui.KeyPadListener;
import codegears.DEPuzzles.ui.dialog.CrosswordsClearDialog;
import codegears.DEPuzzles.ui.dialog.CrosswordsClearDialogListener;
import codegears.DEPuzzles.util.DataBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import freehand.neandroid.GameActivity;

public class CrosswordsActivity extends Activity implements OnClickListener,
		CrosswordsTileListener, KeyPadListener, CrosswordsClearDialogListener {

	private CrosswordsBoard board;
	private CrosswordsClue clue;
	private CrosswordsWord currentWord;
	private CrosswordsTile currentTile;
	private KeyPad keyPad;
	private Button left;
	private Button hintBar;
	private Button right;
	private Button clear;
	private Button clueButton;
	private boolean errorDialogShown;
	private String file;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.crosswords);
		errorDialogShown = false;
		board = (CrosswordsBoard) findViewById(R.id.CrosswordsBoard);
		keyPad = (KeyPad) findViewById(R.id.CrosswordsKeyPad);
		keyPad.setKeyPadListener(this);
		CrosswordsTile[] tile = board.getTile();
		for (CrosswordsTile t : tile) {
			t.setCrosswordsTileListener(this);
		}
		left = (Button) findViewById(R.id.CrosswordsLeft);
		left.setOnClickListener(this);
		clear = (Button) findViewById(R.id.CrosswordsClear);
		clear.setOnClickListener(this);
		clueButton = (Button) findViewById(R.id.CrosswordsClue);
		clueButton.setOnClickListener(this);
		hintBar = (Button) findViewById(R.id.CrosswordsHintBar);
		hintBar.setOnClickListener(this);
		right = (Button) findViewById(R.id.CrosswordsRight);
		right.setOnClickListener(this);
		setGameArea();
	}

	private void setGameArea() {
		Intent i = getIntent();
		file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
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
		} else if (clear.equals(view)) {
			CrosswordsClearDialog dialog = new CrosswordsClearDialog(this);
			dialog.setDialogListener(this);
			dialog.show();
		} else if (clueButton.equals(view)) {
			Intent i = new Intent(this, CrosswordsClueActivity.class);
			i.putExtra(PuzzleSelectActivity.EXTRA_FILE, file);
			this.startActivity(i);
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
		if (mode != CrosswordsTile.TEXT_MULTI) {
			currentWord.unselect();
			CrosswordsTile t = currentWord.getNextEmptyTile(currentTile);
			if (t.equals(currentTile)) {
				CrosswordsWord w = clue.getNextWord(currentWord);
				t = w.getFirstTile();
				if (t.isEmpty()) {
					currentTile = t;
					currentWord = w;
				} else {
					CrosswordsTile t2 = w.getNextEmptyTile(t);
					if (!t2.equals(t)) {
						currentTile = t2;
						currentWord = w;
					}
				}
			} else {
				currentTile = t;
			}
			currentWord.selected();
			currentTile.tileSelected();
		}
		if (clue.isComplete()) {
			// show complete
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder = builder
					.setMessage("Puzzle was successfully completed! Time: xx:xx:xx.");
			builder = builder.setTitle("Puzzle Completed");
			builder.setPositiveButton("OK", new CompleteDialogListener(this));
			AlertDialog dialog = builder.create();
			dialog.show();
		} else if ((clue.isFull()) && (!errorDialogShown)) {
			// show board full message
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder = builder.setMessage("Puzzle contains errors and it is not "
					+ "completed. Would you like to reveal them");
			builder = builder.setTitle("Completed Error");
			builder.setPositiveButton("Reveal", new ErrorDialogListener());
			builder.setNegativeButton("Cancel", new ErrorDialogListener());
			AlertDialog dialog = builder.create();
			dialog.show();
			errorDialogShown = true;
		}
	}

	private class ErrorDialogListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			if (which == DialogInterface.BUTTON_POSITIVE) {
				clue.showError();
				currentTile.tileSelected();
			}
		}
	}

	@Override
	public void onReset(Dialog dialog) {
		clue.emptyBoard();
		currentWord = clue.getFirstWord();
		currentWord.selected();
		currentTile = currentWord.getFirstTile();
		hintBar.setText(currentWord.getClue());
		currentTile.tileSelected();
		dialog.dismiss();
	}

	@Override
	public void onDeleteCurrent(Dialog dialog) {
		currentWord.emptyTile();
		currentWord.selected();
		currentTile.tileSelected();
		dialog.dismiss();
	}

	@Override
	public void onClearErrors(Dialog dialog) {
		clue.clearErrors();
		currentWord.selected();
		currentTile.tileSelected();
		dialog.dismiss();
	}
}