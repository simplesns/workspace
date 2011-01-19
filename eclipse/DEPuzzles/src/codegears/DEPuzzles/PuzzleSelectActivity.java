package codegears.DEPuzzles;

import java.util.ArrayList;

import codegears.DEPuzzles.data.PuzzleSelectData;
import codegears.DEPuzzles.ui.PuzzleSelectListItem;
import codegears.DEPuzzles.util.DataBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class PuzzleSelectActivity extends Activity {
	
	private ListView list;
	private PuzzleSelectAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.puzzleselect);		 
		 list = (ListView)findViewById(R.id.ListView01);
		 adapter = new PuzzleSelectAdapter(this);
		 DataBuilder.createPuzzleSelectDataFromAsset(this, "CodeWordsPuzzle/CW_easy.txt");
//		 adapter.setData(data1);
		 list.setAdapter(adapter);
	}
	
	private class PuzzleSelectAdapter extends BaseAdapter {

		private ArrayList<PuzzleSelectData> data;
		private Context context;
		
		public PuzzleSelectAdapter(Context context){
			this.context = context;
		}
		
		public void setData(ArrayList<PuzzleSelectData> data){
			this.data = data;
		}
		
		@Override
		public int getCount() {
			if(data != null){
				return data.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if(data != null){
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
			if(data != null){
				PuzzleSelectListItem view = new PuzzleSelectListItem(context);
				return view;
			}
			return null;
		}
	}
}
