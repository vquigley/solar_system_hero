package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.TextureCache.Texture;

public abstract class WeaponFixture extends BodyFixture {

	protected WeaponFixture(SpaceBody owner, Texture texture,
			Vector2 body_offset, float angle_offset) {
		super(owner, texture, body_offset, angle_offset);
	}
}
