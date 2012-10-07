package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Venus extends Planet {
	public Venus() {
		super(new Vector2(0f, 0f), 
			  new Vector2(108f, 108f), 
			  0f,
			  224,
			  -243,
			  0.907f);
		
		new VenusFixture(this);
	}
}
