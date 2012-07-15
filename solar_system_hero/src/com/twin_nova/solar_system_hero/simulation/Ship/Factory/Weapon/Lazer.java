package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

public class Lazer extends WeaponFire {
	
	public Lazer(Weapon owner) {
		super(owner);
		new LazerFixture(this);
	}
	
	@Override
	public void update() {
		body.setLinearVelocity(get_force(10f));
		super.update();
	}	
}
