package codegears.DEPuzzles;

import codegears.DEPuzzles.data.OptionData;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class OptionActivity extends Activity {
	
	private static boolean timer, bgmusic, sound;
	private ToggleButton timerbt, bgmusicbt, soundbt;
	private OptionData data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		data = new OptionData();
		data.load();

		//----- get Data from SharedPreferences ----// 
//		SharedPreferences saveoptions = getSharedPreferences("Options", 0);
//		boolean svtimer = saveoptions.getBoolean("time", false);
//		boolean svbgmusic = saveoptions.getBoolean("bgmusic", false);
//		boolean svsound = saveoptions.getBoolean("sound", false);

		timerbt = (ToggleButton) findViewById(R.id.toggletime);
		bgmusicbt = (ToggleButton) findViewById(R.id.bgmusicbutton);
		soundbt = (ToggleButton) findViewById(R.id.soundfxbutton);

		timerbt.setChecked(/*data.isTimer()*/svtimer);
		bgmusicbt.setChecked(/*data.isBgMusic()*/svbgmusic);
		soundbt.setChecked(/*data.isSoundFX()*/svsound);

		timerbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (timerbt.get) {
					timer = true;
				} else {
					timer = false;
				}
			}
		});

		bgmusicbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (bgmusicbt.) {
					bgmusic = true;
				} else {
					bgmusic = false;
				}
			}
		});

		soundbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (soundbt.getTextOff() == null) {
					sound = true;
				} else {
					sound = false;
				}
			}
		});
	}

	public void onResume() {
		super.onResume();
		
		data.load();

//		SharedPreferences saveoptions = getSharedPreferences("Options", 0);
//		boolean svtimer = saveoptions.getBoolean("time", false);
//		boolean svbgmusic = saveoptions.getBoolean("bgmusic", false);
//		boolean svsound = saveoptions.getBoolean("sound", false);

		timerbt.setChecked(svtimer);
		bgmusicbt.setChecked(svbgmusic);
		soundbt.setChecked(svsound);
	}

	public void onPause() {
		
		super.onPause();
		
		data.save();

//		SharedPreferences saveoptions = getSharedPreferences("Options", 0);
//		SharedPreferences.Editor editoption = saveoptions.edit();
//		editoption.putBoolean("timer", timer);
//		editoption.putBoolean("bgmusic", bgmusic);
//		editoption.putBoolean("sound", sound);

//		editoption.commit();
	}

	public void onStop() {
		super.onStop();
		data.save();

//		SharedPreferences saveoptions = getSharedPreferences("Options", 0);
//		SharedPreferences.Editor editoption = saveoptions.edit();
//		editoption.putBoolean("timer", timer);
//		editoption.putBoolean("bgmusic", bgmusic);
//		editoption.putBoolean("sound", sound);
//
//		editoption.commit();
	}

}
