package com.twin_nova.solar_system_hero.simulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.twin_nova.solar_system_hero.simulation.Planet.Planet;
import com.twin_nova.solar_system_hero.simulation.Planet.PlanetBuilder;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipBuilder;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class Space {
	// All solar system masses are defined in terms of earth masses, which is defined as kgs.
	public static final float earth_mass 		= 1000;
	public static final float earth_year		= 30; // 1 minutes.
	public static final float half_size_x = 50f;
	public static final float half_size_y = 50f;
	private static Sprite[] stars = null;
	SpaceContact contact_listener = new SpaceContact();
	public static ArrayList<SpaceBody> nuke_list = new ArrayList<SpaceBody>();
	public static Date begin_date = new Date();
	public static Date space_date = new Date();
	
	private static World world 					= new World(new Vector2(0, 0), false);
	private static ArrayList<Planet> planets 	= new ArrayList<Planet>();
	private static LinkedList<Ship> ships 		= new LinkedList<Ship>();
	private static Ship player					= ShipBuilder.build_hero(new Vector2(0, 4), 0);
	private static Star sun 					= new Star();
	public static Boundary boundary				= new Boundary();
	
	public static World World() {
		return world;
	}

	public static ArrayList<Planet> planets() {
		return planets;
	}
	
	public static Star sun() {
		return sun;
	}
	
	public static Ship player() {
		return player;
	}
	
	public Space() {
		begin_date = new Date();
		// Create stars.
		int stars_per_meter = 1;
		
		//sprite.setRotation(start_direction);
		stars = new Sprite[(int)(stars_per_meter * half_size_x * 2 * half_size_y * 2)];
		//stars = new Mesh[10];
		//float star_size = 5f;
		//float star_colour = Color.toFloatBits(255, 255, 255, 255);
		float scale_min = 0.05f;
		float scale_max = 0.3f;
		float scale = scale_min;
		for (int i = 0; i < stars.length; ++i)
		{
			int x_sign = (Math.random() > 0.5) ? 1 : -1;
			int y_sign = (Math.random() > 0.5) ? 1 : -1;
			
			float x_pos = Global.to_pixels((float)Math.random() * half_size_x * x_sign);
			float y_pos = Global.to_pixels((float)Math.random() * half_size_y * y_sign);
			
			Vector2 start_point = new Vector2(x_pos, y_pos);

			
	//		Gdx.app.log(String.format("Star 1 X %f - Y %f", start_point.x, start_point.y), "");
	//		Gdx.app.log(String.format("Star 2 X %f - Y %f", start_point.add(star_size, 0).x, start_point.add(star_size, 0).y), "");
	//		Gdx.app.log(String.format("Star 3 X %f - Y %f", start_point.add(star_size / 2, star_size).x, start_point.add(star_size / 2, star_size).y), "");
			/*stars[i] = MeshFactory.triangle(start_point, 
											star_colour, 
											new Vector2(start_point.x + star_size, start_point.y), 
											star_colour, 
											new Vector2(start_point.x + (star_size / 2), start_point.y + star_size),
											star_colour);*/
			stars[i] = new Sprite(Global.textures.get(Texture.star));
			stars[i].setPosition(start_point.x, start_point.y);
			stars[i].setScale(scale);
			scale += scale_min;
			
			if (scale > scale_max) {
				scale = scale_min;
			}
			
		}
		
		// Add planets, their position in space is dependent on the system time.
		planets.add(PlanetBuilder.build_earth());
		
		// Create controlled ship. It always starts at earth.
		ships.add(player);
		
		world.setContactListener(contact_listener);
	}
	
	public void update() {
		space_date = new Date();
		nuke();
		world.step(Global.step_delta(), 6, 3);
		
		sun.update();
		
		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			// Reset.
			player.body.setTransform(0, 0, 0);
		}
		
		for (Planet planet : planets) {
			planet.update();
		}
		
		if (boundary.update(player) == false) {
			//  Let ship steer itself.
			player.update();
		}
	}
	
	private void nuke()
	{
		for (int i = nuke_list.size() - 1; i >= 0; --i)
		{
			nuke_list.get(i).destroy();
		}
		
		nuke_list.clear();
	}
	
	public void render(SpriteBatch batch) {
				
		for (Sprite star : stars) {
			star.draw(batch);
		}
		
		for (Planet planet : planets) {
			planet.render();
		}
		
		for (Ship ship : ships) {
			ship.render();
		}
		
		sun.render();
	}
	
	public void render_meshes()
	{				
		boundary.render();		
	}

	public void resize(int width, int height) {
/*		for (Planet planet : planets) {
			planet.resize(width, height);
		}
		
		for (Ship ship : ships) {
			ship.resize(width, height);
		}
		
		sun.resize(width, height);*/		
	}
	
	public static long get_space_time() {
		return (space_date.getTime() - begin_date.getTime());
	}
}
