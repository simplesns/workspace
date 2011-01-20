package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends GameActivity {

	
	private MenuScreen screen;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
	
       	setContentView(R.layout.mainmenu);
        screen = new MenuScreen(this);
        this.set((GameSurfaceView) this.findViewById(R.id.SurfaceMenu), screen);
        
    }

	@Override
	public void changeScreen(int requestCode) {
	}
}