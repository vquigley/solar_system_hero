package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.Planet.GravityWell;
import com.twin_nova.utilities.Global;

public class Star extends SpaceBody{

	protected Star() {
		super(new Vector2(0,0), 0f);
		new StarFixture(this);
		
		well = new GravityWell(this, 2, 20);
	}
	
	GravityWell well;

	public void update()
	{
		well.update();
		super.update();
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
	
	public float getScaleFactor()
	{
		return 1;
	}

	@Override
	public int get_health() {
		// TODO Auto-generated method stub
		return INFINITE_HEALTH;
	}
}
