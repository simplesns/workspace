package codegears.DEPuzzles;

import codegears.DEPuzzles.ui.TimerButton;
import codegears.DEPuzzles.ui.WordSearchBoard;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordSearchActivity extends Activity implements OnClickListener {

	private TextView[] wordView;
	private WordSearchBoard board;
	private Button backButton;
	private Button hintButton;
	private Button clearButton;
	private TimerButton timerButton;

	private String puzzleText;
	private String file;
	private String[] wordList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.wordsearch);
		board = (WordSearchBoard) findViewById(R.id.WordSearchBoard);
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
		backButton = (Button) findViewById(R.id.WordSearchBack);
		backButton.setOnClickListener(this);
		hintButton = (Button) findViewById(R.id.WordSearchHint);
		hintButton.setOnClickListener(this);
		clearButton = (Button) findViewById(R.id.WordSearchClear);
		clearButton.setOnClickListener(this);
		timerButton = (TimerButton) findViewById(R.id.WordSearchTimer);
		timerButton.setOnClickListener(this);
		setGameArea();
	}

	private void setGameArea() {
		Intent i = getIntent();
		puzzleText = i.getStringExtra(PuzzleSelectActivity.EXTRA_TEXT);
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		String[] grid = DataBuilder.createGridFromAsset(this, "WordSearchPuzzle/"
				+ file + "grid.txt", 10, 10);
		board.setBoardData(grid);
		wordList = DataBuilder.createWordSearchListFromAsset(this,
				"WordSearchPuzzle/" + file + "list.txt");
		for (int loop = 0; loop < 12; loop++) {
			wordView[loop].setText(wordList[loop]);
		}
	}
	
	@Override
	public void onClick(View view) {
		if(backButton.equals(view)){
			//record
			finish();
		} else if(hintButton.equals(view)){
		} else if(clearButton.equals(view)){
		} else if(timerButton.equals(view)){
		}
	}
}