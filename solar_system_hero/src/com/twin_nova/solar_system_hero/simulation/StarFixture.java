package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.simulation.Planet.Planet;
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
	public float getRadius() {
		// TODO Auto-generated method stub
		return 109.25f;
	}
	
	protected float getSpriteRenderedWidth() {
		return Global.to_pixels(getRadius() * 2) * sunScaleSize ;
	}

	protected float getSpriteRenderedHeight() {
		return Global.to_pixels(getRadius() * 2) * sunScaleSize ;
	}
	
	public static float sunScaleSize = 0.35f;

	@Override
	public FixtureDef get_fixture_def() {
		CircleShape shape = new CircleShape();
		shape.setRadius(getRadius() * sunScaleSize);

		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(1f, fixture_def.shape.getRadius());
		fixture_def.friction = 1f;
		fixture_def.restitution = 0f;

		return fixture_def;
	}

	@Override
	public float get_mass() {
		return 1;
	}
	
	public void update() {
		super.update();
	}

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 256;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 256;
	}
}
