package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.data.PuzzleSelectData;
import codegears.DEPuzzles.ui.PuzzleSelectListItem;
import codegears.DEPuzzles.util.DataBuilder;
import freehand.neandroid.GameActivity;
import freehand.neandroid.widget.ClickListener;
import freehand.neandroid.widget.CompoundButtonGroup;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PuzzleSelectActivity extends Activity implements ClickListener {

	private ListView list;
	private PuzzleSelectAdapter adapter;
	private String game;
	private ArrayList<PuzzleSelectData>[] puzzleSelectList;
	private ToggleButton[] toggleButton;
	private CompoundButtonGroup group;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.puzzleselect);
		list = (ListView) findViewById(R.id.PuzzleSelectListView);
		adapter = new PuzzleSelectAdapter(this);
		toggleButton = new ToggleButton[4];
		toggleButton[0] = (ToggleButton) findViewById(R.id.PuzzleSelectToggleButton1);
		toggleButton[1] = (ToggleButton) findViewById(R.id.PuzzleSelectToggleButton2);
		toggleButton[2] = (ToggleButton) findViewById(R.id.PuzzleSelectToggleButton3);
		toggleButton[3] = (ToggleButton) findViewById(R.id.PuzzleSelectToggleButton4);
		group = new CompoundButtonGroup();
		group.setClickListener(this);
		for (ToggleButton t : toggleButton) {
			group.add(t);
		}
		// get game from Intent
		game = "codeword";
		if (game.equals(MenuActivity.CODEWORD)) {
			puzzleSelectList = new ArrayList[4];
			puzzleSelectList[0] = DataBuilder.createPuzzleSelectDataFromAsset(this,
					"CodeWordsPuzzle/CW_easy.txt", "easy");
			puzzleSelectList[1] = DataBuilder.createPuzzleSelectDataFromAsset(this,
					"CodeWordsPuzzle/CW_medium.txt", "medium");
			puzzleSelectList[2] = DataBuilder.createPuzzleSelectDataFromAsset(this,
					"CodeWordsPuzzle/CW_hard.txt", "hard");
			puzzleSelectList[3] = new ArrayList<PuzzleSelectData>();
			adapter.setData(puzzleSelectList[0]);
		}
		toggleButton[0].toggle();
		list.setAdapter(adapter);
	}

	@Override
	public void onClick(View view) {
		for (int i = 0; i < toggleButton.length; i++) {
			if (toggleButton[i].equals(view)) {
				adapter.setData(puzzleSelectList[i]);
				adapter.notifyDataSetInvalidated();
			}
		}
	}

	private class PuzzleSelectAdapter extends BaseAdapter {

		private ArrayList<PuzzleSelectData> data;
		private Context context;

		public PuzzleSelectAdapter(Context context) {
			this.context = context;
		}

		public void setData(ArrayList<PuzzleSelectData> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			if (data != null) {
				return data.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if (data != null) {
				return data.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (data != null) {
				PuzzleSelectListItem view = null;
				if(convertView == null){
					view = new PuzzleSelectListItem(context);
					view.setData(data.get(position));
				} else {
					view = (PuzzleSelectListItem)convertView;
					view.setData(data.get(position));
				}
				return view;
			}
			return null;
		}
	}

}
