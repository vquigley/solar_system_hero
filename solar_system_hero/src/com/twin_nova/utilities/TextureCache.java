package com.twin_nova.utilities;

import com.badlogic.gdx.Gdx;
import java.util.*;

public class TextureCache {
	
	public enum Texture
	{
		blue_lazer("blue_lazer.png"),
		player("player.png"),
		star("star.png"),
		sun("sun.png"),
		lazer_bank("lazer_bank.png"),
		main_menu("main_menu.png"),
		end_game("end_game.png"),
		enemy_scout("scout.png"),
		impulse_engine("impulseEngine.png"),
		
		mercury("Mercury.png"),
		venus("venus.png"),
		earth("earth.png"),
		mars("mars.png"),
		
		digit_7("digit_7.png"),
		digit_6("digit_6.png"),
		digit_5("digit_5.png"),
		digit_4("digit_4.png"),
		digit_3("digit_3.png"),
		digit_2("digit_2.png"),
		digit_1("digit_1.png"),
		digit_0("digit_0.png");
		
	
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
