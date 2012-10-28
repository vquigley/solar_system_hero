 package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Transform;
import com.twin_nova.solar_system_hero.screens.Game;
import com.twin_nova.utilities.Global;
import com.twin_nova.utilities.TextureCache.Texture;

public abstract class BodyFixture {

	protected Integer INSTANT_DEATH = Integer.MAX_VALUE;
	protected BodyFixture(SpaceBody owner,
						  Texture texture,
						  Vector2 body_offset, 
						  float angle_offset) {
		this.setOwner(owner);
		this.body_offset = body_offset;
		this.angle_offset = angle_offset;
		
		FixtureDef fixture_def = get_fixture_def();
		fixture_def.filter.categoryBits = owner.get_weapon_category();
		fixture_def.filter.maskBits = owner.get_weapon_mask();
		set_fixture(owner.get_body().createFixture(fixture_def));
		get_fixture().setUserData(this);
		
		if (texture != null) {
			sprite = new Sprite(Global.textures.get(texture), 
								getSpriteOriginX(), 
								getSpriteOriginY(),
								getSpriteBoundWidth(), 
								getSpriteBoundHeight());
						
			sprite.setOrigin(getSpriteRenderedWidth() / 2, 
							 getSpriteRenderedHeight() / 2);

			sprite.setSize(getSpriteRenderedWidth(), 
						   getSpriteRenderedHeight());
		}
	}
	
	protected float getRadius()
	{
		return (getSpriteRenderedHeight() / 2);
	}
	
	protected abstract int getSpriteBoundHeight();

	protected abstract int getSpriteBoundWidth();

	protected int getSpriteOriginX() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int getSpriteOriginY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected float getSpriteRenderedWidth() {
		return getSpriteBoundWidth() * owner.getScaleFactor();
	}

	protected float getSpriteRenderedHeight() {
		return getSpriteBoundHeight() * owner.getScaleFactor();
	}

	public Vector2 get_world_center() {
		Vector2 world_position = body_offset.cpy();

		Transform transform = getOwner().body.getTransform();
		transform.mul(world_position);
		
		world_position.x -=  Global.to_meters(getSpriteRenderedWidth() / 2);
		world_position.y -=  Global.to_meters(getSpriteRenderedHeight() / 2);
	
		return world_position;
	}
	
	public float get_width() {
		return get_sprite().getWidth();
	}
	
	public float get_height() {
		return get_sprite().getHeight();
	}
	
	public float get_angle() {
		return getOwner().body.getAngle() + angle_offset;
	}
	
	public Vector2 get_body_offset() {
		return body_offset;
	}
	
	public void update() {
		if (get_sprite() != null) {
			Vector2 world_position = get_world_center();
			
			get_sprite().setPosition(Global.to_pixels(world_position.x), 
									 Global.to_pixels(world_position.y));
			get_sprite().setRotation(Global.to_degrees(get_angle()));
		}
	}
	
	
	
	public void render() {
		get_sprite().draw(Game.batch);
	}
	
	public void apply_damage(int damage) {
		owner.apply_damage(damage);
	}
	
	protected void startDestroy() {		
	}

	public void register_contact_begin(BodyFixture contact) {
		apply_damage(contact.get_contact_damage());
	}
	
	// Not right, need to involve speed of object being contacted.
	protected int get_contact_damage() {
		return (int)(get_mass() * getOwner().get_speed());
	}
	
	protected Sprite get_sprite() {
		return sprite;
	}

	protected Sprite sprite = null;
	public abstract FixtureDef get_fixture_def();
	public abstract float get_mass();
	
	protected Vector2 body_offset;
	protected SpaceBody owner;
	protected float angle_offset;
	
	private Fixture fixture = null;
	
	public boolean colides_with(BodyFixture bodyFixture) {
		return true;		
	}

	public SpaceBody getOwner() {
		return owner;
	}

	public void setOwner(SpaceBody owner) {
		this.owner = owner;
	}
	
	/**
	 * @return the fixture
	 */
	public Fixture get_fixture() {
		return fixture;
	}

	/**
	 * @param fixture the fixture to set
	 */
	public void set_fixture(Fixture fixture) {
		this.fixture = fixture;
	}	
}
