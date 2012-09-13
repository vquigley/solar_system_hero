package com.twin_nova.utilities;

public class Score {

	private static final int INITIAL_SCORE =  0;
	private static final int DEFAULT_MULTIPLIER = 1;

	private static Score instance;
	
	private int value = INITIAL_SCORE;
	private int multiplier = DEFAULT_MULTIPLIER;

	private Score() {
		// Do nothing.
	}
	
	public static Score Instance() {
		if (instance == null) {
			instance = new Score();
		}
		
		return instance;		
	}
	
	public int getValue() {return value;}
	
	public void increaseScore(int delta) {
		this.value += (delta * multiplier);
	}
	
	public void increaseMultiplier(int delta) {
		multiplier += delta;
	}
	
	public void resetMultiplier() {
		multiplier = DEFAULT_MULTIPLIER;
	}
}
