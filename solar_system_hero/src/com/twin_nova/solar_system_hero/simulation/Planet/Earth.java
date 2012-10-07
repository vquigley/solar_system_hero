package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;

public class Earth extends Planet {

	public Earth() {
		super(new Vector2(0f, 0f), 
			  new Vector2(150f, 150f), 
			  0f,
			  365,
			  1,
			  1);
		
		new EarthFixture(this);
	}
}
