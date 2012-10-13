package com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout;

import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.Global;

public class ScoutAI extends ShipControl {
	
	public void update(Ship ship) {
		switch (state)
		{
		case Disabled:
			// Do nothing.
			break;
		case AttackEarth:
			break;
		case AttackHero:
			
			float angle = ship.face(Space.instance().player());
		
			if (angle < Global.to_radians(10))
			{
				ship.forward();
			}
			else
			{
				ship.slowDown();
			}
			
			break;
		case Destroyed:
			break;
		case ExitingPortal:
			ship.get_body().setLinearVelocity(ship.get_force(1f));
			break;
		case Stop:
			ship.get_body().setLinearVelocity(ship.get_force(0f));
			break;
		case GroupMind:
			break;
		case Patrol:
			break;
		default:
			break;
		}	
	}
}
