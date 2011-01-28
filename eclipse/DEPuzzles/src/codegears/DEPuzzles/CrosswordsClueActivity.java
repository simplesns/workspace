package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.ui.ClueItem;
import codegears.DEPuzzles.util.DataBuilder;

import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CrosswordsClueActivity extends Activity implements OnClickListener {

	private Button done;
	private ListView list;
	private ArrayList<Object> data;
	private ClueListAdapter listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.crosswordsclue);
		Intent i = getIntent();
		String file = i.getStringExtra(PuzzleSelectActivity.EXTRA_FILE);
		data = DataBuilder.createClueListFromAsset(this, "CrosswordsPuzzle/"
				+ file + ".clues.txt");
		list = (ListView) findViewById(R.id.CrosswordsClueList);
		listAdapter = new ClueListAdapter(this);
		listAdapter.setData(data);
		list.setAdapter(listAdapter);
		done = (Button) findViewById(R.id.CrosswordsClueDone);
		done.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}

	private class ClueListAdapter extends BaseAdapter {

		private ArrayList<Object> data;
		private Context context;

		public ClueListAdapter(Context context) {
			this.context = context;
		}

		public void setData(ArrayList<Object> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			if (data == null) {
				return 0;
			}
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			if (data == null) {
				return null;
			}
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Object d = data.get(position);
			if(d instanceof String){
				TextView t = new TextView(context);
				t.setText((String)d);
				return t;
			} else {
				ClueItem c = new ClueItem(context);
				return c;
			}
		}

	}
}
