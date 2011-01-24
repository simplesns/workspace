package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import codegears.DEPuzzles.R.id;
import codegears.DEPuzzles.R.layout;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberCrunchGame extends LinearLayout implements OnClickListener {
	
	private TextView num1;
	private TextView num2;
	private TextView numopearation;
	private EditText result;

	public NumberCrunchGame(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_numbercrunchgame, this);
		
		num1 = (TextView) this.findViewById(R.id.Num1);
		num2 = (TextView) this.findViewById(R.id.Num2);
		numopearation = (TextView) this.findViewById(R.id.NumOperation);
		result = (EditText) this.findViewById(R.id.Result);
	}
		
	@Override
	public void onClick(View arg0) {
		
	}		
	
}
