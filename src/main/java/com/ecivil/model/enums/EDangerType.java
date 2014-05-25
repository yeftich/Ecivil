package com.ecivil.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Milan
 */
public enum EDangerType {
    Other ("Κίνδυνος"),
    TreeOnRoad ("Δέντρο στο δρόμο"),
    Fire ("Φωτιά"),
    HomeBurglary ("Κρημνός"),
    DogBite ("Αποκλεισμένος δρόμος");

    private final String name;
    private static final Map<String, String> selectMap = makeSelectMap();

    private EDangerType(String s) {
        name = s;
    }

	private static Map<String, String> makeSelectMap() {
		Map<String, String> typesMap = new HashMap<String, String>();

		for (EDangerType aEnum : EDangerType.values()) {
			typesMap.put(aEnum.inGreek(), aEnum.inGreek());
		}

		typesMap.remove(defaultInGreek());
		
		return typesMap;
	}
	
	public static String defaultInGreek() {
		return "Κίνδυνος";
	}
	
	public static Map<String, String> getSelections() {
		return selectMap;
	}
	
    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String inGreek(){
        return name;
    }

@Override
    public String toString(){
       return name;
    }    
}
