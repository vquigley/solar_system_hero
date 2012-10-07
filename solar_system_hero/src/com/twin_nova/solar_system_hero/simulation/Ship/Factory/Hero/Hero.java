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
	
	private ArrayList<Weapon> weapons_a = new ArrayList<Weapon>();

	private ImpulseEngine leftFrontImpulse;
	private ImpulseEngine rightFrontImpulse;
	private ImpulseEngine rearLeftImpulse;
	private ImpulseEngine rearRightImpulse;
	private ImpulseEngine leftTopImpulse;
	private ImpulseEngine leftBottomImpulse;
	private ImpulseEngine rightTopImpulse;
	private ImpulseEngine rightBottomImpulse;
			
	public Hero(Vector2 start_coordinates, float start_direction, ShipControl control) {
		super(start_coordinates, start_direction, control);
		build_weapons();
		build_cockpit();
		//build_impulse_engines();
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
	
	private void build_impulse_engines() {
		Vector2 frontLeft = new Vector2(242, 90);
		Vector2 frontRight = new Vector2(242, -90);
		
		Vector2 rearLeft = new Vector2(-242, 140);
		Vector2 rearRight = new Vector2(-242, -140);
		
		Vector2 leftTop = new Vector2(172.6f, 121);
		Vector2 leftBottom = new Vector2(-205, 174);
		
		Vector2 rightTop = new Vector2(172.6f, -121);
		Vector2 rightBottom = new Vector2(-205, -174);
		
		frontLeft.mul(1f / Global.pixels_per_metre);
		frontLeft.mul(getScaleFactor());
		
		frontRight.mul(1f / Global.pixels_per_metre);
		frontRight.mul(getScaleFactor());
		
		rearLeft.mul(1f / Global.pixels_per_metre);
		rearLeft.mul(getScaleFactor());
		
		rearRight.mul(1f / Global.pixels_per_metre);
		rearRight.mul(getScaleFactor());
		
		leftTop.mul(1f / Global.pixels_per_metre);
		leftTop.mul(getScaleFactor());
		
		leftBottom.mul(1f / Global.pixels_per_metre);
		leftBottom.mul(getScaleFactor());
		
		rightTop.mul(1f / Global.pixels_per_metre);
		rightTop.mul(getScaleFactor());
		
		rightBottom.mul(1f / Global.pixels_per_metre);
		rightBottom.mul(getScaleFactor());		
		
		leftFrontImpulse 	= new ImpulseEngine(this, frontLeft, Global.to_radians(180));
		rightFrontImpulse 	= new ImpulseEngine(this, frontRight, Global.to_radians(180));
		rearLeftImpulse 	= new ImpulseEngine(this, rearLeft, 0);
		rearRightImpulse 	= new ImpulseEngine(this, rearRight, 0);
		leftTopImpulse 		= new ImpulseEngine(this, leftTop, Global.to_radians(-90));
		leftBottomImpulse 	= new ImpulseEngine(this, leftBottom, Global.to_radians(-90));
		rightTopImpulse 	= new ImpulseEngine(this, rightTop, Global.to_radians(90));
		rightBottomImpulse	= new ImpulseEngine(this, rightBottom, Global.to_radians(90));
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

	private void fireEngines(ImpulseEngine one,
							 ImpulseEngine two) {
		if (one != null)
		{
			one.fire();
		}
		
		if (two != null)
		{
			two.fire();
		}
	}
	
	public void impulseForward()
	{
		fireEngines(rearLeftImpulse, 
					rearRightImpulse);
	}	

	public void impulseBackward()
	{
		fireEngines(leftFrontImpulse, 
				rightFrontImpulse);
	}
	
	public void impulseLeft()
	{
		fireEngines(rightTopImpulse, 
				rightBottomImpulse);
	}
	
	public void impulseRight()
	{
		fireEngines(leftTopImpulse, 
				leftBottomImpulse);
	}
	
	public void impulseTurnLeft()
	{
		fireEngines(rightTopImpulse, 
				leftBottomImpulse);
	}
	
	public void impulseTurnRight()
	{
		fireEngines(leftTopImpulse, 
				rightBottomImpulse);
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
}
