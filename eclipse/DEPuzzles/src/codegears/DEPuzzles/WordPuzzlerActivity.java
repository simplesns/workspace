package codegears.DEPuzzles;

import codegears.DEPuzzles.ui.CompleteDialogListener;
import codegears.DEPuzzles.ui.TimerButton;
import codegears.DEPuzzles.ui.WordSearchBoard;
import codegears.DEPuzzles.ui.WordSearchBoardListener;
import codegears.DEPuzzles.util.DataBuilder;
import codegears.DEPuzzles.util.Util;
import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WordPuzzlerActivity extends Activity implements
		WordSearchBoardListener, OnClickListener {
	private TextView[] wordView;
	private WordSearchBoard board;
	private Button backButton;
	private Button hintButton;
	private Button clearButton;
	private TimerButton timerButton;

	private String puzzleText;
	private String file;
	private String[] wordList;
	private String[] clueList;
	private String message;
	private boolean[] wordFound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.wordsearch);
		board = (WordSearchBoard) findViewById(R.id.WordSearchBoard);
		board.setWordSearchBoardListener(this);
		wordFound = new boolean[10];
		for (int i = 0; i < wordFound.length; i++) {
			wordFound[i] = false;
		}
		wordView = new TextView[12];
		wordView[0] = (TextView) findViewById(R.id.WordSearchWord1);
		wordView[1] = (TextView) findViewById(R.id.WordSearchWord2);
		wordView[2] = (TextView) findViewById(R.id.WordSearchWord3);
		wordView[3] = (TextView) findViewById(R.id.WordSearchWord4);
		wordView[4] = (TextView) findViewById(R.id.WordSearchWord5);
		wordView[5] = (TextView) findViewById(R.id.WordSearchWord6);
		wordView[6] = (TextView) findViewById(R.id.WordSearchWord7);
		wordView[7] = (TextView) findViewById(R.id.WordSearchWord8);
		wordView[8] = (TextView) findViewById(R.id.WordSearchWord9);
		wordView[9] = (TextView) findViewById(R.id.WordSearchWord10);
		wordView[10] = (TextView) findViewById(R.id.WordSearchWord11);
		wordView[11] = (TextView) findViewById(R.id.WordSearchWord12);
		wordView[10].setText("");
		wordView[11].setText("");
		backButton = (Button) findViewById(R.id.WordSearchBack);
		backButton.setOnClickListener(this);
		hintButton = (Button) findViewById(R.id.WordSearchHint);
		hintButton.setOnClickListener(this);
		clearButton = (Button) findViewById(R.id.WordSearchClear);
		clearButton.setOnClickListener(this);
		timerButton = (TimerButton) findViewById(R.id.WordSearchTimer);
		timerButton.setOnClickListener(this);
		setGameArea();
		hintDialog();
	}

	@Override
	public void onResume() {
		super.onResume();
		timerButton.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		timerButton.stop();
	}

	private void setGameArea() {
		Intent i = getIntent();
		puzzleText = i.getStringExtra(PuzzleSelectActivity.EXTRA_TEXT);
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createGridFromAsset(this, "WordPuzzlerPuzzle/"
				+ file + "grid.txt", 10, 10);
		board.setBoardData(grid);
		wordList = DataBuilder.createWordSearchListFromAsset(this,
				"WordPuzzlerPuzzle/" + file + "sols.txt", 10);
		message = DataBuilder.createWordPuzzlerMessageFromAsset(this,
				"WordPuzzlerPuzzle/" + file + "blurb.txt");
		clueList = DataBuilder.createWordSearchListFromAsset(this,
				"WordPuzzlerPuzzle/" + file + "clues.txt", 10);
		for (int loop = 0; loop < 10; loop++) {
			wordList[loop] = wordList[loop].substring(3);
			wordList[loop] = wordList[loop].trim();
			clueList[loop] = clueList[loop].substring(3);
			clueList[loop] = clueList[loop].trim();
			wordView[loop].setText(clueList[loop]);
		}
	}

	public void hintDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder = builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public boolean onWord(String word) {
		for (int i = 0; i < wordList.length; i++) {
			System.out.println(wordList[i] + " : " + word);
			if (wordList[i].equals(word)) {
				if (!wordFound[i]) {
					wordFound[i] = true;
					wordView[i].setTextColor(0xFFFFFF00);
					if (isComplete()) {
						showComplete();
					}
					return true;
				}
			}
		}
		word = Util.stringReverse(word);
		for (int i = 0; i < wordList.length; i++) {
			if (wordList[i].equals(word)) {
				if (!wordFound[i]) {
					wordFound[i] = true;
					wordView[i].setTextColor(0xFFFFFF00);
					if (isComplete()) {
						showComplete();
					}
					return true;
				}
			}
		}
		return false;
	}

	private void showComplete() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder = builder.setMessage("Puzzle was successfully completed! Time: "
				+ timerButton.getText() + ".");
		builder = builder.setTitle("Puzzle Completed");
		builder.setPositiveButton("OK", new CompleteDialogListener(this));
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private boolean isComplete() {
		for (boolean b : wordFound) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		if (backButton.equals(view)) {
			// record
			finish();
		} else if (hintButton.equals(view)) {
			hintDialog();
		} else if (clearButton.equals(view)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder = builder.setMessage("Would you like to restart puzzle?");
			builder = builder.setTitle("Restart Puzzle");
			ClearDialogListener listener = new ClearDialogListener();
			builder.setPositiveButton("OK", listener);
			builder.setNegativeButton("Cancel", listener);
			AlertDialog dialog = builder.create();
			dialog.show();
		} else if (timerButton.equals(view)) {
			Intent i = new Intent(this, SummaryActivity.class);
			i.putExtra(SummaryActivity.EXTRA_GAME, "Word Search");
			i.putExtra(SummaryActivity.EXTRA_TITLE1, "Puzzle");
			i.putExtra(SummaryActivity.EXTRA_TEXT1, puzzleText);
			i.putExtra(SummaryActivity.EXTRA_TITLE2, "Currrent time");
			i.putExtra(SummaryActivity.EXTRA_TEXT2, timerButton.getText());
			i.putExtra(SummaryActivity.EXTRA_TITLE3, "Words found");
			i.putExtra(SummaryActivity.EXTRA_TEXT3, String.valueOf(countWordFound()));
			i.putExtra(SummaryActivity.EXTRA_TITLE4, "Words total");
			i.putExtra(SummaryActivity.EXTRA_TEXT4, "10");
			this.startActivity(i);
		}
	}

	public int countWordFound() {
		int count = 0;
		for (boolean b : wordFound) {
			if (b) {
				count++;
			}
		}
		return count;
	}

	private void clear() {
		for (int i = 0; i < wordFound.length; i++) {
			wordFound[i] = false;
			wordView[i].setTextColor(0xFFFFFFFF);
		}
		board.clear();
	}

	private class ClearDialogListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int button) {
			if (button == DialogInterface.BUTTON_POSITIVE) {
				clear();
				dialog.dismiss();
			}
			if (button == DialogInterface.BUTTON_NEGATIVE) {
				dialog.dismiss();
			}
		}
	}
}
