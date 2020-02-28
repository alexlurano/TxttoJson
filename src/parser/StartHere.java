package parser;


public class StartHere {

	public StartHere() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RawCardListCreator makeCleanInfo = new RawCardListCreator("minor powers.txt");
		makeCleanInfo.accessInfoFromGermans();
		
		RawCardListToNestedArray makeInfoPretty = new RawCardListToNestedArray("cleanminor.txt");
		makeInfoPretty.createJsonFile(makeCleanInfo.getCardArray());
		
		RawCardListCreator makeCleanInfo2 = new RawCardListCreator("major powers.txt");
		makeCleanInfo2.accessInfoFromGermans();
		
		RawCardListToNestedArray makeInfoPretty2 = new RawCardListToNestedArray("cleanMajor.txt");
		makeInfoPretty2.createJsonFile(makeCleanInfo2.getCardArray());
		
		
	}

}
