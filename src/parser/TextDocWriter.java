package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextDocWriter {
	ArrayList<String> cardToWrite  = new ArrayList<String>();
	public TextDocWriter() {
		// TODO Auto-generated constructor stub
	}
	
	public void addInfoToWrite(ArrayList<String> args) {
		for(int i = 0;i<args.size();i++) {
			cardToWrite.add(args.get(i));
		}
		cardToWrite.add("\n\n");
	}
	  public void writeListToFile(String fileName) 
	  { 
	  
	    if(cardToWrite.size() == 0)
	    	return;
	    try
	    { 
	    	FileWriter writeObject = new FileWriter(fileName, false); // set to true if we dont want to overwrite
	    	for(int i = 0;i<cardToWrite.size();i++)
	    		writeObject.write(cardToWrite.get(i)+"\n");
	    	writeObject.close();
	    } 
	  
	    catch (IOException e) 
	    { 
	  
	      // do something 
	      e.printStackTrace(); 
	    } 
	  } 

	
	

}
