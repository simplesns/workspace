package codegears.DEPuzzles.data;

public class Clue {
	private String id;
	private String text;
	private String length;

	public void setData(String str) {
		// A4:Drinks lacking body? (7)
		String[] temp = str.split(":");
		id = temp[0];
//		String[] temp2 = temp[1].split("(");
		text = temp[1];
		length = "N/A";
	}
	
	public String getId(){
		return id;
	}
	
	public String getText(){
		return text;
	}
	
	public String getLength(){
		return length;
	}
}
