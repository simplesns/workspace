package codegears.DEPuzzles.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

import codegears.DEPuzzles.data.PuzzleSelectData;

public class DataBuilder {

	public static ArrayList<PuzzleSelectData> createPuzzleSelectDataFromAsset(Context context, String file){
		AssetManager manager= context.getAssets();
		try {
			InputStream iStream = manager.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
