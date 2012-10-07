package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;

public class Mars extends Planet {

	public Mars() {
		super(new Vector2(0f, 0f), 
			  new Vector2(228f, 228f), 
			  0f,
			  687,
			  1.03f,
			  0.377f);
			
			new MarsFixture(this);
	}

}
