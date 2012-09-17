package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class PlanetFixture extends BodyFixture {

	private int spriteBoundsX;
	private int spriteBoundsY;

	protected PlanetFixture(SpaceBody owner, 
							Texture texture,
							float part_mass,
							 int spriteBoundsX,
							 int spriteBoundsY) {
		super(owner, texture, new Vector2(0,0), 0f);
		this.part_mass = part_mass;
		this.spriteBoundsX = spriteBoundsX;
		this.spriteBoundsY = spriteBoundsY;
	}

	@Override
	public int get_health() {
		return INFINITE_HEALTH;
	}

	@Override
	public FixtureDef get_fixture_def() {
		CircleShape shape = new CircleShape();
		shape.setRadius(Global.to_meters(sprite.getHeight() / 2));

		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(part_mass, fixture_def.shape.getRadius());
		fixture_def.friction = 1f;
		fixture_def.restitution = 0f;

		return fixture_def;
	}

	@Override
	protected float get_mass() {
		return part_mass;
	}

	@Override
	public int get_contact_damage() {
		return INSTANT_DEATH;
	}
	
	private float part_mass;

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return spriteBoundsY;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return spriteBoundsX;
	}

}
