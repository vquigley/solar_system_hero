package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class LazerFixture extends BodyFixture {

	protected LazerFixture(SpaceBody owner) {
		super(owner, Texture.blue_lazer, new Vector2(0,0), 0);
	}

	@Override
	public int get_health() {
		return INFINITE_HEALTH;
	}

	@Override
	public FixtureDef get_fixture_def() {
		CircleShape shape = new CircleShape();
		shape.setRadius(Global.to_meters(sprite.getHeight() / 2));
		
		fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(part_mass, fixture_def.shape.getRadius());
		fixture_def.friction = friction;
		fixture_def.restitution = restitution;
		
		return fixture_def;
	}

	@Override
	protected float get_mass() {
		return part_mass;
	}
	
	private static FixtureDef fixture_def = null;
	private static final float friction = 0.5f;
	private static final float restitution = 0.2f;
	private static float part_mass = 0.0f;
	
	@Override
	public int get_contact_damage() {
		return 10;
	}
}