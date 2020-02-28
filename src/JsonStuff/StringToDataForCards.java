package JsonStuff;

import java.util.ArrayList;
import parser.*;
import com.google.gson.*;
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
		
		for(int i=0;i<holdingArray.size();i++) { // brackets arent removed in tonestedarray file so snip them here to avoid problems int parsing
			holdingArray.set(i, holdingArray.get(i).replaceAll("[\\[\\]]", ""));
		}
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
	
	public String getCardType() {
		return cardType;
	}
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}
	public int getRange() {
		return range;
	}
	public String getSpeed() {
		return speed;
	}
	public String getSource() {
		return source;
	}
	public String getTargetType() {
		return targetType;
	}
	public String getElementsGained() {
		return elementsGained;
	}
	public String getCardText() {
		return cardText;
	}
	public String toString() {
		String returnString = cardType+ "\n" +
				name+"\n" +cost+"\n" +range+"\n" +speed+"\n" +source+"\n" +targetType+"\n" +"\n" +cardText;
		return returnString;
		
				
	}
	
	
}
