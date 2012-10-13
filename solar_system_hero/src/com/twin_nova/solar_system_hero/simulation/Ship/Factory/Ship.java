package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.Weapon;
import com.twin_nova.utilities.Console;
import com.twin_nova.utilities.Global;

public abstract class Ship extends SpaceBody {

	private ShipControl ship_control = null;
	
	public ShipControl control() {return ship_control;}
	
	protected Ship(Vector2 start_coordinates, 
				   float start_direction,
				   ShipControl control) {
		super(start_coordinates, start_direction);		
		ship_control = control;
	}
	
	public void update() {
		super.update();
		ship_control.update(this);
		
		Iterator<Fixture> f_it = body.getFixtureList().iterator();
		
		while (f_it.hasNext()) {
			Fixture f = f_it.next();
			((BodyFixture)f.getUserData()).update();
		}
		
		maintainTopSpeed();
		maintainTorque();
	}
	
	protected BodyDef get_body_definition(Vector2 start_coordinates, 
			   						      float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = Global.to_radians(start_direction);
		bd.type = BodyDef.BodyType.DynamicBody;
		
		return bd;
	}
	
	public void force_turn(Global.Dir dir)
	{
		float dir_r = Global.to_radians(dir.angle());
		body.setLinearVelocity(new Vector2 (0,0));
		body.setAngularVelocity(0);
		
		if (Math.abs(body.getAngle() - dir_r) > 1) {
			float torque = 5 * (((body.getAngle() < dir.angle())) ? 1 : -1);
			body.applyTorque(torque);
			Console.write_line("Apply Torque", torque);
		}
		else {
			float x_force = (float)Math.cos(body.getAngle()) * 50;
			float y_force = (float)Math.sin(body.getAngle()) * 50;
			body.applyForceToCenter(x_force, y_force);
		}
		
		update();
	} 
	
	public void forward()
	{
		//if (Math.abs(get_speed()) < top_speed())
		{
			get_body().applyLinearImpulse(new Vector2((float)Math.cos(get_body().getAngle()) * forwardImpulse(),
				   					  					(float)Math.sin(get_body().getAngle()) * forwardImpulse()), get_body().getWorldCenter());	
		}
	}
	
	public void turnLeft()
	{
		// stop the turning if were close to zero and turning in the opposite direction.
		if ((get_body().getAngularVelocity() >= 0) || (get_body().getAngularVelocity() < -1))
		{
			get_body().applyAngularImpulse(torque());
		}
		else
		{
			get_body().setAngularVelocity(0);
		}
	}
	
	public void turnRight()
	{
		// stop the turning if were close to zero and turning in the opposite direction.
		if ((get_body().getAngularVelocity() <= 0) || (get_body().getAngularVelocity() > 1))
		{
			get_body().applyAngularImpulse(-torque());
		}
		else
		{
			get_body().setAngularVelocity(0);
		}
		Gdx.app.log("Math.abs(get_body().getAngularVelocity())", 
				String.format("%s", get_body().getAngularVelocity()));
	}
	
	public void maintainTopSpeed()
	{
		if ((Math.abs(get_speed()) > top_speed()) && (get_speed() > 0))
		{
			
		}
	}
	
	public void maintainTorque()
	{		
		float vel = get_body().getAngularVelocity();
		
		if (Math.abs(vel) >top_torque())
		{
			int dir = (vel > 0) ? 1 : -1;
			get_body().setAngularVelocity(top_torque() * dir);
		}
	}
	
	public abstract float forwardImpulse();
	public abstract float torque();
	
	public abstract ShipPart build_cockpit();
	public abstract ShipPart build_engine();
	public abstract ArrayList<Weapon> build_weapons();
	
	public abstract float top_speed();
	public abstract float top_torque();
	
	public abstract void fire_a();
	public abstract void fire_b();

	public int getKillValue() {
		return 10;
	}

	public void slowDown() {
		if (Math.abs(get_speed()) > 0)
		{
			get_body().applyForceToCenter((float)Math.cos(get_body().getAngle()) * -forwardImpulse(),
						  (float)Math.sin(get_body().getAngle()) * -forwardImpulse());	
		}
	}
}
