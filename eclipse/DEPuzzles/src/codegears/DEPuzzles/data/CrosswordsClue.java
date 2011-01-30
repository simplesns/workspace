package codegears.DEPuzzles.data;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CrosswordsBoard;
import codegears.DEPuzzles.ui.CrosswordsTile;

public class CrosswordsClue {

	private ArrayList<CrosswordsWord> aWord;
	private ArrayList<CrosswordsWord> dWord;

	public CrosswordsClue() {
		aWord = new ArrayList<CrosswordsWord>();
		dWord = new ArrayList<CrosswordsWord>();
	}

	public void addAWord(CrosswordsWord word) {
		aWord.add(word);
	}

	public void addDWord(CrosswordsWord word) {
		dWord.add(word);
	}

	public CrosswordsWord getFirstWord() {
		return aWord.get(0);
	}

	public CrosswordsWord getPreviousWord(CrosswordsWord current) {
		for (int i = 0; i < aWord.size(); i++) {
			if (aWord.get(i).equals(current)) {
				if (i == 0) {
					return aWord.get(aWord.size() - 1);
				} else {
					return aWord.get(i - 1);
				}
			}
		}
		for (int i = 0; i < dWord.size(); i++) {
			if (dWord.get(i).equals(current)) {
				if (i == 0) {
					return dWord.get(dWord.size() - 1);
				} else {
					return dWord.get(i - 1);
				}
			}
		}
		return null;
	}

	public CrosswordsWord getNextWord(CrosswordsWord current) {
		for (int i = 0; i < aWord.size(); i++) {
			if (aWord.get(i).equals(current)) {
				if (i == aWord.size() - 1) {
					return aWord.get(0);
				} else {
					return aWord.get(i + 1);
				}
			}
		}
		for (int i = 0; i < dWord.size(); i++) {
			if (dWord.get(i).equals(current)) {
				if (i == dWord.size() - 1) {
					return dWord.get(0);
				} else {
					return dWord.get(i + 1);
				}
			}
		}
		return null;
	}

	public CrosswordsWord getWordFromId(String id) {
		for (CrosswordsWord w : aWord) {
			if (w.getClue().startsWith(id)) {
				return w;
			}
		}
		for (CrosswordsWord w : dWord) {
			if (w.getClue().startsWith(id)) {
				return w;
			}
		}
		return null;
	}

	public void reveal() {
		for (CrosswordsWord w : aWord) {
			w.reveal();
		}
		for (CrosswordsWord w : dWord) {
			w.reveal();
		}
	}

	public CrosswordsTile getHintBarClickedTile(CrosswordsBoard board,
			CrosswordsWord word, CrosswordsTile tile) {
		int position = board.getPosition(tile);
		CrosswordsTile t = tile;
		if (isAWord(word)) {
			// result must be in dWord
			while (true) {
				for (CrosswordsWord w : dWord) {
					if (w.isContain(t)) {
						return t;
					}
				}
				t = board.getNextTile(tile);
				tile = t;
			}
		} else if (isDWord(word)) {
			// result must be in aWord
			while (true) {
				for (CrosswordsWord w : aWord) {
					if (w.isContain(t)) {
						return t;
					}
				}
				t = board.getNextTile(tile);
				tile = t;
			}
		}
		return null;
	}

	public CrosswordsWord getHintBarClickedWord(CrosswordsBoard board,
			CrosswordsWord word, CrosswordsTile tile) {
		// if word is in aWord
		if (isAWord(word)) {
			// answer is in dWord
			for (CrosswordsWord w : dWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
			// else if word is in dword
		} else if (isDWord(word)) {
			// answer is in aWord
			for (CrosswordsWord w : aWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
		}
		return null;
	}

	public boolean isAWord(CrosswordsWord word) {
		for (CrosswordsWord w : aWord) {
			if (word.equals(w)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDWord(CrosswordsWord word) {
		for (CrosswordsWord w : dWord) {
			if (word.equals(w)) {
				return true;
			}
		}
		return false;
	}

	public void scanBoard(CrosswordsBoard board) {
		String[] boardData = board.getBoardData();
		int wordCount = 0;
		// horizontal scan
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < (boardData[i].length() - 2); j++) {
				if ((boardData[i].charAt(j) != '.')
						&& (boardData[i].charAt(j + 1) != '.')) {
					if (j == 0) {
						addNewAWord(wordCount, board, i, j);
						wordCount++;
					} else if (boardData[i].charAt(j - 1) == '.') {
						addNewAWord(wordCount, board, i, j);
						wordCount++;
					}
				}
			}
		}
		wordCount = 0;
		// vertical scan
		for (int i = 0; i < boardData.length - 2; i++) {
			for (int j = 0; j < (boardData[i].length()); j++) {
				if ((boardData[i].charAt(j) != '.')
						&& (boardData[i + 1].charAt(j) != '.')) {
					if (i == 0) {
						addNewDWord(wordCount, board, i, j);
						wordCount++;
					} else if (boardData[i - 1].charAt(j) == '.') {
						addNewDWord(wordCount, board, i, j);
						wordCount++;
					}
				}
			}
		}
	}

	private void addNewAWord(int index, CrosswordsBoard board, int i, int j) {
		CrosswordsWord word = aWord.get(index);
		int length = board.getALength(i, j);
		ArrayList<CrosswordsTile> tile = new ArrayList<CrosswordsTile>();
		for (int loop = 0; loop < length; loop++) {
			tile.add(board.getTile(i, j + loop));
		}
		word.setTileList(tile);
		word.setNumberToTile();
	}

	private void addNewDWord(int index, CrosswordsBoard board, int i, int j) {
		CrosswordsWord word = dWord.get(index);
		int length = board.getDLength(i, j);
		ArrayList<CrosswordsTile> tile = new ArrayList<CrosswordsTile>();
		for (int loop = 0; loop < length; loop++) {
			tile.add(board.getTile(i + loop, j));
		}
		word.setTileList(tile);
		word.setNumberToTile();
	}

	@Override
	public String toString() {
		String str = "CrosswordsClue:\n";
		for (CrosswordsWord w : aWord) {
			str += w.toString();
		}
		for (CrosswordsWord w : dWord) {
			str += w.toString();
		}
		return str;
	}

	public boolean isFull() {
		for (CrosswordsWord w : aWord) {
			if (!w.isFilled()) {
				return false;
			}
		}
		for (CrosswordsWord w : dWord) {
			if (!w.isFilled()) {
				return false;
			}
		}
		return true;
	}

	public boolean isComplete() {
		for (CrosswordsWord w : aWord) {
			if (!w.isComplete()) {
				return false;
			}
		}
		for (CrosswordsWord w : dWord) {
			if (!w.isComplete()) {
				return false;
			}
		}
		return true;
	}

	public void showError() {
		for (CrosswordsWord w : aWord) {
			w.showError();
		}
		for (CrosswordsWord w : dWord) {
			w.showError();
		}
	}

	public void emptyBoard() {
		for (CrosswordsWord w : aWord) {
			w.emptyTile();
		}
		for (CrosswordsWord w : dWord) {
			w.emptyTile();
		}
	}

	public void clearErrors() {
		for (CrosswordsWord w : aWord) {
			w.clearErrors();
		}
		for (CrosswordsWord w : dWord) {
			w.clearErrors();
		}
	}

	public CrosswordsWord getWordFromTouch(CrosswordsWord word,
			CrosswordsTile tile) {
		if (isAWord(word)) {
			for (CrosswordsWord w : aWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
			for (CrosswordsWord w : dWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
		} else if (isDWord(word)) {
			for (CrosswordsWord w : dWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
			for (CrosswordsWord w : aWord) {
				if (w.isContain(tile)) {
					return w;
				}
			}
		}
		return null;
	}
}
