package codegears.DEPuzzles;

import codegears.DEPuzzles.data.OptionData;
import freehand.neandroid.GameActivity;
import freehand.neandroid.widget.ClickListener;
import freehand.neandroid.widget.CompoundButtonGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class OptionActivity extends Activity implements ClickListener {
	
	private ToggleButton timerbt;
	private ToggleButton bgmusicbt;
	private ToggleButton soundbt;
	private ToggleButton togglePop;
	private ToggleButton toggleJazz;
	private ToggleButton toggleSpace;
	private ToggleButton toggleAcoustic;
	private ToggleButton toggleHappy;
	private OptionData data;
	private CompoundButtonGroup toggleGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		data = new OptionData();
		data.load(this);
		timerbt = (ToggleButton) findViewById(R.id.toggletime);
		bgmusicbt = (ToggleButton) findViewById(R.id.bgmusicbutton);
		soundbt = (ToggleButton) findViewById(R.id.soundfxbutton);
		togglePop = (ToggleButton) findViewById(R.id.Togglepop);
		toggleJazz = (ToggleButton) findViewById(R.id.Togglejazz);
		toggleSpace = (ToggleButton) findViewById(R.id.Togglespace);
		toggleAcoustic = (ToggleButton) findViewById(R.id.Toggleacoustic);
		toggleHappy = (ToggleButton) findViewById(R.id.Togglehappy);
		
		toggleGroup = new CompoundButtonGroup();
		toggleGroup.add(togglePop);
		toggleGroup.add(toggleJazz);
		toggleGroup.add(toggleSpace);
		toggleGroup.add(toggleAcoustic);
		toggleGroup.add(toggleHappy);
		toggleGroup.setClickListener(this);
		
		timerbt.setChecked(data.isTimer());
		bgmusicbt.setChecked(data.isBgMusic());
		soundbt.setChecked(data.isSoundFX());
		togglePop.setChecked(data.isTogglePop());
		toggleJazz.setChecked(data.isToggleJazz());
		toggleSpace.setChecked(data.isToggleSpace());
		toggleAcoustic.setChecked(data.isToggleAcoustic());
		toggleHappy.setChecked(data.isToggleHappy());

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

	@Override
	public void onClick(View view) {
		if(togglePop.equals(view)){
			// toogleop clicked
			data.setTogglePop(true);
		} else {
			data.setTogglePop(false);
		}
		
		if(toggleJazz.equals(view)){
			data.setToggleJazz(true);
		} else {
			data.setToggleJazz(false);
		}
			
		if(toggleSpace.equals(view)){
			data.setToggleSpace(true);
		} else {
			data.setToggleSpace(false);
		}
		
		if(toggleAcoustic.equals(view)){
			data.setToggleAcoustic(true);
		} else {
			data.setToggleAcoustic(false);
		}
		
		if(toggleHappy.equals(view)){
			data.setToggleHappy(true);
		} else {
			data.setToggleHappy(false);
		}
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
}
