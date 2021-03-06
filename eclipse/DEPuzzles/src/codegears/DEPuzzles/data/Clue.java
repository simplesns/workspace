package codegears.DEPuzzles.data;

public class Clue {
	private String id;
	private String text;
	private String length;

	public void setData(String str) {
		// A4:Drinks lacking body? (7)
		String[] temp = str.split(":");
		id = temp[0];
		String[] temp2 = temp[1].split("[(]");
		text = temp2[0];
		length = "[" + temp2[1].substring(0, temp2[1].length() - 1) + "]";
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getLength() {
		return length;
	}
}
