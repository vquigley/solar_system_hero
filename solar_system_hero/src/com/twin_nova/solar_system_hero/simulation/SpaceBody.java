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
		current_health = get_health();
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
		return getScalar(body.getLinearVelocity());
	}
	
	public float getScalar(Vector2 vec) {
		// Use Pythagoras' theorem to determine the speed.
		// a� + b� = c�
		float a = vec.x;
		float b = vec.y;
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
	
	public float distanceFrom(SpaceBody other)
	{
		return getScalar(this.body.getPosition().sub(other.body.getPosition()));
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
	

	
	public abstract int get_health();
	protected Integer INFINITE_HEALTH = Integer.MIN_VALUE;
	protected Integer current_health;

	public void startDestroy()
	{
		Space.instance().nuke_list.add(this);
	}
	
	public void apply_damage(int damage) {

		if ((get_health() != INFINITE_HEALTH) && (current_health > 0)) {
			
			current_health -= damage;
			
			if (current_health <= 0)
			{
				startDestroy();
				//Space.instance().nuke_fixture.add(this);
			}
		}
	}
}
