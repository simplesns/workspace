package codegears.DEPuzzles.data;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.CrosswordsBoard;

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

	public void scanBoard(CrosswordsBoard board) {
		String[] boardData = board.getBoardData();
		int wordCount = 0;
		// horizontal scan
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < (boardData[i].length() - 2); j++) {
				// check current position and next position, if both is character then
				if ((boardData[i].charAt(j) != '.')
						&& (boardData[i].charAt(j + 1) != '.')) {
					// check backward position if it empty then
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
	}

	private void addNewAWord(int index, CrosswordsBoard board, int i, int j) {
		CrosswordsWord word = aWord.get(index);
		int length = board.getALength(i, j);
		//get all Tile from [i,j] to [i, j + length]
		//add all Tile to word
	}

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
}
