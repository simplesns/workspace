package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import codegears.DEPuzzles.R.id;
import codegears.DEPuzzles.R.layout;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberCrunchGame extends LinearLayout implements OnClickListener {
	
	private TextView num1View;
	private TextView num2View;
	private TextView numOpearationView;
	private EditText resultView;
	
	private String num1;					//chatchai
	private String num2;
	private String numopeartion;
	private int result;
	private int state;						//
	
	private static final int STATE_NORMAL = 0;
	private static final int STATE_CORRET = 1;
	private static final int STATE_WRONG = 2;
	
	
	public NumberCrunchGame(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_numbercrunchgame, this);

		num1View = (TextView) this.findViewById(R.id.Num1);
		num2View = (TextView) this.findViewById(R.id.Num2);
		numOpearationView = (TextView) this.findViewById(R.id.NumOperation);
		resultView = (EditText) this.findViewById(R.id.Result);

	}
	
	public void setStateToNormal(){
		state = STATE_NORMAL;
		//....
	}
	
	public void setStateToCorrect(){
	}
	
	public void setStateToWrong(){
	}

	public String getNum1(){
		return num1;
	}
	
	public String getNum2(){
		return num2;
	}
	
	public String getNumOpeartion(){
		return numopeartion;
	}
	
	public int getResult(){
		return result;
	}
	
	public void setNum1(String num1){
		this.num1 = num1;
		num1View.setText(num1);
	}
	
	public void setNum2(String num2){
		this.num2 = num2;
		num2View.setText(num2);
	}
	
	public void setNumOperation(String numopeartion){
		this.numopeartion = numopeartion;
		numOpearationView.setText(numopeartion);
	}
	
	public void setResult(int result2){
		this.result = result;
	}
	
	@Override
	public void onClick(View arg0) {
		
	}		
	
}
