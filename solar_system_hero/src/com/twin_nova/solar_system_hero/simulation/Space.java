package com.twin_nova.solar_system_hero.simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.twin_nova.solar_system_hero.simulation.Planet.Planet;
import com.twin_nova.solar_system_hero.simulation.Planet.PlanetBuilder;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipBuilder;
import com.twin_nova.utilities.Console;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class Space {	
	private World world = new World(new Vector2(0, 0), false);
	public short player_category = 1;
	public short enemy_category = 2;

	public short enemy_weapon_mask = player_category;
	public short player_weapon_mask = enemy_category;
	
	// All solar system masses are defined in terms of earth masses, which is defined as kgs.
	public final float earth_mass 		= 1000;
	public final float earth_year		= 30; // 1 minutes.
	public final float half_size_x = 50f;
	public final float half_size_y = 50f;
	private Sprite[] stars = null;
	SpaceContact contact_listener = new SpaceContact();
	ContactFilter contact_filter = new ContactFilter();
	public ArrayList<SpaceBody> nuke_list = new ArrayList<SpaceBody>();
	public Date begin_date = new Date();
	public Date space_date = new Date();
	
	private ArrayList<Planet> planets 	= new ArrayList<Planet>();
	private LinkedList<Ship> ships 		= new LinkedList<Ship>();
	private Ship player					= null;
	private static Space self;
	private Star sun 					= null;
	public  Boundary boundary				= null;
	
	boolean end_game = false;
	

	ParticleEffect particleEffect = new ParticleEffect();
	
	public World World() {
		return world;
	}

	public ArrayList<Planet> planets() {
		return planets;
	}
	
	public Star sun() {
		return sun;
	}
	
	public Ship player() {
		return player;
	}
	
	public static Space init() {
		self = new Space();
		return self;
	}
	
	public static Space instance() {
		return self;
	}
	
	private Space() {
		self = this;
		begin_date = new Date();
		// Create stars.
		float stars_per_meter = 0.5f;
		
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
		
		player = ShipBuilder.build_hero(new Vector2(0, 4), 0);
		
		// Create controlled ship. It always starts at earth.
		ships.add(player);
		
		world.setContactListener(contact_listener);
		world.setContactFilter(contact_filter);
		

		sun 					= new Star();
		boundary				= new Boundary();
		
		particleEffect.load(Gdx.files.internal("particle/explosion"), 
	            			Gdx.files.internal("particle/images"));
	}
	
	public void update() {
		world.step(Global.step_delta(), 6, 3);
		
		sun.update();
		
		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			// Reset.
			player.body.setTransform(0, 0, 0);
		}
		
		for (Planet planet : planets) {
			planet.update();
		}
		
		if ((end_game == false) && (player != null) && (boundary.update(player) == false)) {
			//  Let ship steer itself.
			player.update();
		}
		space_date = new Date();
		nuke();
	}
	
	private void nuke()
	{
		for (int i = nuke_list.size() - 1; i >= 0; --i)
		{
			nuke_list.get(i).destroy();
			

			if (nuke_list.get(i) == player) {
				end_game = true;

				particleEffect.start();
			}
		}
		
		nuke_list.clear();
	}
	
	public void render(SpriteBatch batch, float delta) {
		  
		for (Sprite star : stars) {
			star.draw(batch);
		}
		
		for (Planet planet : planets) {
			planet.render();
		}
		
		for (Ship ship : ships) {
			if (end_game == false) {
				ship.render();
			}
		}
		
		sun.render();
		
		if (end_game != false) {		
			particleEffect.setPosition(Global.to_pixels(player.get_body().getWorldCenter().x), 
									   Global.to_pixels(player.get_body().getWorldCenter().y));
			particleEffect.draw(batch, delta);
			
			if (particleEffect.isComplete() != false) {
				player = null;
			}
		}
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
	
	public long get_space_time() {
		long time = (space_date.getTime() - begin_date.getTime());
		
		if (time < 0) {
			time = 0;
		}
		
		return time;
	}
}
