package com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class ScoutCockpit extends ShipPart {

	private static float part_mass = 2.0f;
	private static final float friction = 0.5f;
	private static final float restitution = 0.2f;
	
	protected ScoutCockpit(SpaceBody owner) {
		super(owner, Texture.enemy_scout, new Vector2(0,0), 0);
		integralPart = true;
	}

	@Override
	public int get_health() {
		return 20;
	}

	@Override
	public FixtureDef get_fixture_def() {
		CircleShape shape = new CircleShape();
		shape.setRadius(Global.to_meters(sprite.getHeight() / 2));
		

		FixtureDef fixture_def = new FixtureDef();
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

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 64;
	}

}
