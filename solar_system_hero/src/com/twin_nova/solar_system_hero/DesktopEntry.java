package com.twin_nova.solar_system_hero;

//import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopEntry {
        public static void main (String[] argv) {
                new LwjglApplication(new SolarSystemHero(), "Solar System Hero", 640, 480, false);               
        }
}
