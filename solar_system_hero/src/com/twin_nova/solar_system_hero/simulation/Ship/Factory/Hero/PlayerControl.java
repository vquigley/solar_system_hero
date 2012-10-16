package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.Input;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;

public class PlayerControl extends ShipControl {
	
	public void update(Ship ship)
	{
		handle_turn(ship);
		handle_thrust(ship);
		handle_weapons(ship);
	}
	
	private static void handle_weapons(Ship ship) {
		// Take care of firing system
		if (Game.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			ship.fire_a();
		}
		
		if (Game.isKeyPressed(Input.Keys.ALT_LEFT)) {
			ship.fire_b();
		}
	}

	private void handle_thrust(Ship ship) {
		
		if (Game.isKeyPressed(Input.Keys.W)) {
			ship.forward();
		}		
	}

	private void handle_turn(Ship ship) {
		if (Game.isKeyPressed(Input.Keys.A)) {
			ship.turnLeft();
		}
			
		if (Game.isKeyPressed(Input.Keys.D)) {
			ship.turnRight();
		}
	}
	
}
