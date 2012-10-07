package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Mercury extends Planet {
	public Mercury() {
		super(new Vector2(0f, 0f), 
			  new Vector2(58f, 58f), 
			  0f,
			  88,
			  58,
			  0.378f);
		
		new MercuryFixture(this);
	}
}
