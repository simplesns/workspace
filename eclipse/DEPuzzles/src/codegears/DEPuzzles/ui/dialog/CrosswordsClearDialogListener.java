package codegears.DEPuzzles.ui.dialog;

import android.app.Dialog;

public interface CrosswordsClearDialogListener {

	public void onReset(Dialog dialog);
	public void onDeleteCurrent(Dialog dialog);
	public void onClearErrors(Dialog dialog);
	public void onCancel(Dialog dialog);
}
