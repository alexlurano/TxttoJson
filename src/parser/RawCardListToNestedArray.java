package parser;

import java.io.IOException;
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
	

	private void printJob(String args) { System.out.println(args); }
	private void printArray(ArrayList<String> args) {
		args.forEach((thingy) -> System.out.println(thingy + ""));
	}
	private void printFile(ArrayList<String> args, boolean printNow) {
		String projLoc = System.getProperty("user.dir") + "\\src\\data\\" + "outputTest.txt";
		w.addInfoToWrite(args);
		if(printNow)
			w.writeListToFile(projLoc);
	}
	public RawCardListToNestedArray() {
		// TODO Auto-generated constructor stub
	}
	
	public void createJsonFile(ArrayList<String> rawCardInfo) {
		rawCards = rawCardInfo;
		int index = rawCards.size();
		ArrayList<String> emptyTemp = new ArrayList<String>();
		emptyTemp.add("nonsense");
		index = 2;
		for(int i=0;i<index;i++) {

			cleanCardInfo.add(new StringToDataForCards());
			cleanCardInfo.get(i).assignValues(beautify(rawCards.get(i),i));
		}

		printFile(emptyTemp,true);
	}
	
	
	
	
	
	
	
	
	
	//------------------------------------------------------------------------------
	private ArrayList<String> beautify(String rawInfo,int cardNum) {

		ArrayList<String> dicedCardInfo = new ArrayList<String>();
		ArrayList<String> completeCardInfo = new ArrayList<String>();
		String s = rawInfo;
		s = deleteCppRelics(s);
		dicedCardInfo = parseOutCommas(s);
		completeCardInfo = combineInfoInBrackets(dicedCardInfo,'[',']');
		completeCardInfo = combineInfoInBrackets(completeCardInfo,'"','"');
		completeCardInfo = removeQuotes(completeCardInfo);
		completeCardInfo.add(0, s);

		completeCardInfo.add(completeCardInfo.size()+"long");
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
		ArrayList<Integer> removeSpots = new ArrayList<Integer>();
		ArrayList<String> cleanDataArr = new ArrayList<String>();
		uglyDataArr = args;
		cleanDataArr = args;
		
		for(int i=0;i<uglyDataArr.size();i++) {
			String uglyStuff;
			String cleanElement;
			uglyStuff = uglyDataArr.get(i);

			if((uglyStuff.charAt(0) == leftBracket) && (uglyStuff.charAt(uglyStuff.length()-1) != rightBracket)) {
				cleanElement = uglyStuff;
//				uglyDataArr.remove(i);
				removeSpots.add(i);
				i++;
				uglyStuff = uglyDataArr.get(i);

				boolean loopCheck = true;
				while((loopCheck) && (uglyDataArr.size() > i)){
						
					cleanElement += uglyStuff;
					if((uglyStuff.charAt(uglyStuff.length()-1)) == rightBracket)
						loopCheck = false;

//					uglyDataArr.remove(i);
					removeSpots.add(i);
					i++;
					if(uglyDataArr.size() > i)
						uglyStuff = uglyDataArr.get(i);
					else {
						i--;
						printJob(uglyDataArr.get(i));
					}
						
				}
				int shrinkCounter = 0; // hack to avoid for looping all removespots to correspond to thenew size of cleanDataArr
				for(int a=0;a<removeSpots.size();a++) {

					int index = removeSpots.get(a) + shrinkCounter;
					printJob(cleanDataArr.get(index)+"removingj");
					cleanDataArr.remove(index);
					shrinkCounter--;
					

				}

				cleanDataArr.add(removeSpots.get(0), cleanElement);
				removeSpots.clear();
			}
			
			
				
		}
		return cleanDataArr;
		
		
	}
}
