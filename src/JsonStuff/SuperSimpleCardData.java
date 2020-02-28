package JsonStuff;

public class SuperSimpleCardData {
	private String cardType;
	private String name;
	private int cost;
	private int range = -1; // a range of -1 MEANS RANGE DOES NOT APPLY!!!
	private String speed;
	private String source;
	private String targetType;
	private String elementsGained;
	private String cardText;
	
	public SuperSimpleCardData() {
		
	}
	public SuperSimpleCardData(String cardType,String name,int cost,
			int range,String speed,String source,String targetType, 
			String elementsGained,String cardText ) {
		super();
		this.cardType = cardType;
		this.name = name;
		this.cost = cost;
		this.range = range;
		this.speed = speed;
		this.source = source;
		this.targetType = targetType;
		this.elementsGained = elementsGained;
		this.cardText = cardText;
		// TODO Auto-generated constructor stub
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getElementsGained() {
		return elementsGained;
	}

	public void setElementsGained(String elementsGained) {
		this.elementsGained = elementsGained;
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}
	
	
	@Override
	public String toString()
	{
		return "Card [cardType=" + cardType + ", name=" + name + ", cost=" + cost + ", range="
				 + range + ", speed=" + speed + ", source=" + source + ", targetType=" + targetType +
				 ", elementsGained=" + elementsGained + ", cardText=" + cardText  + "]";
	}
	
	

}
