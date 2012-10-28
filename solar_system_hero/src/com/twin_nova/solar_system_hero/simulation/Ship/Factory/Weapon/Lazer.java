package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

public class Lazer extends WeaponFire {
	
	public Lazer(Weapon owner) {
		super(owner);
		new LazerFixture(this);
	}
	
	@Override
	public void update() {
		body.setLinearVelocity(get_force(1000f));
		super.update();
	}	
	
	@Override
	public int get_health() {
		return INFINITE_HEALTH;
	}
}
