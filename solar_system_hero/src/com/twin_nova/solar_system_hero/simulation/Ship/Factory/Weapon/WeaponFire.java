package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;

public abstract class WeaponFire extends SpaceBody {
	protected WeaponFire(Weapon owner) {
		super(owner.get_world_position().add(2, 2), owner.get_angle());
	}

	@Override
	protected BodyDef get_body_definition(Vector2 start_coordinates, float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = (start_direction);
		bd.type = BodyDef.BodyType.DynamicBody;
		
		return bd;
	}
}
