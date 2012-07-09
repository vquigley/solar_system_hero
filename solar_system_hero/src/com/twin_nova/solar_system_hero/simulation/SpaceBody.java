package com.twin_nova.solar_system_hero.simulation;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class SpaceBody {
	protected Body body;
	
	protected abstract BodyDef get_body_definition(Vector2 start_coordinates, float start_direction);
	public abstract void update();
	
	protected SpaceBody(Vector2 start_coordinates, float start_direction) {
		BodyDef bd = get_body_definition(start_coordinates, start_direction);
		body = Space.World().createBody(bd);
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
		Space.World().destroyBody(this.body);
	}
	
	public float get_speed() {
		// Use Pythagoras' theorem to determine the speed.
		// a² + b² = c²
		float a = body.getLinearVelocity().x;
		float b = body.getLinearVelocity().y;
		float c = (float)Math.sqrt((a*a)+(b*b));
		
		return c;
	}
}
