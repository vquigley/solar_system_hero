package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.TextureCache.Texture;

public class LazerBank extends Weapon {
	private int health = 50;
	private static float part_mass = 0.0f;

	public LazerBank(Ship owner, Vector2 body_offset, float angle_offset) {
		super(owner, Texture.lazer_bank, body_offset, 0);
	}

	@Override
	protected WeaponFire get_weapon_fire() {
		return new Lazer(this);
	}

	@Override
	public int get_health() {
		return health;
	}


	@Override
	public int fire_rate() {
		return 3;
	}

	@Override
	protected float get_mass() {
		return part_mass;
	}

	@Override
	protected int getSpriteBoundHeight() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	protected int getSpriteBoundWidth() {
		// TODO Auto-generated method stub
		return 16;
	}
	
}
