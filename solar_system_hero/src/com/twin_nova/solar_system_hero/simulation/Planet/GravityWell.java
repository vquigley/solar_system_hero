package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.solar_system_hero.simulation.BodyFixture;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.Console;

public class GravityWell {
	
	private float massPeriod;
	private float radiusFactor;

	public GravityWell(SpaceBody affected_body, float massPeriod) {
		this.affected_body = affected_body;
		this.massPeriod = massPeriod;
		this.radiusFactor= 5; 
	}
	
	public GravityWell(SpaceBody affected_body, float massPeriod, float radiusFactor) {
		this(affected_body, massPeriod);
		this.radiusFactor = radiusFactor;
	}
	
	public void update() {
		
		applyGravity(Space.instance().player());
		
		for (Ship ship : Space.instance().ships)
		{
			applyGravity(ship);
		}
	}
	
	void applyGravity(Ship ship)
	{
		Vector2 planet_distance = new Vector2();
		planet_distance.add(affected_body.get_body().getPosition());
		planet_distance.sub(ship.get_body().getPosition());
		float planet_radius = 0f;
		float planet_mass = 0f;
		
		if (affected_body.get_body().getFixtureList().size() > 0)
		{
			planet_radius = affected_body.get_body().getFixtureList().get(0).getShape().getRadius();
			planet_mass = ((BodyFixture)affected_body.get_body().getFixtureList().get(0).getUserData()).get_mass();
		}
		float final_distance = planet_distance.len();
		
		if ((planet_radius * radiusFactor) > final_distance)
		{
			Gdx.app.log("GRAVITY", "GRAVITY");
			planet_distance = planet_distance.nor();
			float vec_sum = Math.abs(planet_distance.x) + Math.abs(planet_distance.y);
			planet_distance.mul((1/vec_sum) * planet_radius / final_distance);
			ship.get_body().applyForceToCenter((planet_distance).mul((massPeriod * 2)));
		}
	}
	
	private SpaceBody affected_body;
}
