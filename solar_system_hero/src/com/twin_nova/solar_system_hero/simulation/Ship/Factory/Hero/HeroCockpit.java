package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class HeroCockpit extends ShipPart {

	public HeroCockpit(Ship owner, Vector2 body_offset) {
		super(owner, Texture.player, body_offset, 0);
	}

	private static int health = 100;
	private static FixtureDef fixture_def = null;
	private static float part_mass = 2.0f;
	private static final float friction = 0.5f;
	private static final float restitution = 0.2f;

	@Override
	public int get_health() {
		return health;
	}

	@Override
	public FixtureDef get_fixture_def() {
		if (fixture_def == null) {
			CircleShape shape = new CircleShape();
			shape.setRadius(Global.to_meters(sprite.getHeight() / 2));
			
			fixture_def = new FixtureDef();
			fixture_def.shape = shape;
			fixture_def.density = Global.density(part_mass, fixture_def.shape.getRadius());
			fixture_def.friction = friction;
			fixture_def.restitution = restitution;
		}
		
		return fixture_def;
	}

	@Override
	protected float get_mass() {
		return part_mass;
	}
}
