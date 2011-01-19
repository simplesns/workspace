package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.os.Bundle;

public class MenuActivity extends GameActivity {
    
	private MenuScreen screen;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        //SharedResource.init(this);
        screen = new MenuScreen(this);
        //this.set((GameSurfaceView)this.findViewById(R.id.surface), screen);
    }

	@Override
	public void changeScreen(int requestCode) {
	}
}