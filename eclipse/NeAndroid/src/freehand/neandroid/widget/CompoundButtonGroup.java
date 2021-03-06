package freehand.neandroid.widget;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;

public class CompoundButtonGroup implements OnClickListener {

	private ArrayList<CompoundButton> buttonList;
	private ClickListener listener;

	public CompoundButtonGroup() {
		buttonList = new ArrayList<CompoundButton>();
	}

	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}

	public void add(CompoundButton button) {
		buttonList.add(button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		for (CompoundButton button : buttonList) {
			button.setChecked(false);
		}
		((CompoundButton) view).setChecked(true);
		if(listener != null){
			listener.onClick(view);
		}
	}

}
