package codegears.DEPuzzles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import freehand.neandroid.GameActivity;
import freehand.neandroid.Screen;
import freehand.neandroid.ui.ButtonUI;
import freehand.neandroid.ui.ClickListener;
import freehand.neandroid.ui.Drawable;
import freehand.neandroid.ui.ImageUI;

public class MenuScreen extends Screen {
	private ButtonUI optionButton;
	private ButtonUI crosswordButton;
	private ButtonUI wordPuzzlerButton;
	private ButtonUI numberCrunchButton;
	private ButtonUI codeWordButton;
	private ButtonUI sudokuButton;
	private ButtonUI wordSearchButton;
	private ButtonUI closeDialogButton;
	private ButtonUI playGameButton;
	private ImageUI background;
	private Bitmap bgpic;
	private Bitmap option ;
	private Bitmap crosswords ;
	private Bitmap wordPuzzler ;
	private Bitmap numberCrunch ;
	private Bitmap codeWord ;
	private Bitmap sudoku ;
	private Bitmap wordSearch ;
	private Bitmap closeDialog;
	private Bitmap playGame;
	private static int DRAWBUTTON_Y_OPTION;
	private static int DRAWBUTTON_X_OPTION;
	private static int DRAWBUTTON_Y_CROSSWORDS;
	private static int DRAWBUTTON_X_CROSSWORDS;
	private static int DRAWBUTTON_Y_WORDPUZZLER;
	private static int DRAWBUTTON_X_WORDPUZZLER;
	private static int DRAWBUTTON_Y_NUMBERCRUNCH;
	private static int DRAWBUTTON_X_NUMBERCRUNCH;
	private static int DRAWBUTTON_Y_CODEWORD;
	private static int DRAWBUTTON_X_CODEWORD;
	private static int DRAWBUTTON_Y_SUDOKU;
	private static int DRAWBUTTON_X_SUDOKU;
	private static int DRAWBUTTON_Y_WORDSEARCH;
	private static int DRAWBUTTON_X_WORDSEARCH;
	private static int DRAWBUTTON_Y_CLOSEDIALOG;
	private static int DRAWBUTTON_X_CLOSEDIALOG;
	private static int DRAWBUTTON_X_PLAYGAME;
	private static int DRAWBUTTON_Y_PLAYGAME;
	
	public static final String CROSSWORDSGAME = "crosswords";
	public static final String SUDOKUGAME = "sudoku";
	public static final String WORDPUZZLERGAME = "wordPuzzler";
	public static final String NUMBERCRUNCHGAME = "numberCrunch";
	public static final String WORDSEARCHGAME = "wordSearch";
	public static final String CODEWORDGAME = "codeword";
	
	MenuScreen(GameActivity activity) {
		super(activity);
		
		SharedResource.init(activity);
		DRAWBUTTON_X_OPTION = 0;
		DRAWBUTTON_Y_OPTION = (int)(SharedResource.screenH * 0.14f);
		DRAWBUTTON_X_CROSSWORDS = 0;
		DRAWBUTTON_Y_CROSSWORDS = (int) (SharedResource.screenH * 0.27f);
		DRAWBUTTON_X_WORDPUZZLER = (int) (SharedResource.screenW * 0.49f);
		DRAWBUTTON_Y_WORDPUZZLER = (int) (SharedResource.screenH * 0.27f);
		DRAWBUTTON_X_NUMBERCRUNCH = (int) (SharedResource.screenW * 0.75f);
		DRAWBUTTON_Y_NUMBERCRUNCH = (int) (SharedResource.screenH * 0.27f);
		DRAWBUTTON_X_CODEWORD = 0;
		DRAWBUTTON_Y_CODEWORD = (int) (SharedResource.screenH * 0.68f);
		DRAWBUTTON_X_SUDOKU = (int) (SharedResource.screenW * 0.49f);
		DRAWBUTTON_Y_SUDOKU = (int) (SharedResource.screenH * 0.66f);
		DRAWBUTTON_X_WORDSEARCH = (int) (SharedResource.screenW * 0.75f);
		DRAWBUTTON_Y_WORDSEARCH = (int) (SharedResource.screenH * 0.66f);
		DRAWBUTTON_X_CLOSEDIALOG = (int) (SharedResource.screenW * 0.25f);
		DRAWBUTTON_Y_CLOSEDIALOG = (int) (SharedResource.screenH * 0.60f);
		DRAWBUTTON_X_PLAYGAME = (int) (SharedResource.screenW * 0.50f);
		DRAWBUTTON_Y_PLAYGAME = (int) (SharedResource.screenH * 0.60f);
		
		bgpic = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bd_mainmenu);
		option  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_option);
		crosswords  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_crosswords);
		wordPuzzler  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_wordpuzzler);
		numberCrunch  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_numbercrunch);
		codeWord  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_codeword);
		sudoku  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_sudoku);
		wordSearch  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_wordsearch);
		closeDialog  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_closedialog);
		playGame  = BitmapFactory.decodeResource(activity.getResources(), R.drawable.menu_playgame);
		
		background = new ImageUI();
		optionButton = new ButtonUI();
		crosswordButton = new ButtonUI();
		wordPuzzlerButton = new ButtonUI();
		numberCrunchButton = new ButtonUI();
		codeWordButton = new ButtonUI();
		sudokuButton = new ButtonUI();
		wordSearchButton = new ButtonUI();
		closeDialogButton = new ButtonUI();
		playGameButton = new ButtonUI();
		
		
		
		background.setBitmap(bgpic);
		optionButton.setBitmap(option);
		crosswordButton.setBitmap(crosswords);
		wordPuzzlerButton.setBitmap(wordPuzzler);
		numberCrunchButton.setBitmap(numberCrunch);
		codeWordButton.setBitmap(codeWord);
		sudokuButton.setBitmap(sudoku);
		wordSearchButton.setBitmap(wordSearch);
		closeDialogButton.setBitmap(closeDialog);
		playGameButton.setBitmap(playGame);
		
		closeDialogButton.setVisible(false);
		playGameButton.setVisible(false);
		
		background.setPosition(0,0);
		optionButton.setPosition(DRAWBUTTON_X_OPTION,DRAWBUTTON_Y_OPTION);
		crosswordButton.setPosition(DRAWBUTTON_X_CROSSWORDS,DRAWBUTTON_Y_CROSSWORDS);
		wordPuzzlerButton.setPosition(DRAWBUTTON_X_WORDPUZZLER,DRAWBUTTON_Y_WORDPUZZLER);
		numberCrunchButton.setPosition(DRAWBUTTON_X_NUMBERCRUNCH,DRAWBUTTON_Y_NUMBERCRUNCH);
		codeWordButton.setPosition(DRAWBUTTON_X_CODEWORD,DRAWBUTTON_Y_CODEWORD);
		sudokuButton.setPosition(DRAWBUTTON_X_SUDOKU,DRAWBUTTON_Y_SUDOKU);
		wordSearchButton.setPosition(DRAWBUTTON_X_WORDSEARCH,DRAWBUTTON_Y_WORDSEARCH);
		closeDialogButton.setPosition(DRAWBUTTON_X_CLOSEDIALOG, DRAWBUTTON_Y_CLOSEDIALOG);
		playGameButton.setPosition(DRAWBUTTON_X_PLAYGAME, DRAWBUTTON_Y_PLAYGAME);
		
		optionButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				Intent i = new Intent(MenuScreen.this.activity,OptionActivity.class);
				MenuScreen.this.activity.startActivity(i);
			}
		});
		
		crosswordButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(CROSSWORDSGAME);
			}
		});
		
		wordPuzzlerButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(WORDPUZZLERGAME);
			}
		});
		
		numberCrunchButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(NUMBERCRUNCHGAME);
			}
		});
		
		codeWordButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(CODEWORDGAME);
			}
		});
		
		sudokuButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(SUDOKUGAME);
			}
		});
		
		wordSearchButton.setClickListener(new ClickListener() {
			@Override
			public void onClick(Drawable drawObject) {
				onClickGame(WORDSEARCHGAME);
			}
		});
		
		this.add(background);
		this.add(optionButton);
		this.add(crosswordButton);
		this.add(wordPuzzlerButton);
		this.add(numberCrunchButton);
		this.add(codeWordButton);
		this.add(sudokuButton);
		this.add(wordSearchButton);
		this.add(closeDialogButton);
		this.add(playGameButton);
	}
	
	void onClickGame(final String gameName) {
		closeDialogButton.setVisible(true);
		playGameButton.setVisible(true);
		
		closeDialogButton.setClickListener(new ClickListener() {
			
			@Override
			public void onClick(Drawable drawObject) {
				closeDialogButton.setVisible(false);
				playGameButton.setVisible(false);
			}
		});
		
		playGameButton.setClickListener(new ClickListener() {
			
			@Override
			public void onClick(Drawable drawObject) {
				Intent i = new Intent(MenuScreen.this.activity,PuzzleSelectActivity.class);
				i.putExtra("gameName", gameName);
				MenuScreen.this.activity.startActivity(i);
			}
		});
	}
}