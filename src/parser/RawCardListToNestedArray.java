package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 *  Method Call order:
 *  createJsonFIle : outside call
 *  beautify
 *  	deletecpprelics
 *  	parseoutcommas
 *  	combineinfoinbrackets
 * 
 * 
 */
public class RawCardListToNestedArray {
	ArrayList<String> rawCards = new ArrayList<String>();
	ArrayList<StringToDataForCards> cleanCardInfo = new ArrayList<StringToDataForCards>();

	TextDocWriter w = new TextDocWriter();
	String fileName = "";
	public RawCardListToNestedArray(String args) {
		fileName = args;
	}
	private void printJob(String args) { System.out.println(args); }
	private void printArray(ArrayList<String> args) {
		args.forEach((thingy) -> System.out.println(thingy));
	}
	private void printFile(ArrayList<String> args, boolean printNow) {
		String projLoc = System.getProperty("user.dir") + "\\src\\data\\" + fileName;
		w.addInfoToWrite(args);
		if(printNow)
			w.writeListToFile(projLoc);
	}

	
	public void createJsonFile(ArrayList<String> rawCardInfo) {
		rawCards = rawCardInfo;
		int index = rawCards.size();
		ArrayList<String> emptyTemp = new ArrayList<String>();
		ArrayList<String> majorDebugger = new ArrayList<String>();
		emptyTemp.add("nonsense");
		
			for(int i=0;i<index;i++) {
				majorDebugger = beautify(rawCards.get(i),i);
				cleanCardInfo.add(new StringToDataForCards());

				if(fileName.equalsIgnoreCase("cleanminor.txt"))
					cleanCardInfo.get(i).assignValues(majorDebugger);
				majorDebugger.clear();
			}

		printFile(emptyTemp,true);
	}
	
	
	
	
	
	
	
	
	
	//------------------------------------------------------------------------------
	private ArrayList<String> beautify(String rawInfo,int cardNum) {
		
		int lengthofArr = -1;
		ArrayList<String> dicedCardInfo = new ArrayList<String>();
		ArrayList<String> completeCardInfo = new ArrayList<String>();
		String s = rawInfo;
		printJob(s);
		s = deleteCppRelics(s);
		printJob(s);
		dicedCardInfo = parseOutCommas(s);
		completeCardInfo = combineInfoInBrackets(dicedCardInfo,'[',']');
		completeCardInfo = combineInfoInBrackets(completeCardInfo,'"','"');
		completeCardInfo = removeQuotes(completeCardInfo);
		lengthofArr = completeCardInfo.size();
		if(completeCardInfo.size() > 0)
			completeCardInfo.remove(lengthofArr-2); // removes the name of the artist, which is in teh 2nd to last spot;

		printFile(completeCardInfo,false);
		return completeCardInfo;
		// send our parsed array of strings to be converted to primitive types.
		
		



	}
	
	private ArrayList<String> removeQuotes(ArrayList<String> args) {
		ArrayList<String> bargs = new ArrayList<String>();
		bargs = args;
		for(int i = 0;i<bargs.size();i++) {
			bargs.set(i, args.get(i).replaceAll("\"", ""));
		}
			
		return bargs;
	}
	private String deleteCppRelics(String uglyDuckling) {
		String gorgeousSwan = uglyDuckling;
        gorgeousSwan = gorgeousSwan.replaceAll("[a-zA-Z]*?\\(", ""); // delete cpp class calls
        gorgeousSwan = gorgeousSwan.replaceAll("\\s{2,}", ""); // deletes excessive whitespace
        gorgeousSwan = gorgeousSwan.replaceAll("ProductSet..+?(,.)", "");// deletes ProductSet.whatever and the comma
        gorgeousSwan = gorgeousSwan.replaceAll("PowerDeckType.", ""); //leaves either just minor,major,or spirit
        

        gorgeousSwan = gorgeousSwan.replaceAll("-", "");
        gorgeousSwan = gorgeousSwan.replaceAll("\\.", " ");
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
				myFoundString = myFoundString.strip();
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
	
	private ArrayList<String> combineInfoInBrackets(ArrayList<String> args,char leftBracket,char rightBracket) {
		ArrayList<String> uglyDataArr = new ArrayList<String>();
		uglyDataArr = args;
		
		for(int i=0;i<uglyDataArr.size();i++) {
			String uglyStuff;
			String cleanElement;
			uglyStuff = uglyDataArr.get(i);
			int firstRemovalSpot = -1;
			if((uglyStuff.charAt(0) == leftBracket) && (uglyStuff.charAt(uglyStuff.length()-1) != rightBracket)) {
				cleanElement = uglyStuff;
				firstRemovalSpot = i;
				uglyDataArr.remove(i);
				uglyStuff = uglyDataArr.get(i);

				boolean loopCheck = true;
				while((loopCheck) && (uglyDataArr.size() > i)){
						
					cleanElement += uglyStuff;
					if((uglyStuff.charAt(uglyStuff.length()-1)) == rightBracket)
						loopCheck = false;

					uglyDataArr.remove(i);
					if(uglyDataArr.size() > i)
						uglyStuff = uglyDataArr.get(i);
					else {
						i--;
					}
						
				}
				if(firstRemovalSpot > 0)
					uglyDataArr.add(firstRemovalSpot, cleanElement);
			}
			
			
				
		}
		return uglyDataArr;
		
		
	}
}
