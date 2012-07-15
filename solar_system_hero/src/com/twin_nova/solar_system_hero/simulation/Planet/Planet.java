package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class Planet extends OrbitingObject {
	
	private GravityWell well;

	protected Planet(Texture planet_texture,
					 Vector2 ellipse_center,
					 Vector2 ellipse_limits,
					 float ellipse_angle,
					 float mass) {
		super(ellipse_center,
			  ellipse_limits,
			  ellipse_angle);
		
		new PlanetFixture(this, planet_texture, mass);
		well = new GravityWell(this);
	}

	@Override
	protected BodyDef get_body_definition(Vector2 start_coordinates,
										  float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = Global.to_radians(start_direction);
		bd.type = BodyDef.BodyType.StaticBody;
		
		return bd;
	}

	@Override
	public void update() {
		super.update();
		well.update();
	}
}
