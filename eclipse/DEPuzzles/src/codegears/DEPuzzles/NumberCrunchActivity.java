package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.data.PuzzleSelectData;
import codegears.DEPuzzles.ui.NumPad;
import codegears.DEPuzzles.ui.NumPadListener;
import codegears.DEPuzzles.ui.NumberCrunchGame;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import freehand.neandroid.GameSurfaceView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class NumberCrunchActivity extends Activity implements NumPadListener {
	
	private NumPad numPad;
	private Button hint;
	private Button reset;
	private LinearLayout game;
	private String mission;
	private String[] quiz;
	public static final String EXTRA_FILE = "file";
	private int[] result;
	private String[] splitValue;
	private NumberCrunchGame setValue;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.numbercrunch);
		numPad = (NumPad)this.findViewById(R.id.TableNumPad);
		numPad.setNumPadListener(this);
		game = (LinearLayout) findViewById(R.id.LinearNumberCrunchArea);
		
		setGameArea();
	}
	
	private void setGameArea(){
		mission = this.getIntent().getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		
		quiz = DataBuilder.createNumberCrunchQuizFromAsset(this,"NumberCrunchPuzzle/quiz/"+mission+".txt");
		int j = 0;
		for(int i = 0; i < quiz.length; i+=2){
			if(i == 0){
				setValue = new NumberCrunchGame(this);
				setValue.setNum1(quiz[0]);
				setValue.setNumOperation(quiz[1]);
				setValue.setNum2(quiz[2]);
				
				if(quiz[0].indexOf("/") > 0){
					splitValue = quiz[0].split("/");
					quiz[0] = String.valueOf(Float.parseFloat(splitValue[0])/Integer.parseInt(splitValue[1]));
				}
				if(quiz[2].indexOf("/") > 0){
					splitValue = quiz[2].split("/");
					quiz[2] = String.valueOf(Float.parseFloat(splitValue[0])/Integer.parseInt(splitValue[1]));
				}
				result = new int[9];
				if(quiz[1].equals("x")){
					result[j] = (int) (Double.valueOf(quiz[0])*Double.valueOf(quiz[2]));
				}else if(quiz[1].equals("%")){
					result[j] = (int) ((Double.valueOf(quiz[0])*Double.valueOf(quiz[2]))/100);
				}else if(quiz[1].equals("^")){
					result[j] = (int) Math.pow(Double.valueOf(quiz[0]), 2);
					setValue.setNumOperation("squared");
					setValue.setNum2("");
				}else if(quiz[1].equals("rt")){
					result[j] = (int) Math.sqrt(Double.valueOf(quiz[0]));
					setValue.setNumOperation("√");
					setValue.setNum2("");
				}else if(quiz[1].equals("+")){
					result[j] = (int) (Double.valueOf(quiz[0])+Double.valueOf(quiz[2]));
				}else if(quiz[1].equals("-")){
					result[j] = (int) (Double.valueOf(quiz[0])-Double.valueOf(quiz[2]));
				}else if(quiz[1].equals("/")){
					result[j] = (int) (Double.valueOf(quiz[0])/Double.valueOf(quiz[2]));
				}
				i++;
				setValue.setResult(result[j]);
			}else {
				setValue = new NumberCrunchGame(this);
				setValue.setNum1(String.valueOf(result[j-1]));
				setValue.setNumOperation(quiz[i]);
				setValue.setNum2(quiz[i+1]);
				
				if(quiz[i+1].indexOf("/") > 0){
					splitValue = quiz[i+1].split("/");
					System.out.println(quiz[i+1]);
					quiz[i+1] = String.valueOf(Float.parseFloat(splitValue[0])/Integer.parseInt(splitValue[1]));
				}
				
				if(quiz[i].equals("x")){
					result[j] = (int) (result[j-1]*Double.valueOf(quiz[i+1]));
				}else if(quiz[i].equals("%")){
					result[j] = (int) ((result[j-1]*Double.valueOf(quiz[i+1]))/100);
				}else if(quiz[i].equals("^")){
					result[j] = (int) Math.pow(result[j-1], 2);
					setValue.setNumOperation("squared");
					setValue.setNum2("");
				}else if(quiz[i].equals("rt")){
					result[j] = (int) Math.sqrt(result[j-1]);
					setValue.setNumOperation("√");
					setValue.setNum2("");
				}else if(quiz[i].equals("+")){
					result[j] = (int) (result[j-1]+Double.valueOf(quiz[i+1]));
				}else if(quiz[i].equals("-")){
					result[j] = (int) (result[j-1]-Double.valueOf(quiz[i+1]));
				}else if(quiz[i].equals("/")){
					result[j] = (int) (result[j-1]/Double.valueOf(quiz[i+1]));
				}
				setValue.setResult(result[j]);
			}
			j++;
			game.addView(setValue);
		}
	}

	@Override
	public void onNumPadClicked(int keyCode) {
		//find cursor 
		View view = getCurrentFocus();
		if(!(view instanceof EditText)){
			return;
		}
		//add text on that EditText
		EditText editText = (EditText)view;
		//calculate for correct answer
		char c = NumPad.getChar(keyCode); //Chatchai
		if(keyCode == NumPad.NUMPAD_x){
			//delete the last one
		} else {
			editText.append(String.valueOf(c));
		}
		String text = editText.getText().toString();
		//if correct
		if(text.equals(text)){	//Chatchai
			//cursor move or finish game
			//change ui_numbercrunchgame state
		}
		
	}
}