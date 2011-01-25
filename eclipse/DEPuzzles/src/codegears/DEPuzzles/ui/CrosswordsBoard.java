package codegears.DEPuzzles.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CrosswordsBoard extends LinearLayout {

	private LinearLayout[] line;
	private CrosswordsTile[] tile;
	private String[] boardData;

	public CrosswordsBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(LinearLayout.VERTICAL);
		line = new LinearLayout[13];
		tile = new CrosswordsTile[13 * 13];
		for (int i = 0; i < 13; i++) {
			line[i] = new LinearLayout(context);
			for (int j = 0; j < 13; j++) {
				tile[(i * 13) + j] = new CrosswordsTile(context);
				line[i].addView(tile[(i * 13) + j], new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			}
			this.addView(line[i], new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		}
		this.invalidate();
	}

	public String[] getBoardData() {
		return boardData;
	}

	public int getALength(int i, int j) {
		int length = 0;
		for (int loop = j; loop < boardData[i].length(); loop++) {
			if (boardData[i].charAt(loop) == '.') {
				return length;
			} else {
				length++;
			}
		}
		return length;
	}

	public void setBoardData(String[] data) {
		boardData = data;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				if (data[i].charAt(j) == '.') {
					tile[(i * 13) + j].setBlack(true);
				} else {
					tile[(i * 13) + j].setResult(data[i].charAt(j));;
				}
			}
		}
	}
}