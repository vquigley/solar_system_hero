package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;

public class EnemyPortal extends SpaceBody {
	
	long last_action = Long.MAX_VALUE;
	ArrayList<Ship> exiting_ships = new ArrayList<Ship>();
	
	public float getScaleFactor() {
		return 4f;
	}


	public EnemyPortal(Vector2 start_coordinates, float start_direction) {
		super(start_coordinates, start_direction);
		new PortalFixture(this);
		last_action = Space.instance().get_space_time();
	}

	@Override
	protected BodyDef get_body_definition(Vector2 start_coordinates,
										  float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = Global.to_radians(start_direction);
		bd.type = BodyDef.BodyType.StaticBody;
		
		return bd;
	}
	
	public void update()
	{
		super.update();
		
		new_ship();
		
		Iterator<Ship> ship_it = exiting_ships.iterator();
		
		while (ship_it.hasNext()) {
			Ship ship = ship_it.next();
			ship.update();
			if ((Math.abs(ship.get_body().getPosition().x - get_body().getPosition().x) > 5) ||
				(Math.abs(ship.get_body().getPosition().y - get_body().getPosition().y) > 5)) {
				ship.control().set_state(ShipControl.State.Stop);
				Space.instance().add_ship(ship);
				ship_it.remove();
			}
		}
	}
	
	public void render() {
		super.render();
		
		for (Ship ship : exiting_ships) {
			ship.render();
		}
	}
	
	private void new_ship() {
		if (Space.instance().get_space_time() - last_action > 5000) {
			last_action = Space.instance().get_space_time();
			Ship ship = ShipBuilder.build_scout(body.getWorldCenter(), 
												Global.to_degrees(body.getAngle()));
			ship.control().set_state(ShipControl.State.ExitingPortal);
			exiting_ships.add(ship);
		}
	}
}
