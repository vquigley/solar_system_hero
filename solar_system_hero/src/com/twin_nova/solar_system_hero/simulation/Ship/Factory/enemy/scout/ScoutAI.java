package com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout;

import com.badlogic.gdx.Input;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.Global;

public class ScoutAI extends ShipControl {
	
	public void update(Ship ship) {
		
		if (avoid() == false)
		{
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
					if (Game.isKeyPressed(ship, Input.Keys.W))
					{
						ship.forward();
					}
					
					
					
					if ((ship.distanceFrom(Space.instance().player()) < Global.ScaleDistance(10)) && 
						(Game.isKeyPressed(ship, Input.Keys.CONTROL_LEFT)))
					{
						ship.fire_a();
					}
				}
				else
				{
					if (Game.isKeyPressed(ship,  Input.Keys.S))
					{
						ship.slowDown();
					}
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

	private boolean avoid() {
		boolean doAvoid = false;
		
		for (SpaceBody body : Space.instance().planets())
		{
			
		}
		
		return doAvoid;
	}
	
	private boolean avoid(SpaceBody body)
	{
		boolean doAvoid = false;

		//for (SpaceBody body : Space.instance().planets())
		{
			
		}
		
		return doAvoid;
	}
}
