package codegears.DEPuzzles.ui.dialog;

import codegears.DEPuzzles.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CrosswordsClearDialog extends Dialog implements OnClickListener {

	private Button reset;
	private Button deleteCurrent;
	private Button clearErrors;
	private Button cancel;
	private CrosswordsClearDialogListener listener;

	public CrosswordsClearDialog(Context context) {
		super(context);
		setContentView(R.layout.dialog_crosswords_clear);
		reset = (Button) findViewById(R.id.DialogCrosswordsReset);
		reset.setOnClickListener(this);
		deleteCurrent = (Button) findViewById(R.id.DialogCrosswordsDeleteCurrent);
		deleteCurrent.setOnClickListener(this);
		clearErrors = (Button) findViewById(R.id.DialogCrosswordsClearErrors);
		clearErrors.setOnClickListener(this);
		cancel = (Button) findViewById(R.id.DialogCrosswordsCancel);
		cancel.setOnClickListener(this);
	}

	public void setFirstButtonText(String text) {
		reset.setText("text");
	}

	public void setSecondButtonText(String text) {
		deleteCurrent.setText(text);
	}

	public void setThirdButtonText(String text) {
		clearErrors.setText(text);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		listener.onCancel(this);
	}

	public void setDialogListener(CrosswordsClearDialogListener listener) {
		this.listener = listener;
	}

	@Override
	public void onClick(View view) {
		if (reset.equals(view)) {
			listener.onReset(this);
		} else if (deleteCurrent.equals(view)) {
			listener.onDeleteCurrent(this);
		} else if (clearErrors.equals(view)) {
			listener.onClearErrors(this);
		} else if (cancel.equals(view)) {
			listener.onCancel(this);
		}
	}

}
