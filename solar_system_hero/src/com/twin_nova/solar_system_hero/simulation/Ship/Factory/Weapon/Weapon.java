package com.twin_nova.solar_system_hero.simulation.Ship.Factory.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.twin_nova.solar_system_hero.simulation.Space;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.Ship;
import com.twin_nova.solar_system_hero.simulation.Ship.Factory.ShipPart;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public abstract class Weapon extends ShipPart {

	public abstract int fire_rate();
	private static FixtureDef fixture_def = null;
	private static final float friction = 0.5f;
	private static final float restitution = 0.2f;
	
	protected Weapon(Ship ship, Texture texture, Vector2 body_offset, float angle_offset) {
		super(ship, texture, body_offset, angle_offset);
	}
	
	public void fire()
	{		
		if ((last_fired_lazer == null) || 
			((Space.instance().get_space_time() - last_fired_lazer) >= (1000 / fire_rate()))) {
			fire_list.add(get_weapon_fire());
			last_fired_lazer = Space.instance().get_space_time();
		}
	}
	
	protected abstract WeaponFire get_weapon_fire();
	
	public void update() {
		super.update();
		
		Iterator<WeaponFire> it = fire_list.iterator();
		
		while (it.hasNext()) {
			
			WeaponFire fire = it.next();
			
			if ((nuke_list.contains(fire)) || (Space.instance().boundary.contains(fire) == false)) {
				it.remove();
				fire.destroy();
			}
			else
			{
				fire.update();
			}
		}
		
		nuke_list.clear();
	}
	
	public void render() {
		super.render();
		
		Iterator<WeaponFire> it = fire_list.iterator();
		
		while (it.hasNext()) {
			WeaponFire fire = it.next();
			fire.render();
		}
			
	}
	
	@Override
	public FixtureDef get_fixture_def() {
		if (fixture_def == null) {
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(Global.to_meters(getSpriteRenderedWidth() * owner.getScaleFactor()), 
						   Global.to_meters(getSpriteRenderedHeight() * owner.getScaleFactor()), 
						   get_body_offset(),
						   0);
			
			fixture_def = new FixtureDef();
			fixture_def.shape = shape;
			fixture_def.density = Global.density(get_mass(), fixture_def.shape.getRadius());
			fixture_def.friction = friction;
			fixture_def.restitution = restitution;
		}
		
		return fixture_def;
	}
	
	public void destroy(WeaponFire fire) {
		nuke_list.add(fire);
	}
	
	private Long last_fired_lazer = null;
	private List<WeaponFire> fire_list = new ArrayList<WeaponFire>();
	public ArrayList<WeaponFire> nuke_list = new ArrayList<WeaponFire>();

}
