package codegears.DEPuzzles.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class CompleteDialogListener implements OnClickListener {

	private Activity activity;
	public CompleteDialogListener(Activity activity){
		this.activity = activity;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int button) {
		dialog.dismiss();
		activity.finish();
	}

}
