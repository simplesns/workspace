package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CompleteDialogListener;
import codegears.DEPuzzles.ui.CrosswordsBoard;
import codegears.DEPuzzles.ui.CrosswordsTile;
import codegears.DEPuzzles.ui.CrosswordsTileListener;
import codegears.DEPuzzles.ui.KeyPad;
import codegears.DEPuzzles.ui.KeyPadListener;
import codegears.DEPuzzles.ui.TimerButton;
import codegears.DEPuzzles.ui.dialog.CrosswordsClearDialog;
import codegears.DEPuzzles.ui.dialog.CrosswordsClearDialogListener;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CodeWordActivity extends Activity implements OnClickListener,
		CrosswordsClearDialogListener, KeyPadListener, CrosswordsTileListener {

	private Button backButton;
	private Button hintButton;
	private Button clearButton;
	private TimerButton timerButton;
	private CrosswordsBoard board;
	private CrosswordsTile currentTile;
	private ArrayList<CrosswordsTile> sameWordTile;
	private KeyPad keyPad;
	private String puzzleText;
	private char[] answer;
	private boolean errorDialogShown;
	private int penalty;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.codeword);
		backButton = (Button) findViewById(R.id.CodeWordBack);
		backButton.setOnClickListener(this);
		hintButton = (Button) findViewById(R.id.CodeWordHint);
		hintButton.setOnClickListener(this);
		clearButton = (Button) findViewById(R.id.CodeWordClear);
		clearButton.setOnClickListener(this);
		timerButton = (TimerButton) findViewById(R.id.CodeWordTimer);
		timerButton.setOnClickListener(this);
		board = (CrosswordsBoard) findViewById(R.id.CodeWordBoard);
		CrosswordsTile[] allTile = board.getTile();
		errorDialogShown = false;
		penalty = 0;
		for (CrosswordsTile t : allTile) {
			t.setCrosswordsTileListener(this);
		}
		keyPad = (KeyPad) findViewById(R.id.CodeWordKeyPad);
		keyPad.setKeyPadListener(this);
		setGameArea();
	}

	@Override
	public void onPause() {
		super.onPause();
		timerButton.stop();
	}

	@Override
	public void onResume() {
		super.onResume();
		timerButton.start();
	}

	private void setGameArea() {
		Intent i = getIntent();
		puzzleText = i.getStringExtra(PuzzleSelectActivity.EXTRA_TEXT);
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createGridFromAsset(this,
				"CodeWordsPuzzle/" + file + ".txt", 13, 13);
		board.setBoardData(grid);
		answer = board.scanForCodeWordAnswer();
		sameWordTile = board.getTileFromNumber(1);
		currentTile = sameWordTile.get(0);
		for (CrosswordsTile t : sameWordTile) {
			t.wordSelected();
		}
		currentTile.tileSelected();
	}

	@Override
	public void onClick(View view) {
		if (view.equals(backButton)) {
			// record data
			finish();
		} else if (view.equals(hintButton)) {
			String key = keyPad.getRandomEmptyKey();
			if (key != null) {
				ArrayList<CrosswordsTile> tList = board.getTile(key.charAt(0));
				if (tList.get(0).isFilled()) {
					keyPad.setNumber(tList.get(0).getText(), 0);
				}
				for (CrosswordsTile t : tList) {
					t.reveal();
				}
				keyPad.setNumber(key, tList.get(0).getNumber());
				delaySetColor();
				completeCheck();
			}
		} else if (view.equals(clearButton)) {
			CrosswordsClearDialog dialog = new CrosswordsClearDialog(this);
			dialog.setSecondButtonText("Delete Current Selection");
			dialog.setDialogListener(this);
			dialog.show();
			timerButton.stop();
		} else if (view.equals(timerButton)) {
			Intent i = new Intent(this, SummaryActivity.class);
			i.putExtra(SummaryActivity.EXTRA_GAME, "Codeword");
			i.putExtra(SummaryActivity.EXTRA_TITLE1, "Puzzle");
			i.putExtra(SummaryActivity.EXTRA_TEXT1, puzzleText);
			i.putExtra(SummaryActivity.EXTRA_TITLE2, "Current time");
			i.putExtra(SummaryActivity.EXTRA_TEXT2, timerButton.getText());
			i.putExtra(SummaryActivity.EXTRA_TITLE3, "Entered fields");
			i.putExtra(SummaryActivity.EXTRA_TEXT3,
					String.valueOf(board.getFilledCount()));
			i.putExtra(SummaryActivity.EXTRA_TITLE4, "Remaining fields");
			i.putExtra(SummaryActivity.EXTRA_TEXT4,
					String.valueOf(board.getTileCount() - board.getFilledCount()));
			this.startActivity(i);
		}
	}

	private void delaySetColor() {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(CrosswordsActivity.DELAY_SETCOLOR);
					CodeWordActivity.this.runOnUiThread(new Runnable() {
						public void run() {
							board.setAllTileToNormalState();
							if (sameWordTile != null) {
								for (CrosswordsTile t : sameWordTile) {
									t.wordSelected();
								}
							}
							if (currentTile != null) {
								currentTile.tileSelected();
							}
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void clearError(){
		ArrayList<CrosswordsTile> tList = board.getErrorTile();
		for(CrosswordsTile t:tList){
			keyPad.setNumber(t.getText(), 0);
			t.empty();
		}
	}

	@Override
	public void onReset(Dialog dialog) {
		board.emptyBoard();
		keyPad.clear();
		sameWordTile = board.getTileFromNumber(1);
		currentTile = sameWordTile.get(0);
		currentTile.tileSelected();
		dialog.dismiss();
		penalty = 0;
		timerButton.reset();
		timerButton.start();
	}

	@Override
	public void onDeleteCurrent(Dialog dialog) {
		keyPad.press("back");
		dialog.dismiss();
	}

	@Override
	public void onClearErrors(Dialog dialog) {
		clearError();
		dialog.dismiss();
	}

	@Override
	public void onCancel(Dialog dialog) {
		dialog.dismiss();
		timerButton.start();
	}

	@Override
	public void onKeyPad(int mode, String key) {
		if (key.equals(KeyPad.KEY_BACK)) {
			for (CrosswordsTile t : sameWordTile) {
				keyPad.setNumber(t.getText(), 0);
				t.empty();
				t.wordSelected();
			}
			currentTile.tileSelected();
			return;
		}
		if (keyPad.findButtonByKey(key).getNumber() != 0) {
			for (CrosswordsTile t : sameWordTile) {
				t.unselect();
			}
			sameWordTile = board.getTileFromNumber(keyPad.findButtonByKey(key)
					.getNumber());
			for (CrosswordsTile t : sameWordTile) {
				t.wordSelected();
			}
			currentTile = sameWordTile.get(0);
			currentTile.tileSelected();
		} else {
			if (sameWordTile.get(0).isFilled()) {
				keyPad.setNumber(sameWordTile.get(0).getText(), 0);
			}
			for (CrosswordsTile t : sameWordTile) {
				t.setText(mode, key);
			}
			if (mode == CrosswordsTile.TEXT_PEN) {
				keyPad.setNumber(key, sameWordTile.get(0).getNumber());
			}
		}
		completeCheck();
	}

	public void completeCheck() {
		if (board.isComplete()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder = builder.setMessage("Puzzle was successfully completed! Time: "
					+ timerButton.getText() + ".");
			builder = builder.setTitle("Puzzle Completed");
			builder.setPositiveButton("OK", new CompleteDialogListener(this));
			AlertDialog dialog = builder.create();
			dialog.show();
		} else if ((board.isFull()) && (!errorDialogShown)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder = builder
					.setMessage("the puzzle is complete but there are errors"
							+ "in the solution. Would you like to clear all errors? (Every cleared"
							+ "letter adds 20 seconds to your score)");
			builder = builder.setTitle("Puzzle Errors");
			builder.setPositiveButton("Clear", new ErrorDialogListener());
			builder.setNegativeButton("Cancel", new ErrorDialogListener());
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}

	@Override
	public void onTouch(CrosswordsTile view) {
		currentTile = view;
		for (CrosswordsTile t : sameWordTile) {
			t.unselect();
		}
		sameWordTile = board.getTileFromNumber(view.getNumber());
		for (CrosswordsTile t : sameWordTile) {
			t.wordSelected();
		}
		currentTile.tileSelected();
	}

	private class ErrorDialogListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			if (which == DialogInterface.BUTTON_POSITIVE) {
				clearError();
			}
		}
	}

}