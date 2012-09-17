package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.utilities.TextureCache.Texture;

public class PlanetBuilder {

	public static Planet build_earth() {
		return new Planet(Texture.earth, 
						  new Vector2(0f, 0f), 
						  new Vector2(20f, 10f), 
						  0f, 
						  Space.instance().earth_mass,
						  256,
						  256);
	}
}
