package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public abstract class BodyFixture {
	
	protected BodyFixture(SpaceBody owner,
						  Texture texture,
						  Vector2 body_offset, 
						  float angle_offset) {
		this.owner = owner;
		this.body_offset = body_offset;
		this.angle_offset = angle_offset;
		current_health = get_health();
		sprite = new Sprite(Global.textures.get(texture));
		owner.get_body().createFixture(get_fixture_def()).setUserData(this);
	}
	
	public Vector2 get_world_position() {
		float position_x_meters = (owner.body.getPosition().x + body_offset.x) - Global.to_meters(get_sprite().getWidth() / 2);
		float position_y_meters = (owner.body.getPosition().y + body_offset.y) - Global.to_meters(get_sprite().getHeight() / 2);
		
		return new Vector2(position_x_meters, position_y_meters);
	}
	
	public float get_angle() {
		return owner.body.getAngle() + angle_offset;
	}
	
	public Vector2 get_body_offset() {
		return body_offset;
	}
	
	public void update() {
		Vector2 world_position = get_world_position();
		
		get_sprite().setPosition(Global.to_pixels(world_position.x), 
								 Global.to_pixels(world_position.y));
		get_sprite().setRotation(Global.to_degrees(get_angle()) - 90);
	}
	
	public void render() {
		get_sprite().draw(Game.batch);
	}
	
	public void apply_damage(int damage) {
		if (get_health() != INFINITE_HEALTH) {
			
			current_health -= damage;
			
			if (current_health <= 0)
			{
			//	Space.nuke_list.add(this);	
			}
		}
	}
	
	public void register_contact_begin(BodyFixture contact) {
		apply_damage(contact.get_contact_damage());
	}
	
	// Not right, need to involve speed of object being contacted.
	protected int get_contact_damage() {
		return (int)(get_mass() * owner.get_speed());
	}
	
	protected Sprite get_sprite() {
		return sprite;
	}

	protected Sprite sprite = null;
	public abstract int get_health();
	public abstract FixtureDef get_fixture_def();
	protected abstract float get_mass();
	
	protected Vector2 body_offset;
	protected SpaceBody owner;
	protected float angle_offset;
	
	protected Integer INFINITE_HEALTH = Integer.MIN_VALUE;
	protected Integer INSTANT_DEATH = Integer.MAX_VALUE;
	
	private Integer current_health;
}
