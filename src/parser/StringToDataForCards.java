package parser;

import java.util.ArrayList;

public class StringToDataForCards {
	String cardType;
	String name;
	int cost;
	String speed;
	String source;
	String targetType;
	String cardText; // the card needs a func to parse this info out
	ArrayList<String> holdingArray = new ArrayList<String>();
	TextDocWriter w = new TextDocWriter();
	public StringToDataForCards() {
	}
	
	private void printFile(ArrayList<String> args, boolean printNow) {
		String projLoc = System.getProperty("user.dir") + "\\src\\data\\" + "outputTest.txt";
		w.addInfoToWrite(args);
		if(printNow)
			w.writeListToFile(projLoc);
	}
	
	public void assignValues(ArrayList<String> args) {
		holdingArray = args;
		
	}
	
	
}
