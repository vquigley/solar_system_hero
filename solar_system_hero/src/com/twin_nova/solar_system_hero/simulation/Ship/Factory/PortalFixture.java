package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;

public class PortalFixture extends BodyFixture {

	ParticleEffect particleEffect = new ParticleEffect();
	
	protected PortalFixture(SpaceBody owner) {
		super(owner, null, new Vector2(0,0), 0);
		
		
		particleEffect.load(Gdx.files.internal("particle/portal"), 
    						Gdx.files.internal("particle/images"));
		
		for (ParticleEmitter em : particleEffect.getEmitters())
		{
			//em.getScale().setHighMax(em.getScale().getHighMax() * owner.getScaleFactor());
			//em.getScale().setHighMin(em.getScale().getHighMin() * owner.getScaleFactor());
			//em.getScale().setLowMax(em.getScale().getLowMax() * owner.getScaleFactor());
			//em.getScale().setLowMin(em.getScale().getLowMin() * owner.getScaleFactor());
			em.setMinParticleCount((int) (em.getMinParticleCount() * owner.getScaleFactor()));
			em.setMaxParticleCount((int) (em.getMaxParticleCount() * owner.getScaleFactor()));
			
			float[] d = em.getScale().getScaling().clone();
			
			for (int i = 0; i < d.length; ++i)
			{
			//	d[i] = d[i] * owner.getScaleFactor();
			}
			
			em.getScale().setScaling(d);
		}
		
		particleEffect.start();
	}

	@Override
	public int get_health() {
		return INFINITE_HEALTH;
	}

	@Override
	public FixtureDef get_fixture_def() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Global.to_meters(getSpriteRenderedWidth()) / 2, 
					   Global.to_meters(getSpriteRenderedHeight()) / 2);
		
		
		FixtureDef fixture_def = new FixtureDef();
		fixture_def.shape = shape;
		fixture_def.density = Global.density(1, fixture_def.shape.getRadius());
		fixture_def.friction = 1;
		fixture_def.restitution = 1;
		
		return fixture_def;
	}

	@Override
	public float get_mass() {
		return 1;
	}
	
	public void render() {
		particleEffect.setPosition(Global.to_pixels(getOwner().get_body().getWorldCenter().x), 
				   				   Global.to_pixels(getOwner().get_body().getWorldCenter().y));
		particleEffect.draw(Game.batch, Game.delta);
	}

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 10;
	}
}
