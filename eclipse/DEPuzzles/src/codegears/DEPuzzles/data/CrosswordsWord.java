package codegears.DEPuzzles.data;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CrosswordsTile;

public class CrosswordsWord {

	private ArrayList<CrosswordsTile> tileList;
	private String clue;

	public void setClue(String clue) {
		this.clue = clue;
	}

	public CrosswordsTile getFirstTile() {
		if (tileList != null) {
			if (tileList.size() > 0) {
				return tileList.get(0);
			}
		}
		return null;
	}

	public boolean isContain(CrosswordsTile tile) {
		for (CrosswordsTile t : tileList) {
			if (t.equals(tile)) {
				return true;
			}
		}
		return false;
	}

	public void selected() {
		for (CrosswordsTile t : tileList) {
			t.wordSelected();
		}
	}

	public void unselect() {
		for (CrosswordsTile t : tileList) {
			t.unselect();
		}
	}

	public String getClue() {
		return clue;
	}

	public String toString() {
		String str = "Word: ";
		for (CrosswordsTile t : tileList) {
			str += t.getResult();
		}
		return str;
	}

	public void setTileList(ArrayList<CrosswordsTile> tile) {
		tileList = tile;
	}

	public void setNumberToTile() {
		String number = clue.substring(1, 3);
		if (number.contains(":")) {
			number = number.substring(0,1);
		}
		getFirstTile().setNumber(number);
	}
}