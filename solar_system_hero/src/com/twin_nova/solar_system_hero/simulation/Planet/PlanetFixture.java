package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public abstract class PlanetFixture extends BodyFixture {


	protected PlanetFixture(SpaceBody owner, 
							Texture texture,
							float part_mass) {
		super(owner, texture, new Vector2(0,0), 0f);
		this.part_mass = part_mass;
	}

	@Override
	public FixtureDef get_fixture_def() {
		CircleShape shape = new CircleShape();
		shape.setRadius(getRadius() * Planet.objectSizeScale);

		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(part_mass, fixture_def.shape.getRadius());
		fixture_def.friction = 1f;
		fixture_def.restitution = 0f;

		return fixture_def;
	}
	
	protected float getSpriteRenderedWidth() {
		return Global.to_pixels(getRadius() * Planet.objectSizeScale * 2);
	}

	protected float getSpriteRenderedHeight() {
		return Global.to_pixels(getRadius() * Planet.objectSizeScale * 2);
	}

	@Override
	public float get_mass() {
		return part_mass;
	}

	@Override
	public int get_contact_damage() {
		return INSTANT_DEATH;
	}
	
	private float part_mass;
}
