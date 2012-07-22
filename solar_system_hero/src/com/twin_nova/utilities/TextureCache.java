package com.twin_nova.utilities;

import com.badlogic.gdx.Gdx;
import java.util.*;

public class TextureCache {
	
	public enum Texture
	{
		blue_lazer("blue_lazer.png"),
		earth("earth.png"),
		player("player.png"),
		star("star.png"),
		sun("sun.png"),
		lazer_bank("lazer_bank.png"),
		main_menu("main_menu.png"),
		end_game("end_game.png");
		
	
		public String file_name;
		
		private Texture (String a_file_name) {
			file_name = a_file_name;
		}
	}
	
	private HashMap<Texture, com.badlogic.gdx.graphics.Texture> textures = new HashMap<Texture, com.badlogic.gdx.graphics.Texture>();
	
	public TextureCache() {
		for (Texture texture : Texture.values()) {
			textures.put(texture, new com.badlogic.gdx.graphics.Texture(Gdx.files.internal(texture.file_name)));
		}
	}
	
	public com.badlogic.gdx.graphics.Texture get(Texture texture)
	{
		return (com.badlogic.gdx.graphics.Texture)textures.get(texture);
	}
}
