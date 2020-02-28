package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class RawCardListCreator {

    List<String> linesForDatabase = Collections.emptyList();
    ArrayList<String> parsedCardInfo = new ArrayList<String>();
    int parserLoc = -1; // iterator to determine where to put info.
    String germanfile = "minor powers.txt";

	private void printJob(String args) { System.out.println(args); }

	public RawCardListCreator() {
		// TODO Auto-generated constructor stub
	}
	
	public void accessInfoFromGermans() { // until i can figure out how to read images use data from oberon
		String projLoc = System.getProperty("user.dir") + "\\src\\data\\" + germanfile;

		linesForDatabase = new TextDocReader().readFileInList(projLoc) ;
	    Iterator<String> itr = linesForDatabase.iterator(); 
	    
	    parseLoop(itr);



	    //System.out.println(person.toString());

	}
	
	private void parseLoop(Iterator<String> fileInfo) {
		String myNextLine;
		String tempString = "";
		
		   // 
		lineReading:
		while (fileInfo.hasNext()) {
	    	myNextLine = fileInfo.next();
	    	if(checkForEmptyInput(myNextLine))
	    			continue lineReading;
	    	if(checkForNewCard(myNextLine)) {
	    		parserLoc++;
	    		if(tempString.length() == 0) {

		    		tempString = myNextLine;
	    			continue lineReading;
	    		}
	    		addCard(tempString);
	    		tempString = myNextLine;
	    		continue lineReading;
	    	} else  {
	    		tempString += myNextLine;

	    		continue lineReading;
	    	}
	    }
	}
	
	private void addCard(String lineToAdd) {
		parsedCardInfo.add(lineToAdd);
	}
	private boolean checkForNewCard(String lineToCheck) {
		int c = lineToCheck.indexOf("new PowerCard");
		if(c != -1)
			return true;
		return false;
	}
	
	private boolean checkForEmptyInput(String lineToCheck) {
		if(lineToCheck.length() == 0)
			return true;
		
		
		return false;
	}
	public ArrayList<String> getCardArray() {
		return parsedCardInfo;
	}
}
