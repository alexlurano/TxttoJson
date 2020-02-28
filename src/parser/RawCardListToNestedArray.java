package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RawCardListToNestedArray {
	ArrayList<String> rawCards = new ArrayList<String>();
	ArrayList<StringToDataForCards> cleanCardInfo = new ArrayList<StringToDataForCards>();
	
	

	private void printJob(String args) { System.out.println(args); }
	public RawCardListToNestedArray() {
		// TODO Auto-generated constructor stub
	}
	
	public void createJsonFile(ArrayList<String> rawCardInfo) {
		rawCards = rawCardInfo;

		beautify(rawCards.get(1),1);
		beautify(rawCards.get(2),2);
//		for(int i=0;i<rawCards.size();i++) {
//			beautify(rawCards.get(i),i);
//		}
	}
	
	
	
	
	
	
	
	
	
	//------------------------------------------------------------------------------
	private void beautify(String rawInfo,int cardNum) {

		ArrayList<String> dicedCardInfo = new ArrayList<String>();
		ArrayList<String> completeCardInfo = new ArrayList<String>();
		String s = rawInfo;
		s = deleteCppRelics(s);
		dicedCardInfo = parseOutCommas(s);
		dicedCardInfo.forEach((thingy) -> System.out.println(thingy));
		completeCardInfo = combineInfoInBrackets(dicedCardInfo);
		cleanCardInfo.add(new StringToDataForCards(s));
		// send our parsed array of strings to be converted to primitive types.
		
		



	}
	
	private String deleteCppRelics(String uglyDuckling) {
		String gorgeousSwan = uglyDuckling;

        gorgeousSwan = gorgeousSwan.replaceAll("[a-zA-Z]*?\\(", ""); // delete cpp class calls
        gorgeousSwan = gorgeousSwan.replaceAll("\\s{2,}", ""); // deletes excessive whitespace
        gorgeousSwan = gorgeousSwan.replaceAll("ProductSet..+?(,.)", "");// deletes ProductSet.whatever and the comma
        gorgeousSwan = gorgeousSwan.replaceAll("PowerDeckType.", ""); //leaves either just minor,major,or spirit
        gorgeousSwan = gorgeousSwan.replaceAll("\"", "");	// gets rid of quotes
        gorgeousSwan = gorgeousSwan.replaceAll("\\)", "");
        gorgeousSwan = gorgeousSwan.replaceAll("new", "");
        gorgeousSwan = gorgeousSwan.replaceAll("\\s{2,}", " ");
        gorgeousSwan = gorgeousSwan.replaceAll("[,]$", ",k"); // so our parser doesnt lose the last element
        	
		return gorgeousSwan;
	}
	
	private ArrayList<String> parseOutCommas(String inputStuff) { // rips apart input until it cant find any more stuff tween commas then returns string "done"

		ArrayList<String> stuffToAdd = new ArrayList<String>();
		String s = inputStuff;
		String regexStatement = ".*?(?=,)."; //matches any number of things up to and including a comma
		Pattern p = Pattern.compile(regexStatement);
		Matcher m = p.matcher(s);
		
		   int commas = 0;
		   for(int i=0;i<s.length();i++)
		   {
			     if(s.charAt(i)==',') 
			    {
			    	 commas++;
			    }
		   }
		while(commas > 0) {
			
			if(m.find())
			{
				String leftToParse = s;
				String myFoundString = m.group();
				myFoundString = myFoundString.replaceAll(",", "");
				stuffToAdd.add(myFoundString);
				myFoundString = "";
				s = leftToParse;
	
			} else {
				printJob("error!");
			}

			commas--;
		}


		return stuffToAdd;
		
	}
	
	private ArrayList<String> combineInfoInBrackets(ArrayList<String> args) {
		ArrayList<String> uglyDataArr = new ArrayList<String>();
		ArrayList<String> correctDataArr = new ArrayList<String>();
		uglyDataArr = args;
		for(int i=0;i<uglyDataArr.size();i++) {
			String uglyStuff;
			String cleanElement;
			uglyStuff = uglyDataArr.get(i);
			//printJob(uglyStuff);
			if(uglyStuff.charAt(0) == '[') {
				printJob(uglyStuff);
				cleanElement = uglyStuff;
				uglyDataArr.remove(i);
				uglyStuff = uglyDataArr.get(i);
				printJob("found" + uglyStuff.charAt(uglyStuff.length()-1));
				if(uglyStuff.charAt(uglyStuff.length()-1) == ']') {
					cleanElement += uglyStuff;
					printJob(cleanElement);
					uglyDataArr.remove(i);
					uglyStuff = uglyDataArr.get(i);
				} else {
					while(uglyStuff.charAt(uglyStuff.length()-1) != ']' && uglyDataArr.size() < i){
						
						cleanElement += uglyStuff;
						printJob(cleanElement);
						uglyDataArr.remove(i);
						uglyStuff = uglyDataArr.get(i);
						
					}
				}
				
			}
			
				
		}
		return correctDataArr;
		
		
	}
}
