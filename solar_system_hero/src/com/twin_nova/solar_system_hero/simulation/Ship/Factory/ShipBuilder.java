package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero.Hero;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero.PlayerControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout.Scout;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout.ScoutAI;

public class ShipBuilder {

// ============ Builder methods ===================
	
	public static Ship build_hero(Vector2 start_coordinates, 
			   					  float start_direction) {
		return build(new Hero(start_coordinates, 
							  start_direction, 
							  new PlayerControl()));
	}
	
	public static Ship build_scout(Vector2 start_coordinates, float start_direction) {
		return build(new Scout(start_coordinates,
							   start_direction,
							   new ScoutAI()));
	}
	
// ============ Private methods ===================
	private static Ship build(Ship ship) {
		return ship;
	}
}
