package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.utilities.TextureCache.Texture;

public class PortalFixture extends BodyFixture {

	protected PortalFixture(SpaceBody owner, Texture texture,
			Vector2 body_offset, float angle_offset) {
		super(owner, texture, body_offset, angle_offset);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int get_health() {
		// TODO Auto-generated method stub
		return INFINITE_HEALTH;
	}

	@Override
	public FixtureDef get_fixture_def() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected float get_mass() {
		// TODO Auto-generated method stub
		return 0;
	}

}
