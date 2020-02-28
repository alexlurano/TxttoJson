package objectToJson;


import com.google.gson.*;

import JsonStuff.StringToDataForCards;
import JsonStuff.SuperSimpleCardData;
import parser.*;

import java.io.FileWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonMaker {
	StringToDataForCards inp = new StringToDataForCards();
	public JsonMaker() {
		// TODO Auto-generated constructor stub
	}
	
	public void makeJson(StringToDataForCards garbageStuff) {
		inp = garbageStuff;

		SuperSimpleCardData ez = new SuperSimpleCardData();
		ez.setCardText(inp.getCardText());
		ez.setCardType(inp.getCardType());
		ez.setCost(inp.getCost());
		ez.setElementsGained(inp.getElementsGained());
		ez.setName(inp.getName());
		ez.setRange(inp.getRange());
		ez.setSource(inp.getSource());
		ez.setSpeed(inp.getSpeed());
		ez.setTargetType(inp.getTargetType());
		





		
		//boolean fuk = new AccessibleObject().canAccess(ez);
		Gson gson = new Gson();

		String jsonInString = gson.toJson(ez);
		System.out.println(jsonInString);
        try {

    		gson.toJson(ez, new FileWriter("src\\data\\staff.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	      


	      
	}
}
