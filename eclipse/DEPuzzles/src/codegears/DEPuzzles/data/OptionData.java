package codegears.DEPuzzles.data;

import android.content.Context;
import android.content.SharedPreferences;

public class OptionData {
	private boolean timer,bgmusic,soundFX;
	
	public boolean getTimer(){
		return timer;
	}
	
	public boolean getBgmusic(){
		return bgmusic;
	}
	
	public boolean getSoundFX(){
		return soundFX;
	}
	
	public void setTimer(boolean timer){
		this.timer = timer;
	}
	
	public void setBgmusic(boolean bgmusic){
		this.bgmusic = bgmusic;
	}
	
	public void setSoundFX(boolean soundFX){
		this.soundFX = soundFX;
	}
	
	public void save(Context context){
		SharedPreferences saveoptions = context.getSharedPreferences("Options", 0);
		SharedPreferences.Editor editoption = saveoptions.edit();
		
		editoption.putBoolean("timer", timer);
		editoption.putBoolean("bgmusic", bgmusic);
		editoption.putBoolean("sound", soundFX);

		editoption.commit();
	}
	
	public void load(Context context){
		SharedPreferences saveoptions = context.getSharedPreferences("Options", 0);
		
		timer = saveoptions.getBoolean("timer", false);
		bgmusic = saveoptions.getBoolean("bgmusic", false);
		soundFX = saveoptions.getBoolean("sound", false);
		
	}

	public boolean isTimer() {
		return timer;
	}

	public boolean isBgMusic() {
		return bgmusic;
	}

	public boolean isSoundFX() {
		return soundFX;
	}
}
