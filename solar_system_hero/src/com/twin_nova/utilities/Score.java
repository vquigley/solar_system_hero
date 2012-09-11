package com.twin_nova.utilities;

public class Score {

	private Score instance;
	
	private int value;
	private int multiplier;

	private Score() {
		// Do nothing.
	}
	
	public Score Instance() {
		if (instance == null) {
			instance = new Score();
		}
		
		return instance;		
	}
	
	
	
}
