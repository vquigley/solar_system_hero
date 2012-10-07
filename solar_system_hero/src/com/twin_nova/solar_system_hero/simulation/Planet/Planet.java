package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class Planet extends OrbitingObject {
	
	private GravityWell well;
	private float rotationTime;
	private long start_time;
	private static float orbitScale = 1.4f;
	public static float objectSizeScale = 11f;

	protected Planet(Vector2 ellipse_center,
					 Vector2 ellipse_limits,
					 float ellipse_angle,
					 int earthDaysForOrbit,
					 float rotationPeriod,
					 float massPeriod) {
		super(ellipse_center.mul(orbitScale),
			  ellipse_limits.mul(orbitScale),
			  ellipse_angle,
			  Space.instance().earth_year * ((float)earthDaysForOrbit / 365));
		
		well = new GravityWell(this, massPeriod);
		
		Space.instance();
		this.rotationTime = Space.EarthRotationTime * rotationPeriod;
		this.start_time = Space.instance().get_space_time();
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
		
		rotate();
	}

	private void rotate() {
		long game_time = (Space.instance().get_space_time() - start_time);
		
		if (game_time != 0)
		{
			long time_of_current_revolution = game_time % (long)(rotationTime * 1000);
			
			int next_position = (int) (360 * (time_of_current_revolution / (rotationTime * 1000)));
			
			body.setTransform(body.getPosition(), Global.to_radians(next_position));
		}
		else
		{
			body.setTransform(body.getPosition(), 0);
		}
		
	}
}
