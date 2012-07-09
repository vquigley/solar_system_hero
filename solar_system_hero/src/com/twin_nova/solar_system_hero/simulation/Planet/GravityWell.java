package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Console;

public class GravityWell {
	
	public GravityWell(SpaceBody affected_body) {
		this.affected_body = affected_body;
	}
	
	public void update() {
		
		Vector2 planet_distance = new Vector2();
		planet_distance.add(affected_body.get_body().getPosition());
		planet_distance.sub(Space.player().get_body().getPosition());
		float planet_radius = 0f;
		if (affected_body.get_body().getFixtureList().size() > 0)
		{
			planet_radius = affected_body.get_body().getFixtureList().get(0).getShape().getRadius();
		}
		float final_distance = planet_distance.len();
		
		if ((planet_radius * 3) > final_distance)
		{
			Console.write_line("Gravity");
			planet_distance = planet_distance.nor();
			float vec_sum = Math.abs(planet_distance.x) + Math.abs(planet_distance.y);
			planet_distance.mul((1/vec_sum) * planet_radius / final_distance);
			Space.player().get_body().applyForceToCenter((planet_distance).mul((affected_body.get_body().getMass() / Space.earth_mass) * 10));
		}
	}
	
	private SpaceBody affected_body;
}
