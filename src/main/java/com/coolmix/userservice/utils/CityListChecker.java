package com.coolmix.userservice.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

public class CityListChecker {
	private CityListChecker() {}
	private static volatile CityListChecker INSTANCE;
    private Map<String, String> connectedCities = new HashMap<>();
    private Set<String> allCities = new HashSet<>();
    private Set<String> allCitiesCopy = new HashSet<>();
	private List<String> directlyConnected;
	private String origFrom;
	private String origTo;


	static {
		if (INSTANCE == null) {
			synchronized(CityListChecker.class) { 
				if (INSTANCE == null) {
					INSTANCE = new CityListChecker();
				}
			}
		}
	}
	public static synchronized CityListChecker getInstance() {
		return INSTANCE;
	}
	
	public void loadFile(String loadfile) {
		
		File source = new File(loadfile);
	      
		try (
				BufferedReader in = new BufferedReader (new FileReader(source));
		) 
		{
			String s;
			while ((s = in.readLine()) != null) {
				String[] fromTo =s.split(",");
				if (fromTo.length ==2 && !StringUtils.isEmpty(fromTo[0]) && !StringUtils.isEmpty(fromTo[1])) {
					String f = fromTo[0].trim().toUpperCase();
					String t = fromTo[1].trim().toUpperCase();
					connectedCities.put(f, t);
					allCities.add(f);
					allCities.add(t);
				}
			}
			//connectedCities.forEach((f,t)->System.out.println(f+t));
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public boolean checkIfConnected(String rawFrom, String rawTo) {
		origFrom = rawFrom.trim().toUpperCase();
		origTo = rawTo.trim().toUpperCase();
		
		if (origFrom.equals(origTo)) {
			return true;
		}
		
		if (connectedCities.containsKey(origFrom)) {
			if (connectedCities.get(origFrom).equals(origTo)) {
				return true;
			} else {
				return checkFurther(origFrom,origTo);
			}
		} else {			
			if (connectedCities.containsKey(origTo)) {
				if (connectedCities.get(origTo).equals(origFrom)) {
					return true;
				} else {
					return checkFurther(origFrom,origTo);
				}

			} else {
				return checkFurther(origFrom,origTo);
			}
		}
	}
	
	private boolean checkFurther(String from, String to) {
		directlyConnected = new ArrayList<>();
		allCitiesCopy = new HashSet<>();
	    allCitiesCopy.addAll(allCities);
	    checkEvenFurther(allCities,from,to);
		//System.out.println("final list: " + directlyConnected.toString());

		if (directlyConnected.contains(origTo)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void checkEvenFurther(Set<String> allCities, String from, String to) {
		//System.out.println("from:" + from + " to:" + to);
		//System.out.println("cities:" + allCities);
		//System.out.println("loop starts:");
		for (String s: allCities) {
			//System.out.println("from:" + from + " to:" + s);
			if (s!=from) {
				boolean connected = checkIfDirectlyConnected(from,s);
				//System.out.println(" :" + connected);
				if (connected) {
					directlyConnected.add(s);
					allCitiesCopy = new HashSet<>();
					allCitiesCopy.addAll(allCities);
					allCitiesCopy.remove(from);
					if (!s.equals(origTo)) {
						checkEvenFurther(allCitiesCopy, s,to);
					}

				} else {
				}
			}
		}
	}
	
	private boolean checkIfDirectlyConnected(String from, String to) {
		
		if (connectedCities.containsKey(from)) {
			if (connectedCities.get(from).equals(to)) {
				return true;
			} else {
				if (connectedCities.containsKey(to)) {
					if (connectedCities.get(to).equals(from)) {
						return true;
					} else {
						return false;
					}

				} else {
					return false;
				}
			}
		} else {			
			if (connectedCities.containsKey(to)) {
				if (connectedCities.get(to).equals(from)) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		}
	}
	

}