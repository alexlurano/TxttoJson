package parser;


public class StartHere {

	public StartHere() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RawCardListCreator makeCleanInfo = new RawCardListCreator();
		makeCleanInfo.accessInfoFromGermans();
		
		RawCardListToNestedArray makeInfoPretty = new RawCardListToNestedArray();
		makeInfoPretty.createJsonFile(makeCleanInfo.getCardArray());
		
		
		
	}

}
