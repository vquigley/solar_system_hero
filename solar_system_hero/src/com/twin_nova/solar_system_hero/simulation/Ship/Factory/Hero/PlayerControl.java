package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.IShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;

public class PlayerControl implements IShipControl {
	
	private static int Torque = 5;
	private static int Impulse = 5;
	
	public void update(Ship ship)
	{
		handle_turn(ship);
		handle_thrust(ship);
		handle_weapons(ship);
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

	private static void handle_thrust(Ship ship) {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			ship.get_body().applyForceToCenter((float)Math.cos(ship.get_body().getAngle()) * Impulse,
											   (float)Math.sin(ship.get_body().getAngle()) * Impulse);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			ship.get_body().applyForceToCenter(- (float)Math.cos(ship.get_body().getAngle())  * Impulse, 
											   - (float)Math.sin(ship.get_body().getAngle()) * Impulse);
		}
		
		if (Math.abs(ship.get_body().getLinearVelocity().x) > ship.top_speed())
		{
			int factor = (ship.get_body().getLinearVelocity().x > 0) ? 1 : -1;
			ship.get_body().setLinearVelocity(new Vector2 (factor * ship.top_speed(), ship.get_body().getLinearVelocity().y));
		}
		
		if (Math.abs(ship.get_body().getLinearVelocity().y) > ship.top_speed())
		{
			int factor = (ship.get_body().getLinearVelocity().y > 0) ? 1 : -1;
			ship.get_body().setLinearVelocity(new Vector2 (ship.get_body().getLinearVelocity().x, factor * ship.top_speed()));
		}
	}

	private static void handle_turn(Ship ship) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			ship.get_body().applyTorque(Torque);
		}
			
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			ship.get_body().applyTorque(-Torque);
		}		

		ship.get_body().setAngularVelocity(0);
	}
	
}
