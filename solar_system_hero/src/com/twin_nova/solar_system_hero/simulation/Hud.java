package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.Score;
import com.twin_nova.utilities.StringBuild;

public class Hud {

	private static Hud hud;
	
	private Hud()
	{
		setScore();
	}
	
	private void setScore() {
		
		score = new StringBuild(new Vector2(-Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() / 2 - 50), String.format("Score  %s", Space.instance().getScore().getValue()));
	}

	public static Hud init() {
		hud = new Hud();
		return hud;
	}
	
	StringBuild score;

	public void update()
	{
		setScore();
	}
	
	public void render()
	{
		score.render(Game.batch);
	}
}
