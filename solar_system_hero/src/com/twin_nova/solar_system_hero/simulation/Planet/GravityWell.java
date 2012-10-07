package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Console;

public class GravityWell {
	
	private float massPeriod;

	public GravityWell(SpaceBody affected_body, float massPeriod) {
		this.affected_body = affected_body;
		this.massPeriod = massPeriod;
	}
	
	public void update() {
		
		Vector2 planet_distance = new Vector2();
		planet_distance.add(affected_body.get_body().getPosition());
		planet_distance.sub(Space.instance().player().get_body().getPosition());
		float planet_radius = 0f;
		float planet_mass = 0f;
		if (affected_body.get_body().getFixtureList().size() > 0)
		{
			planet_radius = affected_body.get_body().getFixtureList().get(0).getShape().getRadius();
			planet_mass = ((PlanetFixture)affected_body.get_body().getFixtureList().get(0).getUserData()).get_mass();
		}
		float final_distance = planet_distance.len();
		
		if ((planet_radius * 5) > final_distance)
		{
			Gdx.app.log("GRAVITY", "GRAVITY");
			planet_distance = planet_distance.nor();
			float vec_sum = Math.abs(planet_distance.x) + Math.abs(planet_distance.y);
			planet_distance.mul((1/vec_sum) * planet_radius / final_distance);
			Space.instance().player().get_body().applyForceToCenter((planet_distance).mul((massPeriod * 5)));
		}
	}
	
	private SpaceBody affected_body;
}
