package com.twin_nova.solar_system_hero.simulation;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.twin_nova.utilities.Global;

public abstract class SpaceBody {
	protected Body body;
	
	protected abstract BodyDef get_body_definition(Vector2 start_coordinates, float start_direction);
	
	protected SpaceBody(Vector2 start_coordinates, float start_direction) {
		BodyDef bd = get_body_definition(start_coordinates, start_direction);
		body = Space.instance().World().createBody(bd);
	}
	
	public float Scale(float input) {
		// TODO Auto-generated method stub
		return input * getScaleFactor();
	}
	
	public void update() {
		Iterator<Fixture> f_it = body.getFixtureList().iterator();
		
		while (f_it.hasNext()) {
			Fixture f = f_it.next();
			((BodyFixture)f.getUserData()).update();
		}
	}
	
	public void render() {
		Iterator<Fixture> f_it = body.getFixtureList().iterator();
		
		while (f_it.hasNext()) {
			Fixture f = f_it.next();
			((BodyFixture)f.getUserData()).render();
		}
	}
	
	public Body get_body() {
		return body;
	}
	
	public void destroy() {
		Space.instance().World().destroyBody(this.body);
	}
	
	public Vector2 get_force(float angular_force) {
		Vector2 force = new Vector2();
		
		// Use trigonometry to determine the x and y from the angular force.
		force.x = (float)Math.cos((double)body.getAngle()) * angular_force;
		force.y = (float)Math.sin((double)body.getAngle()) * angular_force;
		
		return force;
	}
	
	public float get_speed() {
		// Use Pythagoras' theorem to determine the speed.
		// a² + b² = c²
		float a = body.getLinearVelocity().x;
		float b = body.getLinearVelocity().y;
		float c = (float)Math.sqrt((a*a)+(b*b));
		
		return c;
	}
	
	public short get_weapon_mask() {
		return Space.instance().player_weapon_mask;
	}
	
	public short get_weapon_category() {
		return Space.instance().enemy_category;
	}
	
	public float getScaleFactor() {
		return 1f;
	}

	public void destroyFixture(BodyFixture fixture) {
		Iterator<Fixture> f_it = body.getFixtureList().iterator();
		
		while (f_it.hasNext()) {
			Fixture f = f_it.next();
			if (f.getUserData() == fixture)
			{
				f_it.remove();
				break;
			}
			
		}		
	}
}
