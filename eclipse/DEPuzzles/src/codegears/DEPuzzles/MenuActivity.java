package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.os.Bundle;

public class MenuActivity extends GameActivity {

	public static final String GAMENAME_EXTRA = "gameName";
	
	public static final String CROSSWORDSGAME = "crosswords";
	public static final String SUDOKUGAME = "sudoku";
	public static final String WORDPUZZLERGAME = "wordPuzzler";
	public static final String NUMBERCRUNCHGAME = "numberCrunch";
	public static final String WORDSEARCHGAME = "wordSearch";
	public static final String CODEWORD = "codeword";

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