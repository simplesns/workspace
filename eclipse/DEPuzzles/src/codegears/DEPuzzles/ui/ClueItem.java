package codegears.DEPuzzles.ui;

import codegears.DEPuzzles.R;
import codegears.DEPuzzles.data.Clue;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClueItem extends LinearLayout {

	private TextView id;
	private TextView text;
	private TextView length;
	private Clue clue;
	
	public ClueItem(Context context) {
		super(context);
		View.inflate(context, R.layout.ui_clueitem, this);
		id = (TextView)findViewById(R.id.ClueId);
		text = (TextView)findViewById(R.id.ClueText);
		length = (TextView)findViewById(R.id.ClueLength);
	}
	
	public Clue getData(){
		return clue;
	}

	public void set(Clue c){
		clue = c;
		id.setText(c.getId());
		text.setText(c.getText());
		length.setText(c.getLength());
	}
}
