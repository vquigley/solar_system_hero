package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.graphics.*;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.MeshFactory;
import com.badlogic.gdx.math.*;

public class Boundary {
	private float SPACE_BOUNDARY_X = Global.to_pixels(Space.half_size_x);
	private float SPACE_BOUNDARY_Y = Global.to_pixels(Space.half_size_y);
	private float BOUNDARY_SIZE = Global.to_pixels(5f);
	//  Boundary, made up of four meshes.
	//p1  ___________________ p2
	//p4 |_________1_________|p3
	//   | |p11		     p5| |
	//   |4|			   |2|
	//p8 |_|p12__________p7|_|p6
	//p10|_________3_________|p9
	
	private Mesh[] meshes = new Mesh[4];
    
	public Boundary()
	{		
		Vector2 point_1 = new Vector2(-SPACE_BOUNDARY_X - BOUNDARY_SIZE,
									  SPACE_BOUNDARY_Y + BOUNDARY_SIZE);
		
		Vector2 point_2 = new Vector2(SPACE_BOUNDARY_X + BOUNDARY_SIZE,
									  SPACE_BOUNDARY_Y + BOUNDARY_SIZE);
		
		Vector2 point_3 = new Vector2(SPACE_BOUNDARY_X + BOUNDARY_SIZE,
									  SPACE_BOUNDARY_Y);
		
		Vector2 point_4 = new Vector2(-SPACE_BOUNDARY_X - BOUNDARY_SIZE,
									  SPACE_BOUNDARY_Y);
		
		Vector2 point_5 = new Vector2(SPACE_BOUNDARY_X,
				  					  SPACE_BOUNDARY_Y);
		
		Vector2 point_6 = new Vector2(SPACE_BOUNDARY_X + BOUNDARY_SIZE,
				  					 -SPACE_BOUNDARY_Y);
		
		Vector2 point_7 = new Vector2(SPACE_BOUNDARY_X,
				  					  -SPACE_BOUNDARY_Y);
		
		Vector2 point_8 = new Vector2(-SPACE_BOUNDARY_X - BOUNDARY_SIZE,
				  					  -SPACE_BOUNDARY_Y);
		
		Vector2 point_9 = new Vector2(SPACE_BOUNDARY_X + BOUNDARY_SIZE,
				  					  -SPACE_BOUNDARY_Y - BOUNDARY_SIZE);
		
		Vector2 point_10 = new Vector2(-SPACE_BOUNDARY_X - BOUNDARY_SIZE,
				  					   -SPACE_BOUNDARY_Y - BOUNDARY_SIZE);
		
		Vector2 point_11 = new Vector2(-SPACE_BOUNDARY_X,
				  					   SPACE_BOUNDARY_Y);
		
		Vector2 point_12 = new Vector2(-SPACE_BOUNDARY_X,
				  					   -SPACE_BOUNDARY_Y);
		
		float border_colour = Color.toFloatBits(255, 0, 0, 255);
		
		meshes[0] = MeshFactory.square(	point_1, border_colour,
										point_2, border_colour,
										point_3, border_colour,
										point_4, border_colour);
		
		meshes[1] = MeshFactory.square(	point_5, border_colour,
										point_3, border_colour,
										point_6, border_colour,
										point_7, border_colour);
		
		meshes[2] = MeshFactory.square(	point_8, border_colour,
										point_6, border_colour,
										point_9, border_colour,
										point_10, border_colour);
		
		meshes[3] = MeshFactory.square(	point_4, border_colour,
										point_11, border_colour,
										point_12, border_colour,
										point_8, border_colour);
	}
	
	public void render()
	{
		for (Mesh mesh : meshes) {
            mesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
		}
	}
	
	public boolean update(Ship ship)
	{
		boolean handled = true;
		
		// Force a direction on the ship if they have gone beyond the bounds of space.
		if (ship.get_body().getPosition().x < Global.to_degrees(-SPACE_BOUNDARY_X)) {
			ship.force_turn(Global.Dir.Right);
		}
		else if (ship.get_body().getPosition().x > Global.to_degrees(SPACE_BOUNDARY_X)) {
			ship.force_turn(Global.Dir.Left);
		}
		else if (ship.get_body().getPosition().y > Global.to_degrees(SPACE_BOUNDARY_Y)) {
			ship.force_turn(Global.Dir.Down);
		}
		else if (ship.get_body().getPosition().y < Global.to_degrees(-SPACE_BOUNDARY_Y)) {
			ship.force_turn(Global.Dir.Up);
		}
		else
		{
			handled = false;
		}
		
		return handled;
	}
	
	public boolean contains(SpaceBody body) {
		return ((Math.abs(Global.to_pixels(body.get_body().getPosition().x)) <= SPACE_BOUNDARY_X) &&
				(Math.abs(Global.to_pixels(body.get_body().getPosition().y)) <= SPACE_BOUNDARY_Y));
	}
}
