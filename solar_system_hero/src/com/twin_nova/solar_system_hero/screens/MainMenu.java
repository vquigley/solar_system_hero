package com.twin_nova.solar_system_hero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public class MainMenu implements Screen{

	 private SpriteBatch spriteBatch = new SpriteBatch();
     private com.badlogic.gdx.graphics.Texture splsh = Global.textures.get(Texture.main_menu);
     
	private com.badlogic.gdx.Game controller = null;
	
	public MainMenu(com.badlogic.gdx.Game controller) {
		this.controller = controller;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         spriteBatch.begin();
         spriteBatch.draw(splsh, 0, 0);
         spriteBatch.end();
         
		if (Gdx.input.isTouched() != false) {
			controller.setScreen(new Game(controller));
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
