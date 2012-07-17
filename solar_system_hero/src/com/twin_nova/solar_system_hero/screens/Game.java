package com.twin_nova.solar_system_hero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.utilities.Global;

public class Game implements Screen {
	private static Space space 				= new Space();
	public static SpriteBatch batch 		= new SpriteBatch();
	private static OrthographicCamera o_cam = new OrthographicCamera(Gdx.graphics.getWidth(), 
																	 Gdx.graphics.getHeight());
	
	private static OrthographicCamera debug_camera = null;
	private static Box2DDebugRenderer debug_renderer = new Box2DDebugRenderer( true, true, true, true );
	public Game() {
		batch.setProjectionMatrix(o_cam.combined);
		batch.getProjectionMatrix().setToOrtho2D(0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		o_cam.update();
		o_cam.zoom = 2.5f;
		
		Boolean debug_view = false;
		
		if (debug_view != false) {
			debug_camera =  new OrthographicCamera(Gdx.graphics.getWidth(), 
					 							   Gdx.graphics.getHeight());
			batch.setProjectionMatrix(debug_camera.combined);
			debug_camera.update();
		}
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
	public void render(float arg0) {
		
		o_cam.update();
		batch.setProjectionMatrix(o_cam.combined);
		
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glClearColor(1f,1f, 1f, 1.0f);
		batch.begin();
		space.update();
		
		space.render(batch);
		//Console.flush_buffer(batch);
		
		
		batch.end();
		
		space.render_meshes();
		
		// Follow the player.
		
		
		if (debug_camera == null)
		{
			o_cam.position.set(Global.to_pixels(Space.player().get_body().getPosition().x), 
					   Global.to_pixels(Space.player().get_body().getPosition().y), 
					   0);
		}
		else
		{
			debug_camera.update();
			//debug_camera.position.set(Space.player().get_body().getPosition().x, 
			//						  Space.player().get_body().getPosition().y, 
			//						  0);
			debug_camera.zoom = 0.05f;
		    debug_renderer.render( Space.World(), debug_camera.combined );
		}
		
		o_cam.zoom = 2.5f;

		
		if (Gdx.input.isKeyPressed(Input.Keys.O))
			o_cam.zoom += 0.001;
		
		if (Gdx.input.isKeyPressed(Input.Keys.I))
			o_cam.zoom -= 0.001;
	}	

	@Override
	public void resume() {	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int arg0, int arg1) {
		o_cam = new OrthographicCamera(arg0, arg1);
		space.resize(arg0, arg1);
	}
}
