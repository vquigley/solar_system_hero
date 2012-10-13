package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class LazerFixture extends WeaponFixture {

	protected LazerFixture(SpaceBody owner) {
		super(owner, Texture.blue_lazer, new Vector2(0,0), 0);
	}

	@Override
	public int get_health() {
		return INFINITE_HEALTH;
	}

	@Override
	public FixtureDef get_fixture_def() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Global.to_meters(getSpriteRenderedWidth()), 
					   Global.to_meters(getSpriteRenderedHeight()));
		
		
		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(part_mass, fixture_def.shape.getRadius());
		fixture_def.friction = friction;
		fixture_def.restitution = restitution;
		
		return fixture_def;
	}

	@Override
	public float get_mass() {
		return part_mass;
	}
	
	private static final float friction = 0.0f;
	private static final float restitution = 0.0f;
	private static float part_mass = 0f;
	
	@Override
	public int get_contact_damage() {
		return 10;
	}
	
	public boolean colides_with(BodyFixture bodyFixture) {
		// Do not let a lazer collide with it's lazer bank.
		Ship firing_ship = ((WeaponFire)getOwner()).get_firing_ship();
		SpaceBody incoming_object = bodyFixture.getOwner();
		return (firing_ship != incoming_object);		
	}
	
	public void register_contact_begin(BodyFixture fixture) {
		((WeaponFire)getOwner()).nuke();
	}

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 64;
	}
}