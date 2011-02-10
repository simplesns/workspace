package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import codegears.DEPuzzles.R.id;
import codegears.DEPuzzles.R.layout;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberCrunchGame extends LinearLayout implements
		OnFocusChangeListener {

	private TextView num1View;
	private TextView num2View;
	private TextView numOpearationView;
	private EditText resultView;
	private LinearLayout bgView;

	private String num1; // chatchai
	private String num2;
	private String numopeartion;
	private int result;
	private int state; //
	private Context context;

	private static final int STATE_NORMAL = 0;
	private static final int STATE_CORRECT = 1;
	private static final int STATE_WRONG = 2;

	public NumberCrunchGame(Context context) {
		super(context);
		this.context = context;
		View.inflate(context, R.layout.ui_numbercrunchgame, this);
		num1View = (TextView) this.findViewById(R.id.Num1);
		num1View.setText("?");
		num2View = (TextView) this.findViewById(R.id.Num2);
		numOpearationView = (TextView) this.findViewById(R.id.NumOperation);
		resultView = (EditText) this.findViewById(R.id.Result);
		bgView = (LinearLayout) this.findViewById(R.id.BgNumberCrunchGame);
		resultView.setInputType(InputType.TYPE_NULL);
		resultView.setOnFocusChangeListener(this);
	}

	public boolean isComplete() {
		// compare result with resultView.getText()
		if (String.valueOf(result).equals(String.valueOf((resultView.getText())))) {
			return true;
		} else {
			return false;
		}
	}

	public void setStateToNormal() {
		state = STATE_NORMAL;
		bgView.setBackgroundResource(R.drawable.numbercrunch_bgquiz);
		resultView.setFocusable(true);
		resultView.setFocusableInTouchMode(true);
	}

	public void setStateToCorrect() {
		state = STATE_CORRECT;
		bgView.setBackgroundResource(R.drawable.numbercrunch_bgquiz_correct);
		resultView.setFocusable(false);
	}

	public void setStateToWrong() {
		state = STATE_WRONG;
		bgView.setBackgroundResource(R.drawable.numbercrunch_bgquiz_wrong);
		resultView.setFocusable(true);
		resultView.setFocusableInTouchMode(true);
	}

	public String getNum1() {
		return num1;
	}

	public String getNum2() {
		return num2;
	}

	public String getNumOpeartion() {
		return numopeartion;
	}

	public int getResult() {
		return result;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public void revealNum1() {
		num1View.setText(num1);
	}

	public void clear() {
		resultView.setText("");
	}

	public void hideNum1() {
		num1View.setText("?");
	}

	public void setNum2(String num2) {
		this.num2 = num2;
		num2View.setText(num2);
	}

	public void setNumOperation(String numopeartion) {
		this.numopeartion = numopeartion;
		numOpearationView.setText(numopeartion);
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void focus() {
		resultView.setFocusable(true);
		resultView.setFocusableInTouchMode(true);
		resultView.requestFocus();
	}

	public void showAnswer() {
		resultView.setText(String.valueOf(result));
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus) {
			if (!isComplete() && (resultView.length() != 0)) {
				setStateToWrong();
			}
		}
	}

}
