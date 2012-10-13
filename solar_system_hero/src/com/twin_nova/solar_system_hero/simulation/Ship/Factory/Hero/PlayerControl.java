package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;

public class PlayerControl extends ShipControl {

	public void update(Ship ship)
	{
		handle_turn((Hero)ship);
		handle_thrust((Hero)ship);
		handle_weapons((Hero)ship);
	}
	
	private static void handle_weapons(Ship ship) {
		// Take care of firing system
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			ship.fire_a();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
			ship.fire_b();
		}
	}

	private static void handle_thrust(Hero ship) {
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			ship.forward();
		}		
	}

	private static void handle_turn(Hero ship) {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			ship.turnLeft();
		}
			
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			ship.turnRight();
		}
	}
	
}
