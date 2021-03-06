package codegears.DEPuzzles.data;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CrosswordsTile;

public class CrosswordsWord {

	private ArrayList<CrosswordsTile> tileList;
	private String clue;

	public void setClue(String clue) {
		this.clue = clue;
	}

	public CrosswordsTile getLastTile() {
		if (tileList != null) {
			if (tileList.size() > 0) {
				return tileList.get(tileList.size() - 1);
			}
		}
		return null;
	}

	public CrosswordsTile getPreviousTile(CrosswordsTile tile) {
		for (int i = 0; i < tileList.size(); i++) {
			if (tileList.get(i).equals(tile)) {
				if (i == 0) {
					return null;
				} else {
					return tileList.get(i - 1);
				}
			}
		}
		return null;
	}

	public CrosswordsTile getFirstTile() {
		if (tileList != null) {
			if (tileList.size() > 0) {
				return tileList.get(0);
			}
		}
		return null;
	}

	public CrosswordsTile getNextEmptyTile(CrosswordsTile tile) {
		int i = 0;
		while (!tile.equals(tileList.get(i))) {
			i++;
			if (i >= tileList.size()) {
				return null;
			}
		}
		for (int j = i; j < tileList.size(); j++) {
			if (tileList.get(j).isEmpty()) {
				return tileList.get(j);
			}
		}
		return tile;
	}

	public boolean isFilled() {
		for (CrosswordsTile t : tileList) {
			if (!t.isFilled()) {
				return false;
			}
		}
		return true;
	}

	public boolean isComplete() {
		for (CrosswordsTile t : tileList) {
			if (!t.isComplete()) {
				return false;
			}
		}
		return true;
	}

	public void emptyTile() {
		for (CrosswordsTile t : tileList) {
			t.empty();
		}
	}

	public void clearErrors() {
		for (CrosswordsTile t : tileList) {
			t.clearErrors();
		}
	}

	public int reveal() {
		int count = 0;
		for (CrosswordsTile t : tileList) {
			if(t.reveal()){
				count++;
			}
		}
		return count;
	}

	public int showError() {
		int showCount = 0;
		for (CrosswordsTile t : tileList) {
			if(t.errorCheck()){
				showCount++;
			}
		}
		return showCount;
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
			number = number.substring(0, 1);
		}
		getFirstTile().setNumber(number);
	}
}