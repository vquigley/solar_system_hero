package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;

public class PlayerControl extends ShipControl {
		
	private static final float Impulse = 3;
	private static final float Torque = 15;

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
		
		//if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			//ship.impulseBackward();
		//}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			//ship.impulseForward();
			ship.get_body().applyForceToCenter((float)Math.cos(ship.get_body().getAngle()) * Impulse,
					   (float)Math.sin(ship.get_body().getAngle()) * Impulse);
		}
		
		if (Math.abs(ship.get_speed()) > ship.top_speed())
		{
			ship.get_body().applyForceToCenter(-ship.get_body().getLinearVelocity().x,
					   -(ship.get_body().getLinearVelocity().y));
		}
		
	//	if ((Math.abs(ship.get_speed()) > ship.top_speed()))
	//	{
	//		Gdx.app.log("ship.top_speed()", String.format("%s", ship.top_speed()));
	//		ship.get_body().setLinearVelocity((float)Math.cos(ship.get_body().getAngle()) * ship.top_speed(),
	//				   						  (float)Math.sin(ship.get_body().getAngle()) * ship.top_speed());
	//	}
		
	}

	private static void handle_turn(Hero ship) {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			//ship.impulseTurnLeft();
			ship.get_body().applyTorque(Torque);
		}
			
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			//ship.impulseTurnRight();

			ship.get_body().applyTorque(-Torque);
		}	
		
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			//ship.impulseLeft();
		}
			
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			//ship.impulseRight();
		}
		
		
		if ((ship.get_body().getAngularVelocity() > 1) || (ship.get_body().getAngularVelocity() < -1))
		{
			
			int dir = (ship.get_body().getAngularVelocity() > 0) ? 1 : -1;
			ship.get_body().setAngularVelocity( dir * 1 *ship.get_body().getAngularVelocity() * 0.05f);
		}
		else
		{
			ship.get_body().setAngularVelocity(0);
		}
		
		if (ship.get_body().getAngularVelocity() > ship.top_speed())
		{
			ship.get_body().setAngularVelocity(ship.top_speed());
		}
	}
	
}
