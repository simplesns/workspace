package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CrosswordsHintActivity extends Activity implements OnClickListener {

	public static final int RESULT_NOTHING = 1;
	public static final int RESULT_ALL = 2;
	public static final int RESULT_REVEALSINGLE = 3;
	public static final int RESULT_SHOWERROR = 4;
	public static final int RESULT_REVEALENTIRE = 5;

	private Button done;
	private Button all;
	private Button revealSingle;
	private Button showError;
	private Button revealEntire;
	private TextView clue1;
	private TextView clue2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.crosswordshint);
		done = (Button) findViewById(R.id.CrosswordsHintDone);
		done.setOnClickListener(this);
		all = (Button) findViewById(R.id.CrosswordsHintAll);
		all.setOnClickListener(this);
		revealSingle = (Button) findViewById(R.id.CrosswordsHintRevealSingle);
		revealSingle.setOnClickListener(this);
		showError = (Button) findViewById(R.id.CrosswordsHintShowError);
		showError.setOnClickListener(this);
		revealEntire = (Button) findViewById(R.id.CrosswordsHintRevealEntire);
		revealEntire.setOnClickListener(this);
		Intent i = getIntent();
		String clue = i.getStringExtra(CrosswordsActivity.EXTRA_CLUE);
		String[] temp = clue.split(":");
		String id = temp[0];
		String direction;
		String[] temp2 = clue.split("[(]");
		String text = temp2[0];
		String length = "[" + temp2[1].substring(0, temp2[1].length() - 1) + "]";
		if(id.startsWith("A")){
			direction = "Across";
		} else {
			direction = "Down";
		}
		String number = id.substring(1, id.length());
		clue1 = (TextView) findViewById(R.id.CrosswordsHintClue1);
		clue1.setText(text);
		clue2 = (TextView) findViewById(R.id.CrosswordsHintClue2);
		clue2.setText(number + " " + direction + " " + length);
	}

	@Override
	public void onClick(View view) {
		if (done.equals(view)) {
			setResult(RESULT_NOTHING);
			finish();
		} else if (all.equals(view)) {
			setResult(RESULT_ALL);
			finish();
		} else if (revealSingle.equals(view)) {
			setResult(RESULT_REVEALSINGLE);
			finish();
		} else if (showError.equals(view)) {
			setResult(RESULT_SHOWERROR);
			finish();
		} else if (revealEntire.equals(view)) {
			setResult(RESULT_REVEALENTIRE);
			finish();
		}
	}

}
