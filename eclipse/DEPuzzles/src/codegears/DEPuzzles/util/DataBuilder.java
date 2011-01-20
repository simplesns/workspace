package codegears.DEPuzzles.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

import codegears.DEPuzzles.data.PuzzleSelectData;

public class DataBuilder {

	public static ArrayList<PuzzleSelectData> createPuzzleSelectDataFromAsset(
			Context context, String file, String text) {
		ArrayList<PuzzleSelectData> dataList = new ArrayList<PuzzleSelectData>();
		AssetManager manager = context.getAssets();
		try {
			InputStream iStream = manager.open(file);

			BufferedReader bReader = new BufferedReader(
					new InputStreamReader(iStream));
			String line = bReader.readLine();
			int count = 1;
			while (line != null) {
				PuzzleSelectData data = new PuzzleSelectData();
				data.setPuzzleFile(line);
				data.setText(text + "-" + count);
				dataList.add(data);
				line = bReader.readLine();
				count++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
}