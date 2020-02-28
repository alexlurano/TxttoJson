package parser;

import java.util.ArrayList;

public class StringToDataForCards {
	String cardType;
	String name;
	int cost;
	int range = -1; // a range of -1 MEANS RANGE DOES NOT APPLY!!!
	String speed;
	String source;
	String targetType;
	String elementsGained;
	String cardText; // the card needs a func to parse this info out
	ArrayList<String> holdingArray = new ArrayList<String>();
	TextDocWriter w = new TextDocWriter();
	public StringToDataForCards() {
	}
	private void printJob(String args) { System.out.println(args); }

	private void printFile(ArrayList<String> args, boolean printNow) {
		String projLoc = System.getProperty("user.dir") + "\\src\\data\\" + "outputTest.txt";
		w.addInfoToWrite(args);
		if(printNow)
			w.writeListToFile(projLoc);
	}
	
	public void assignValues(ArrayList<String> args) {
		if(args.size()<8)
			return;
		holdingArray = args;
		// sometimes range doesnt exist, ripping out range at start is easier than adding null to array
		if(holdingArray.size() == 9) {
			range = Integer.valueOf(holdingArray.get(5));
			holdingArray.remove(5);
		}
		cardType = holdingArray.get(0);
		name= holdingArray.get(1);
		cost = Integer.parseInt(holdingArray.get(2));
		speed= holdingArray.get(3);
		source= holdingArray.get(4);
		targetType= holdingArray.get(5);
		elementsGained= holdingArray.get(6);
		cardText= holdingArray.get(7);
		
	}
	
	public String toString() {
		String returnString = cardType+ "\n" +
				name+"\n" +cost+"\n" +range+"\n" +speed+"\n" +source+"\n" +targetType+"\n" +"\n" +cardText;
		return returnString;
		
				
	}
	
	
}
