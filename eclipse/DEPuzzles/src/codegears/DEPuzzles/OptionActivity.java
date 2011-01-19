package codegears.DEPuzzles;

import codegears.DEPuzzles.data.OptionData;
import freehand.neandroid.widget.ClickListener;
import freehand.neandroid.widget.CompoundButtonGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class OptionActivity extends Activity implements ClickListener {
	
	private ToggleButton timerbt;
	private ToggleButton bgmusicbt;
	private ToggleButton soundbt;
	private ToggleButton togglepop;
	private ToggleButton togglejazz;
	private ToggleButton togglespace;
	private ToggleButton toggleacoustic;
	private ToggleButton togglehappy;
	private OptionData data;
	private CompoundButtonGroup togglegroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		data = new OptionData();
		data.load(this);
		timerbt = (ToggleButton) findViewById(R.id.toggletime);
		bgmusicbt = (ToggleButton) findViewById(R.id.bgmusicbutton);
		soundbt = (ToggleButton) findViewById(R.id.soundfxbutton);
		togglepop = (ToggleButton) findViewById(R.id.Togglepop);
		togglejazz = (ToggleButton) findViewById(R.id.Togglejazz);
		togglespace = (ToggleButton) findViewById(R.id.Togglespace);
		toggleacoustic = (ToggleButton) findViewById(R.id.Toggleacoustic);
		togglehappy = (ToggleButton) findViewById(R.id.Togglehappy);
		
		togglegroup = new CompoundButtonGroup();
		togglegroup.add(togglepop);
		togglegroup.add(togglejazz);
		togglegroup.add(togglespace);
		togglegroup.add(toggleacoustic);
		togglegroup.add(togglehappy);

		togglegroup.setClickListener(this);
		timerbt.setChecked(data.isTimer());
		bgmusicbt.setChecked(data.isBgMusic());
		soundbt.setChecked(data.isSoundFX());

		timerbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (timerbt.isChecked()) {
					data.setTimer(true);
				} else {
					data.setTimer(false);
				}
			}
		});

		bgmusicbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (bgmusicbt.isChecked()) {
					data.setBgmusic(true);
				} else {
					data.setBgmusic(false);
				}
			}
		});

		soundbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (soundbt.isChecked()) {
					data.setSoundFX(true);
				} else {
					data.setSoundFX(false);
				}
			}
		});
	}

	public void onResume() {
		super.onResume();
		data = new OptionData();
		data.load(this);

		timerbt.setChecked(data.isTimer());
		bgmusicbt.setChecked(data.isBgMusic());
		soundbt.setChecked(data.isSoundFX());
	}

	public void onPause() {
		super.onPause();
		data.save(this);
	}

	public void onStop() {
		super.onStop();
		data.save(this);
	}
	
	public void onDestroy() {
		super.onDestroy();
		data.save(this);
	}

	@Override
	public void onClick(View view) {
		if(togglepop.equals(view)){
			// toogleop clicked
		}
	}
}
