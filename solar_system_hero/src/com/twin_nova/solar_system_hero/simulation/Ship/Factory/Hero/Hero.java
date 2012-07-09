package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.IShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.Weapon;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.LazerBank;
import com.twin_nova.utilities.Console;

public class Hero extends Ship {

	private static float top_speed = 3.0f;
	
	private ArrayList<Weapon> weapons_a = new ArrayList<Weapon>();
			
	public Hero(Vector2 start_coordinates, int start_direction, IShipControl control) {
		super(start_coordinates, start_direction, control);
	}

	@Override
	public ShipPart build_cockpit() {
		return new HeroCockpit(this, new Vector2(0,0));
	}

	@Override
	public ShipPart build_engine() {
		return null;
	}

	@Override
	public ArrayList<Weapon> build_weapons() {
		weapons_a.add(new LazerBank(this, new Vector2(1,1), 0));
		
		return weapons_a;
	}
	
	public float top_speed() {
		return top_speed;
	}
	

	public void fire_a() {
		for (Weapon weapon : weapons_a) {
			weapon.fire();
		}
	}
	
	public void fire_b() {
		
	}
	
	public void update() {
		super.update();
		Console.write_line("Body X pos", body.getPosition().x);
		Console.write_line("Body Y pos", body.getPosition().y);
	}
}
