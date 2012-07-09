package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero.Hero;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero.PlayerControl;

public class ShipBuilder {

// ============ Builder methods ===================
	
	public static Ship build_hero(Vector2 start_coordinates, 
			   					  int start_direction) {
		return build(new Hero(start_coordinates, 
							  start_direction, 
							  new PlayerControl()));
	}
	
// ============ Private methods ===================
	private static Ship build(Ship ship) {
		ship.build_weapons();
		ship.build_cockpit();
		return ship;
	}
}
