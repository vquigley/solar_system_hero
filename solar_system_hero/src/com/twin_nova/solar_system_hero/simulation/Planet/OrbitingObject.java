package com.twin_nova.solar_system_hero.simulation.Planet;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.SpaceBody;
import com.twin_nova.utilities.Global;

public class OrbitingObject extends SpaceBody {
	
	protected OrbitingObject(Vector2 ellipse_center,
							 Vector2 ellipse_limits,
							 float ellipse_angle) {
		// The orbit takes care of the position.
		super(new Vector2(0,0), 
			  0f);
		
		calculate_ellipse(ellipse_center.x, 
						  ellipse_center.y, 
						  ellipse_limits.x, 
						  ellipse_limits.y, 
						  ellipse_angle);
	}
	
	// Algorithm taken from http://en.wikipedia.org/w/index.php?title=Ellipse&oldid=456212176#Ellipses_in_computer_graphics
	public void calculate_ellipse(float x, float y, float a, float b, double angle) 
	{
	  orbit_points = new Vector2[number_of_steps];
	 
	  // Angle is given by Degree Value
	  double beta = -angle * (Math.PI / 180); //(Math.PI/180) converts Degree Value into Radians.
	  double sinbeta = Math.sin(beta);
	  double cosbeta = Math.cos(beta);
	 
	  float degrees_per_step = (360f / number_of_steps);
	  for (int i = 0; i < number_of_steps; ++i) 
	  {
	    double alpha = i * degrees_per_step * (Math.PI / 180) ;
	    double sinalpha = Math.sin(alpha);
	    double cosalpha = Math.cos(alpha);
	 
	    double X = x + (a * cosalpha * cosbeta - b * sinalpha * sinbeta);
	    double Y = y + (a * cosalpha * sinbeta + b * sinalpha * cosbeta);
	 
	    orbit_points[i] = new Vector2((float)X, (float)Y);
	   }
	}

	public void update() {
		long game_time = Space.get_space_time();
		
		long time_of_current_revolution = game_time % (long)(Space.earth_year * 1000);
		
		int next_position = (int)((orbit_points.length - 1) * (time_of_current_revolution / (Space.earth_year * 1000)));

		body.setTransform(orbit_points[next_position], 0);
		
		super.update();
	}
	
	@Override
	protected BodyDef get_body_definition(Vector2 start_coordinates,
										  float start_direction) {
		BodyDef bd = new BodyDef();
		bd.position.set(start_coordinates);
		bd.angle = Global.to_radians(start_direction);
		bd.type = BodyDef.BodyType.DynamicBody;
		
		return bd;
	}

	private Vector2[] orbit_points = null;
	private static int number_of_steps = 3600;
}
