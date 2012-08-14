package com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout;

import com.twin_nova.solar_system_hero.simulation.Ship.Factory.IShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;

public class ScoutAI implements IShipControl {
	
	enum State {
		Disabled,
		Destroyed,
		ExitingPortal,
		AttackEarth,
		AttackHero,
		Patrol,
		GroupMind
	}

	@Override
	public void update(Ship ship) {
		switch (state)
		{
		case Disabled:
			// Do nothing.
			break;
		case AttackEarth:
			break;
		case AttackHero:
			break;
		case Destroyed:
			break;
		case ExitingPortal:
			ship.get_body().setLinearVelocity(ship.get_force(1f));
			break;
		case GroupMind:
			break;
		case Patrol:
			ship.get_body().setLinearVelocity(ship.get_force(5f));
			break;
		default:
			break;
		}	
	}
	
	State state = State.ExitingPortal;
}
