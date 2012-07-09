package com.twin_nova.solar_system_hero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.utilities.Console;
import com.twin_nova.utilities.Global;

public class Game implements Screen {
	private static Space space 				= new Space();
	public static SpriteBatch batch 		= new SpriteBatch();
	private static OrthographicCamera o_cam = new OrthographicCamera(Gdx.graphics.getWidth(), 
																	 Gdx.graphics.getHeight());
	
	public Game() {
		batch.setProjectionMatrix(o_cam.combined);
		batch.getProjectionMatrix().setToOrtho2D(0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		o_cam.update();
		o_cam.zoom = 2.5f;
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
		//Gdx.gl.glClearColor(0.55f, 0, 0, 1.0f);
		batch.begin();
		space.update();
		space.render(batch);
		Console.flush_buffer(batch);
		batch.end();
		
		space.render_meshes();
		
		// Follow the player.
		o_cam.position.set(Global.to_pixels(Space.player().get_body().getPosition().x), 
						   Global.to_pixels(Space.player().get_body().getPosition().y), 
						   0);

		o_cam.zoom = 2.5f;
		//if (Gdx.input.isKeyPressed(Input.Keys.O))
		//	o_cam.zoom += 0.001;
		
		//if (Gdx.input.isKeyPressed(Input.Keys.I))
		//	o_cam.zoom -= 0.001;
		
		
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
