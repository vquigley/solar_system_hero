package com.twin_nova.utilities;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.VertexAttributes.*;
import com.badlogic.gdx.math.*;

public class MeshFactory {
	
	public static Mesh triangle(Vector2 point_1, 
								float point_1_colour, 
								Vector2 point_2, 
								float point_2_colour,
								Vector2 point_3, 
								float point_3_colour) {
		Mesh mesh = new Mesh(true, 3, 3, 
				  			 new VertexAttribute(Usage.Position, 3, "a_position"),
				  			 new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
		
		mesh.setVertices(new float[] { 	point_1.x, point_1.y, 0, point_1_colour,
									point_2.x, point_2.y, 0, point_2_colour,
									point_3.x, point_3.y, 0, point_3_colour }); 
		
		mesh.setIndices(new short[] { 0, 1, 2 });
		
		return mesh;
	}
	
	// Create a four sided shape, providing the co-ordinates starting from top left and working clockwise.
	public static Mesh square(Vector2 point_1, 
							  float point_1_colour, 
							  Vector2 point_2, 
							  float point_2_colour,
							  Vector2 point_3, 
							  float point_3_colour,
							  Vector2 point_4,
							  float point_4_colour) {
		Mesh mesh = new Mesh(true, 4, 4, 
	  			  			 new VertexAttribute(Usage.Position, 3, "a_position"),
	  			  			 new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
		
		mesh.setVertices(new float[] { 	point_1.x, point_1.y, 0, point_1_colour,
										point_2.x, point_2.y, 0, point_2_colour,
										point_4.x, point_4.y, 0, point_4_colour,
										point_3.x, point_3.y, 0, point_3_colour }); 
		
		mesh.setIndices(new short[] { 0, 1, 2, 3});
		
		return mesh;
	}
}
