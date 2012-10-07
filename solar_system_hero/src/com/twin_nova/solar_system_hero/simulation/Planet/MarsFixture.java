package com.twin_nova.solar_system_hero.simulation.Planet;

import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.TextureCache.Texture;

public class MarsFixture extends PlanetFixture {

	protected MarsFixture(SpaceBody owner) {
		super(owner, 
			  Texture.mars, 
			  64.2f);
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
		return 0.532f;
	}
}
