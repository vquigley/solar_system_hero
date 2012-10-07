package com.twin_nova.solar_system_hero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.twin_nova.solar_system_hero.simulation.Hud;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.utilities.Global;

public class Game implements Screen {
	private Space space 				= Space.init();
	private Hud hud 				= Hud.init();
	public static SpriteBatch batch 		= new SpriteBatch();
	public static float delta = 0;
	private OrthographicCamera env_cam = new OrthographicCamera(Gdx.graphics.getWidth(), 
																	 Gdx.graphics.getHeight());
	
	private OrthographicCamera debug_camera = null;
	private Box2DDebugRenderer debug_renderer = new Box2DDebugRenderer( true, true, true, true );
	private com.badlogic.gdx.Game controller = null;
	
	private OrthographicCamera hud_cam = new OrthographicCamera(Gdx.graphics.getWidth(), 
			 													Gdx.graphics.getHeight());
	
	public Game(com.badlogic.gdx.Game controller) {
		space 				= Space.init();
		hud 				= Hud.init();
		
		batch.setProjectionMatrix(env_cam.combined);
		batch.setProjectionMatrix(hud_cam.combined);
		batch.getProjectionMatrix().setToOrtho2D(0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		env_cam.update();
		env_cam.zoom = 10.5f;
		
		
		Boolean debug_view = false;
		
		if (debug_view != false) {
			debug_camera =  new OrthographicCamera(Gdx.graphics.getWidth(), 
					 							   Gdx.graphics.getHeight());
			batch.setProjectionMatrix(debug_camera.combined);
			debug_camera.update();
			debug_camera.zoom = 0.15f;
		}
		
		this.controller = controller;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		Game.delta = delta;

		env_cam.update();
		batch.setProjectionMatrix(env_cam.combined);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		space.update();
		space.render(batch, delta);
		batch.end();

		hud_cam.update();
		batch.setProjectionMatrix(hud_cam.combined);
		batch.begin();
		hud.update();
		hud.render();
		batch.end();
		
		space.render_meshes();
		
		// Follow the player.
		
		
		if (debug_camera != null)
		{
			debug_camera.update();
			batch.setProjectionMatrix(debug_camera.combined);
			//debug_camera.position.set(Space.player().get_body().getPosition().x, 
			//						  Space.player().get_body().getPosition().y, 
			//						  0);
			//debug_camera.zoom = 0.05f;
		    debug_renderer.render( Space.instance().World(), debug_camera.combined );
		    
		}
		else if (Space.instance().player() != null) {
			env_cam.position.set(Global.to_pixels(Space.instance().player().get_body().getPosition().x), 
					Global.to_pixels(Space.instance().player().get_body().getPosition().y), 
					0);
			
			hud_cam.position.set(0, 
					0, 
					0);
			
		}
		env_cam.zoom = 10.5f;

		
		if (Gdx.input.isKeyPressed(Input.Keys.O))
			env_cam.zoom += 0.001;
		
		if (Gdx.input.isKeyPressed(Input.Keys.I))
			env_cam.zoom -= 0.001;
		

		if (space.player() == null) {
			controller.setScreen(new EndGame(controller));
		}
	}	

	@Override
	public void resume() {	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int arg0, int arg1) {
		env_cam = new OrthographicCamera(arg0, arg1);
		hud_cam = new OrthographicCamera(arg0, arg1);
		space.resize(arg0, arg1);
	}
}
