package com.ecivil.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Milan
 */
public enum EAccidentType {

	Other("Άτύχημα"), CarAccident("Tροχαίο ατύχημα"), Fire("Πυρκαγιά"), HomeBurglary(
			"Διάρρηξη στο σπίτι"), DogBite("Δάγκωμα σκύλου"), FallDown("Πτώση"), BadMedications(
			"Kακά φάρμακα"), FoodPoisoning("Δηλητηρίαση με φαγητό");

	private final String name;
	private static final Map<String, String> selectMap = makeSelectMap();

	private EAccidentType(String s) {
		name = s;
	}

	private static Map<String, String> makeSelectMap() {
		Map<String, String> typesMap = new HashMap<String, String>();

		
		for (EAccidentType aEnum : EAccidentType.values()) {
			typesMap.put(aEnum.inGreek(), aEnum.inGreek());
		}

		typesMap.remove(defaultInGreek());
		
		return typesMap;
	}
	
	public static String defaultInGreek() {
		return "Άτύχημα";
	}
	
	public static Map<String, String> getSelections() {
		return selectMap;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String inGreek() {
		return name;
	}

	public EAccidentType getAccidentType(String name) {
		for (EAccidentType type : EAccidentType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		// by default
		return EAccidentType.Other;
	}

	@Override
	public String toString() {
		return name;
	}
}
