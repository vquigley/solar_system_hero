package com.twin_nova.solar_system_hero.simulation.Ship.Factory.enemy.scout;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipControl;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.LazerBank;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon.Weapon;
import com.twin_nova.utilities.Global;

public class Scout extends Ship {

	private static float top_speed = 3.0f;
	private static float top_torque = 20.0f;
	private static float impulse = 20f;
	private static float torque = 20f;
	
	private ArrayList<Weapon> weapons_a = new ArrayList<Weapon>();
	
	public Scout(Vector2 start_coordinates, float start_direction, ShipControl control) {
		super(start_coordinates, start_direction, control);
		build_cockpit();
		build_weapons();
	}

	@Override
	public ShipPart build_cockpit() {
		ShipPart part = new ScoutCockpit(this);
		return part;
	}

	@Override
	public ShipPart build_engine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Weapon> build_weapons() {
		weapons_a.add(new LazerBank(this, new Vector2(Scale(Global.to_meters(40)),
													 Scale(Global.to_meters(10))), 0));
		
		weapons_a.add(new LazerBank(this, new Vector2(Scale(Global.to_meters(40)),
				 Scale(Global.to_meters(-10))), 0));
		
		for (Weapon weapon : weapons_a) {
			weapon.get_fixture().getFilterData().categoryBits = get_weapon_category();
			weapon.get_fixture().getFilterData().maskBits = get_weapon_mask();
		} 
		
		return weapons_a;
	}
	
	public float getScaleFactor() {
		return 0.4f;
	}

	@Override
	public float top_speed() {
		return top_speed;
	}

	@Override
	public void fire_a() {
		for (Weapon weapon : weapons_a) {
			weapon.fire();
		}
	}

	@Override
	public void fire_b() {
		// TODO Auto-generated method stub
		
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
