package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Hero;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.Weapon;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.LazerBank;
import com.twin_nova.utilities.Global;

public class Hero extends Ship {

	private static float top_speed = 20.0f;
	private static float top_torque = 5.0f;
	private static float impulse =2f;
	private static float torque = 0.5f;
	
	private ArrayList<Weapon> weapons_a = new ArrayList<Weapon>();
			
	public Hero(Vector2 start_coordinates, float start_direction, ShipControl control) {
		super(start_coordinates, start_direction, control);
		build_weapons();
		build_cockpit();
	}

	public float getScaleFactor() {
		return 0.4f;
	}

	@Override
	public ShipPart build_cockpit() {
		ShipPart part = new HeroCockpit(this, new Vector2(0,0));
		return part;
	}

	@Override
	public ShipPart build_engine() {
		return null;
	}

	@Override
	public ArrayList<Weapon> build_weapons() {
		weapons_a.add(new LazerBank(this, new Vector2(Global.to_meters(150),Global.to_meters(20)), 0));
		weapons_a.add(new LazerBank(this, new Vector2(Global.to_meters(150),Global.to_meters(-20)), 0));
		
		for (Weapon weapon : weapons_a) {
			weapon.get_fixture().getFilterData().categoryBits = get_weapon_category();
			weapon.get_fixture().getFilterData().maskBits = get_weapon_mask();
		} 
		
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
	
	@Override
	public short get_weapon_mask() {
		return Space.instance().enemy_weapon_mask;
	}
	
	@Override
	public short get_weapon_category() {
		return Space.instance().player_category;
	}
	
	public void Destory() {
	}

	@Override
	public float forwardImpulse() {
		return impulse;
	}

	@Override
	public float torque() {
		return torque;
	}

	@Override
	public float top_torque() {
		return top_torque;
	}
}
