package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class ImpulseEngine extends ShipPart {

	protected ImpulseEngine(SpaceBody owner,
							Vector2 body_offset, 
							float angle_offset) {
		super(owner, 
			  Texture.impulse_engine, 
			  body_offset, 
			  angle_offset);
	}

	@Override
	protected int getSpriteBoundHeight() {
		return 48;
	}

	@Override
	protected int getSpriteBoundWidth() {
		return 17;
	}
	
	protected int getSpriteOriginX() {
		// TODO Auto-generated method stub
		return 3;
	}

	protected int getSpriteOriginY() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public int get_health() {
		return 50;
	}

	@Override
	public FixtureDef get_fixture_def() {
		
		Vector2 bla = get_body_offset().cpy();
		//bla.add(-Global.to_meters(getSpriteBoundWidth() * owner.getScaleFactor()) / 2, 
		//		-Global.to_meters(getSpriteBoundHeight() * owner.getScaleFactor()) / 2);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Global.to_meters(getSpriteBoundWidth() * owner.getScaleFactor()) / 2, 
					   Global.to_meters(getSpriteBoundHeight() * owner.getScaleFactor()) / 2,
					   bla,
					   get_angle());
			
		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = 0.0005f;
		fixture_def.friction = 1;
		fixture_def.restitution = 1;
		
		
		return fixture_def;
	}

	@Override
	protected float get_mass() {
		return 1f;
	}
}
