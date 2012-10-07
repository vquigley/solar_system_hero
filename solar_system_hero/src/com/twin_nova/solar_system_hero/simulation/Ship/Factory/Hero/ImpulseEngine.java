package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class ImpulseEngine extends ShipPart {

	ArrayList<ParticleEffect> particleEffects = new ArrayList<ParticleEffect>();
	
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
		//bla.add(Global.to_meters(getSpriteBoundWidth() * owner.getScaleFactor()) / 2, 
		//		Global.to_meters(getSpriteBoundHeight() * owner.getScaleFactor()) / 2);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Global.to_meters(getSpriteBoundWidth() * owner.getScaleFactor()) / 2f, 
					   Global.to_meters(getSpriteBoundHeight() * owner.getScaleFactor()) / 2f,
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

	private float Impulse = 0.2f;
	
	
	public void render()
	{
		super.render();
		
		Iterator<ParticleEffect> it = particleEffects.iterator();
		
		while (it.hasNext())
		{
			ParticleEffect pf = it.next();
			pf.draw(Game.batch, Game.delta);
			
			if (pf.isComplete() != false) {
				it.remove();
			}
		}
	}
	
	public void fire() {
		ParticleEffect particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("particle/smoke_trail3"), 
    						Gdx.files.internal("particle/images"));

		particleEffect.setPosition(Global.to_pixels(get_world_center().x), 
				   				   Global.to_pixels(get_world_center().y));
		
		Array<ParticleEmitter> ems = particleEffect.getEmitters();
		for (ParticleEmitter em : ems)
		{
			float angleOffset = sprite.getRotation();
			
			em.getAngle().setLowMin(em.getAngle().getLowMin() + angleOffset);
			em.getAngle().setLowMax(em.getAngle().getLowMax() + angleOffset);
			em.getAngle().setHigh(em.getAngle().getHighMin() + angleOffset);
			em.getAngle().setHighMax(em.getAngle().getHighMax() + angleOffset);
		}
		
		particleEffect.start();
		
		particleEffects.add(particleEffect);
		Vector2 force = new Vector2((float)Math.cos(get_angle()),
									(float)Math.sin(get_angle()));
		
		Vector2 localPosition = get_world_center();
		localPosition.add(Global.to_meters(getSpriteBoundWidth() * owner.getScaleFactor()) / 2, 
						  Global.to_meters(getSpriteBoundHeight() * owner.getScaleFactor()) / 2);
		
		owner.get_body().applyForce(force.mul(Impulse), localPosition);
	}
}
