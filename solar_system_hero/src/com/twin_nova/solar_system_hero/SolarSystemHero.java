package com.twin_nova.solar_system_hero;

import com.twin_nova.solar_system_hero.screens.MainMenu;
import com.twin_nova.utilities.StringBuild;

public class SolarSystemHero extends com.badlogic.gdx.Game {
		
	@Override
	public void create() {
		StringBuild.load_alphabet();
		setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
