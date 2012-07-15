package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class StarFixture extends BodyFixture {

	protected StarFixture(SpaceBody owner) {
		super(owner, 
			  Texture.sun,
			  new Vector2(0,0), 
			  0f);
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
		fixture_def.density = Global.density(1f, fixture_def.shape.getRadius());
		fixture_def.friction = 1f;
		fixture_def.restitution = 0f;

		return fixture_def;
	}

	@Override
	protected float get_mass() {
		return 1;
	}
	
	public void update() {
		super.update();
	}
}
