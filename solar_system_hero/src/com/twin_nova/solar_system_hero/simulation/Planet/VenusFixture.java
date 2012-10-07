package com.twin_nova.solar_system_hero.simulation.Planet;

import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.TextureCache.Texture;

public class VenusFixture extends PlanetFixture {

	protected VenusFixture(SpaceBody owner) {
		super(owner, Texture.venus, 487);
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
	
	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return 0.950f;
	}
}
