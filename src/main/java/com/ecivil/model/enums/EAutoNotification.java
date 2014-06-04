package com.ecivil.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Milan
 */
public enum EAutoNotification {
    On ("Αυτόματες ειδοποιήσεις ενεργοποιημένες"),
    Off ("Αυτόματες ειδοποιήσεις απενεργοποιημένες");
    
    private final String name;       
    private static final Map<String, String> selectMap = makeSelectMap();

    private EAutoNotification(String s) {
        name = s;
    }

    private static Map<String, String> makeSelectMap() {
		Map<String, String> typesMap = new HashMap<String, String>();

		for (EVerification aEnum : EVerification.values()) {
			typesMap.put(aEnum.inGreek(), aEnum.inGreek());
		}

		return typesMap;
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
