package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class HeroCockpit extends ShipPart {

	public HeroCockpit(Ship owner, Vector2 body_offset) {
		super(owner, Texture.player, body_offset, 0);
	}

	private static FixtureDef fixture_def = null;
	private static float part_mass = 1.0f;
	private static final float friction = 0.5f;
	private static final float restitution = 0.2f;


	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 364;
	}
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 483;
	}

	protected int getSpriteOriginX() {
		return 5;
	}

	protected int getSpriteOriginY() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	@Override
	public FixtureDef get_fixture_def() {
		int trapWidth = 61;
		if (fixture_def == null) {
			Vector2[] cockpit = new Vector2[4];
			cockpit[0] = new Vector2(0, 0);
			cockpit[1] = new Vector2(getSpriteBoundWidth(), trapWidth);
			cockpit[2] = new Vector2(getSpriteBoundWidth() , getSpriteBoundHeight()- trapWidth);
			cockpit[3] = new Vector2(0, getSpriteBoundHeight());
			
			for (Vector2 v2 : cockpit)
			{
				v2.add(-getSpriteBoundWidth() /2, -getSpriteBoundHeight()/2);
				v2.mul(owner.getScaleFactor());
				v2.mul(1f / Global.pixels_per_metre);
			}						
					
			PolygonShape shape = new com.badlogic.gdx.physics.box2d.PolygonShape();
			shape.set(cockpit);
			
			fixture_def = new FixtureDef();
			fixture_def.shape = shape;
			fixture_def.density = 0.05f;
			fixture_def.friction = friction;
			fixture_def.restitution = restitution;
		}
		
		return fixture_def;
	}

	@Override
	public float get_mass() {
		return part_mass;
	}
}
