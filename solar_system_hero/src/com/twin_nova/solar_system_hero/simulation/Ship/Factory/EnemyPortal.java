package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;

public class EnemyPortal extends SpaceBody {
	
	Date last_action = null;
	ArrayList<Ship> ships = new ArrayList<Ship>();

	public EnemyPortal(Vector2 start_coordinates, float start_direction) {
		super(start_coordinates, start_direction);
		new PortalFixture(this);
		last_action = new Date();
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
		
		for (Ship ship : ships) {
			ship.update();
		}
	}
	
	public void render() {
		super.render();
		
		for (Ship ship : ships) {
			ship.render();
		}
	}
	
	private void new_ship() {
		if (Space.instance().get_space_time() - last_action.getTime() > 5000) {
			last_action = new Date();
			ships.add(ShipBuilder.build_scout(body.getWorldCenter(), body.getAngle()));
		}
	}
}
