package codegears.DEPuzzles.data;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CrosswordsTile;

public class CrosswordsWord {

	private ArrayList<CrosswordsTile> tileList;
	private String clue;

	public void setClue(String clue){
		this.clue = clue;
	}
	
	public String toString(){
		return clue;
	}
}