package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.physics.box2d.*;

public class SpaceContact implements ContactListener {

	@Override
	public void beginContact(Contact arg0) {
		BodyFixture body_a = (BodyFixture)(arg0.getFixtureA().getUserData());
		BodyFixture body_b = (BodyFixture)(arg0.getFixtureB().getUserData());
		
		body_a.register_contact_begin(body_b);
		body_b.register_contact_begin(body_a);
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}
