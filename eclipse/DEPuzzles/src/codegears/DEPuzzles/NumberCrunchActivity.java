package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CompleteDialogListener;
import codegears.DEPuzzles.ui.NumPad;
import codegears.DEPuzzles.ui.NumPadListener;
import codegears.DEPuzzles.ui.NumberCrunchGame;
import codegears.DEPuzzles.ui.TimerButton;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NumberCrunchActivity extends Activity implements NumPadListener,
		OnClickListener {

	private static final int PENALTY_HINT = 10000;
	private NumPad numPad;
	private Button back;
	private Button hint;
	private Button reset;
	private TimerButton timerButton;
	private LinearLayout game;
	private String mission;
	private String[] quiz;
	private int[] result;
	private String[] splitValue;
	private ArrayList<NumberCrunchGame> setValue;
	private String puzzleText;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.numbercrunch);
		numPad = (NumPad) this.findViewById(R.id.TableNumPad);
		numPad.setNumPadListener(this);
		game = (LinearLayout) findViewById(R.id.LinearNumberCrunchArea);
		back = (Button) findViewById(R.id.NumberCrunchBack);
		back.setOnClickListener(this);
		hint = (Button) findViewById(R.id.NumberCrunchHint);
		hint.setOnClickListener(this);
		reset = (Button) findViewById(R.id.NumberCrunchResetGame);
		reset.setOnClickListener(this);
		timerButton = (TimerButton) findViewById(R.id.NumberCrunchTimer);
		timerButton.setOnClickListener(this);
		setGameArea();
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
		Intent intent = this.getIntent();
		setValue = new ArrayList<NumberCrunchGame>();
		puzzleText = intent.getStringExtra(PuzzleSelectActivity.EXTRA_TEXT);
		mission = intent.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);

		quiz = DataBuilder.createNumberCrunchQuizFromAsset(this,
				"NumberCrunchPuzzle/quiz/" + mission + ".txt");
		int j = 0;
		for (int i = 0; i < quiz.length; i += 2) {
			NumberCrunchGame t = new NumberCrunchGame(this);
			if (i == 0) {
				t.setNum1(quiz[0]);
				t.revealNum1();
				t.setNumOperation(quiz[1]);
				t.setNum2(quiz[2]);

				if (quiz[0].indexOf("/") > 0) {
					splitValue = quiz[0].split("/");
					quiz[0] = String.valueOf(Float.parseFloat(splitValue[0])
							/ Integer.parseInt(splitValue[1]));
				}
				if (quiz[2].indexOf("/") > 0) {
					splitValue = quiz[2].split("/");
					quiz[2] = String.valueOf(Float.parseFloat(splitValue[0])
							/ Integer.parseInt(splitValue[1]));
				}
				result = new int[9];
				if (quiz[1].equals("x")) {
					result[j] = (int) (Double.valueOf(quiz[0]) * Double.valueOf(quiz[2]));
				} else if (quiz[1].equals("%")) {
					result[j] = (int) ((Double.valueOf(quiz[0]) * Double.valueOf(quiz[2])) / 100);
				} else if (quiz[1].equals("^")) {
					result[j] = (int) Math.pow(Double.valueOf(quiz[0]), 2);
					t.setNumOperation("squared");
					t.setNum2("");
				} else if (quiz[1].equals("rt")) {
					result[j] = (int) Math.sqrt(Double.valueOf(quiz[0]));
					t.setNumOperation("root");
					t.setNum2("");
				} else if (quiz[1].equals("+")) {
					result[j] = (int) (Double.valueOf(quiz[0]) + Double.valueOf(quiz[2]));
				} else if (quiz[1].equals("-")) {
					result[j] = (int) (Double.valueOf(quiz[0]) - Double.valueOf(quiz[2]));
				} else if (quiz[1].equals("/")) {
					result[j] = (int) (Double.valueOf(quiz[0]) / Double.valueOf(quiz[2]));
				}
				i++;
			} else {
				t.setNum1(String.valueOf(result[j - 1]));

				t.setStateToNormal();
				t.setNumOperation(quiz[i]);
				t.setNum2(quiz[i + 1]);

				if (quiz[i + 1].indexOf("/") > 0) {
					splitValue = quiz[i + 1].split("/");
					System.out.println(quiz[i + 1]);
					quiz[i + 1] = String.valueOf(Float.parseFloat(splitValue[0])
							/ Integer.parseInt(splitValue[1]));
				}

				if (quiz[i].equals("x")) {
					result[j] = (int) (result[j - 1] * Double.valueOf(quiz[i + 1]));
				} else if (quiz[i].equals("%")) {
					result[j] = (int) ((result[j - 1] * Double.valueOf(quiz[i + 1])) / 100);
				} else if (quiz[i].equals("^")) {
					result[j] = (int) Math.pow(result[j - 1], 2);
					t.setNumOperation("squared");
					t.setNum2("");
				} else if (quiz[i].equals("rt")) {
					result[j] = (int) Math.sqrt(result[j - 1]);
					t.setNumOperation("root");
					t.setNum2("");
				} else if (quiz[i].equals("+")) {
					result[j] = (int) (result[j - 1] + Double.valueOf(quiz[i + 1]));
				} else if (quiz[i].equals("-")) {
					result[j] = (int) (result[j - 1] - Double.valueOf(quiz[i + 1]));
				} else if (quiz[i].equals("/")) {
					result[j] = (int) (result[j - 1] / Double.valueOf(quiz[i + 1]));
				}
			}
			t.setResult(result[j]);
			game.addView(t);
			setValue.add(t);
			j++;
		}
	}

	@Override
	public void onNumPadClicked(int keyCode) {
		// find cursor
		View view = getCurrentFocus();
		if (!(view instanceof EditText)) {
			return;
		}
		// add text on that EditText
		EditText editText = (EditText) view;
		View v = (View) editText.getParent().getParent().getParent();

		NumberCrunchGame game = null;
		if (v instanceof NumberCrunchGame) {
			game = (NumberCrunchGame) v;
		}

		// check edit num answer
		char c = NumPad.getChar(keyCode); // Chatchai
		if (keyCode == NumPad.NUMPAD_x) {
			// delete the last one
			String editNum = editText.getText().toString();
			if (editNum.length() == 0) {
				editText.setText("");
			} else {
				editNum = editNum.substring(0, editNum.length() - 1);
				editText.setText(editNum);
			}
		} else {
			editText.append(String.valueOf(c));
		}

		if (game.isComplete()) {
			game.setStateToCorrect();
			int index = setValue.indexOf(game);
			if (index != setValue.size() - 1) {
				NumberCrunchGame nextFocus = setValue.get(index + 1);
				nextFocus.revealNum1();
				nextFocus.focus();
			}
			if (isGameComplete()) {
				showCompleteDialog();
			}
		}
	}

	private void showCompleteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder = builder.setMessage("Puzzle was successfully completed! Time: "
				+ timerButton.getText() + ".");
		builder = builder.setTitle("Puzzle Completed");
		builder.setPositiveButton("OK", new CompleteDialogListener(this));
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private boolean isGameComplete() {
		for (NumberCrunchGame n : setValue) {
			if (!n.isComplete()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if (back.equals(v)) {
			// record
			finish();
		} else if (hint.equals(v)) {
			int i = 0;
			NumberCrunchGame game = setValue.get(i);
			while(game.isComplete()){
				i++;
				game = setValue.get(i);
			}
			game.showAnswer();
			timerButton.addTime(PENALTY_HINT);
			game.setStateToCorrect();
			int index = setValue.indexOf(game);
			if (index != setValue.size() - 1) {
				NumberCrunchGame nextFocus = setValue.get(index + 1);
				nextFocus.revealNum1();
				nextFocus.focus();
			}
			if (isGameComplete()) {
				showCompleteDialog();
			}
		} else if (reset.equals(v)) {
			reset();
		} else if (timerButton.equals(v)) {
			Intent i = new Intent(this, SummaryActivity.class);
			i.putExtra(SummaryActivity.EXTRA_GAME, "Number Crunch");
			i.putExtra(SummaryActivity.EXTRA_TITLE1, "Puzzle");
			i.putExtra(SummaryActivity.EXTRA_TEXT1, puzzleText);
			i.putExtra(SummaryActivity.EXTRA_TITLE2, "Current time");
			i.putExtra(SummaryActivity.EXTRA_TEXT2, timerButton.getText());
			i.putExtra(SummaryActivity.EXTRA_TITLE3, "Entered fields");
			int complete = completeCount();
			i.putExtra(SummaryActivity.EXTRA_TEXT3, String.valueOf(complete));
			i.putExtra(SummaryActivity.EXTRA_TITLE4, "Remaining fields");
			i.putExtra(SummaryActivity.EXTRA_TEXT4,
					String.valueOf(setValue.size() - complete));
			this.startActivity(i);
		}
	}

	private int completeCount() {
		int i = 0;
		for (NumberCrunchGame n : setValue) {
			if (n.isComplete()) {
				i++;
			}
		}
		return i;
	}

	private void reset() {
		for (NumberCrunchGame n : setValue) {
			n.setStateToNormal();
			n.clear();
			n.hideNum1();
		}
		setValue.get(0).focus();
		setValue.get(0).revealNum1();
		timerButton.stop();
		timerButton.reset();
		timerButton.start();
	}

}