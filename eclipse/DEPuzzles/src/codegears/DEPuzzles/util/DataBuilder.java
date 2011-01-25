package codegears.DEPuzzles.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

import codegears.DEPuzzles.data.CrosswordsClue;
import codegears.DEPuzzles.data.CrosswordsWord;
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
				data.setText(text + "" + count);
				dataList.add(data);
				line = bReader.readLine();
				count++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public static String[] createCrosswordsGridFromAsset(Context context,
			String file) {
		String[] str = new String[13];
		AssetManager aManager = context.getAssets();
		try {
			InputStream iStream = aManager.open(file);
			BufferedReader bReader = new BufferedReader(
					new InputStreamReader(iStream));
			String line = bReader.readLine();
			String readedData = "";
			while (line != null) {
				readedData += line;
				line = bReader.readLine();
			}
			for (int i = 0; i < str.length; i++) {
				str[i] = readedData.substring(0, 13);
				readedData = readedData.substring(13);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static CrosswordsClue createCrosswordsClueFromAsset(Context context, String file){
		CrosswordsClue clue = new CrosswordsClue();
		ArrayList<String> aClue = new ArrayList<String>();
		ArrayList<String> dClue = new ArrayList<String>();
		AssetManager aManager = context.getAssets();
		try {
			InputStream iStream = aManager.open(file);
			BufferedReader bReader = new BufferedReader(
					new InputStreamReader(iStream));
			String line = bReader.readLine();
			while (line != null) {
				CrosswordsWord word = new CrosswordsWord();
				word.setClue(line);
				if(line.startsWith("A")){
					clue.addAWord(word);
				} else {
					clue.addDWord(word);
				}
				line = bReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clue;
	}
	
	public static String[] createNumberCrunchQuizFromAsset(Context context,
			String file) {
		String[] str = new String[19];
		AssetManager aManager = context.getAssets();
		try {
			InputStream iStream = aManager.open(file);
			BufferedReader bReader = new BufferedReader(
					new InputStreamReader(iStream));
			String line = bReader.readLine();
			str = line.split(",");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
