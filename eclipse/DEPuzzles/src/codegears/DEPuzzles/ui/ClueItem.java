package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class ClueItem extends LinearLayout {

	public ClueItem(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_clueitem, this);
	}

}
