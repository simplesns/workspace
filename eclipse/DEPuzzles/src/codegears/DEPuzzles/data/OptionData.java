package codegears.DEPuzzles.data;

import android.content.Context;
import android.content.SharedPreferences;

public class OptionData {
	private boolean timer,bgmusic,soundFX;
	private boolean togglePop;
	private boolean toggleJazz;
	private boolean toggleSpace;
	private boolean toggleAcoustic;
	private boolean toggleHappy;
	
	public boolean getTimer(){
		return timer;
	}
	
	public boolean getBgmusic(){
		return bgmusic;
	}
	
	public boolean getSoundFX(){
		return soundFX;
	}
	
	public boolean getTogglePop() {
		return togglePop;
	}
	
	public boolean getToggleJazz() {
		return toggleJazz;
	}
	
	public boolean getToggleSpace() {
		return toggleSpace;
	}
	
	public boolean getToggleAcoustic() {
		return toggleAcoustic;
	}
	
	public boolean getToggleHappy() {
		return toggleHappy;
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
	
	public void setTogglePop(boolean togglePop) {
		this.togglePop = togglePop;
	}
	
	public void setToggleJazz(boolean toggleJazz) {
		this.toggleJazz = toggleJazz;
	}
	
	public void setToggleSpace(boolean toggleSpace) {
		this.toggleSpace = toggleSpace;
	}
	
	public void setToggleAcoustic(boolean toggleAcoustic) {
		this.toggleAcoustic = toggleAcoustic;
	}
	
	public void setToggleHappy(boolean toggleHappy) {
		this.toggleHappy = toggleHappy;
	}
	
	public void save(Context context){
		SharedPreferences saveoptions = context.getSharedPreferences("Options", 0);
		SharedPreferences.Editor editoption = saveoptions.edit();
		
		editoption.putBoolean("timer", timer);
		editoption.putBoolean("bgmusic", bgmusic);
		editoption.putBoolean("sound", soundFX);
		editoption.putBoolean("muPop", togglePop);
		editoption.putBoolean("muJazz", toggleJazz);
		editoption.putBoolean("muSpace", toggleSpace);
		editoption.putBoolean("muAcoustic", toggleAcoustic);
		editoption.putBoolean("muHappy", toggleHappy);

		editoption.commit();
	}
	
	public void load(Context context){
		SharedPreferences saveoptions = context.getSharedPreferences("Options", 0);
		
		timer = saveoptions.getBoolean("timer", false);
		bgmusic = saveoptions.getBoolean("bgmusic", false);
		soundFX = saveoptions.getBoolean("sound", false);
		togglePop = saveoptions.getBoolean("muPop", false);
		toggleJazz = saveoptions.getBoolean("muJazz", false);
		toggleSpace = saveoptions.getBoolean("muSpace", false);
		toggleAcoustic = saveoptions.getBoolean("muAcoustic", false);
		toggleHappy = saveoptions.getBoolean("muHappy", false);
		
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

	public boolean isTogglePop() {
		return togglePop;
	}
	
	public boolean isToggleJazz() {
		return toggleJazz;
	}
	
	public boolean isToggleSpace() {
		return toggleSpace;
	}
	
	public boolean isToggleAcoustic() {
		return toggleAcoustic;
	}
	
	public boolean isToggleHappy() {
		return toggleHappy;
	}
}
