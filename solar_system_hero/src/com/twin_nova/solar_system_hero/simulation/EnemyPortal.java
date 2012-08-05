package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.utilities.Global;

public class EnemyPortal extends SpaceBody {

	protected EnemyPortal(Vector2 start_coordinates, float start_direction) {
		super(start_coordinates, start_direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected BodyDef get_body_definition(Vector2 start_coordinates,
										  float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = Global.to_radians(start_direction);
		bd.type = BodyDef.BodyType.DynamicBody;
		
		return bd;
	}
	
}
