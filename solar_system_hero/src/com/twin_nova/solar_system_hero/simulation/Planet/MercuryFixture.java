package com.twin_nova.solar_system_hero.simulation.Planet;

import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.TextureCache.Texture;

public class MercuryFixture extends PlanetFixture {

	protected MercuryFixture(SpaceBody owner) {
		super(owner, Texture.mercury, 33);
	}

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 512;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 512;
	}
	
	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return 0.383f;
	}

}
