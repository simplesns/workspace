package codegears.DEPuzzles;

import java.util.ArrayList;

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
import android.app.Dialog;
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
	private char[] answer;

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
		for(CrosswordsTile t:allTile){
			t.setCrosswordsTileListener(this);
		}
		keyPad = (KeyPad) findViewById(R.id.CodeWordKeyPad);
		keyPad.setKeyPadListener(this);
		currentTile = null;
		sameWordTile = new ArrayList<CrosswordsTile>();
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
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createCrosswordsGridFromAsset(this,
				"CodeWordsPuzzle/" + file + ".txt");
		board.setBoardData(grid);
		answer = board.scanForCodeWordAnswer();
	}

	@Override
	public void onClick(View view) {
		if (view.equals(backButton)) {
			// record data
			finish();
		} else if (view.equals(hintButton)) {
		} else if (view.equals(clearButton)) {
			CrosswordsClearDialog dialog = new CrosswordsClearDialog(this);
			dialog.setDialogListener(this);
			dialog.show();
			timerButton.stop();
		} else if (view.equals(timerButton)) {
			Intent i = new Intent(this, SummaryActivity.class);
			i.putExtra(SummaryActivity.EXTRA_GAME, "Codeword");
			i.putExtra(SummaryActivity.EXTRA_TITLE1, "Puzzle");
			i.putExtra(SummaryActivity.EXTRA_TEXT1, "N/A");
			i.putExtra(SummaryActivity.EXTRA_TITLE2, "Current time");
			i.putExtra(SummaryActivity.EXTRA_TEXT2, "N/A");
			i.putExtra(SummaryActivity.EXTRA_TITLE3, "Entered fields");
			i.putExtra(SummaryActivity.EXTRA_TEXT3, "N/A");
			i.putExtra(SummaryActivity.EXTRA_TITLE4, "Remaining fields");
			i.putExtra(SummaryActivity.EXTRA_TEXT4, "N/A");
			this.startActivity(i);
		}
	}

	@Override
	public void onReset(Dialog dialog) {

	}

	@Override
	public void onDeleteCurrent(Dialog dialog) {
	}

	@Override
	public void onClearErrors(Dialog dialog) {
	}

	@Override
	public void onCancel(Dialog dialog) {
		dialog.dismiss();
		timerButton.start();
	}

	@Override
	public void onKeyPad(int mode, String key) {
		if(key.equals(KeyPad.KEY_BACK)){
			//remove text from tile
			//update keyPad
		}
		//if key is already pressed
			//select tile with that key
		//else
			//all tile with same number appear same effect
		for(CrosswordsTile t:sameWordTile){
			t.setText(mode, key);
			if(mode == CrosswordsTile.TEXT_PEN){
				//update keyPad
			}
		}
	}

	@Override
	public void onTouch(CrosswordsTile view) {
		currentTile = view;
		for(CrosswordsTile t:sameWordTile){
			t.unselect();
		}
		sameWordTile = board.getTile(view.getResult());
		for(CrosswordsTile t:sameWordTile){
			t.wordSelected();
		}
		currentTile.tileSelected();
	}
}