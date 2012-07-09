package com.twin_nova.solar_system_hero;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;

public class AppletEntry extends LwjglApplet {
	private static final long serialVersionUID = 1L;
    
	public AppletEntry()
    {
        super(new SolarSystemHero(), false);
        this.setSize(640, 480);
    }
}
