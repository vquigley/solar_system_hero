package com.twin_nova.solar_system_hero.simulation;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class ContactFilter implements com.badlogic.gdx.physics.box2d.ContactFilter {

	// Taken from box2d manual.
	@Override
	public boolean shouldCollide(Fixture fixture_a, Fixture fixture_b) {
		Filter filter_a = fixture_a.getFilterData();
		Filter filter_b = fixture_b.getFilterData();

		if ((filter_a.groupIndex == filter_b.groupIndex) && (filter_a.groupIndex != 0)){
			return true;
		}		 

		return (((BodyFixture)(fixture_a.getUserData())).colides_with((BodyFixture)(fixture_b.getUserData())) &&
				((BodyFixture)(fixture_b.getUserData())).colides_with((BodyFixture)(fixture_a.getUserData())));
		
		/*short catA = fixture_a.getFilterData().categoryBits;
		short maskA = fixture_a.getFilterData().maskBits;
		short catB = fixture_b.getFilterData().categoryBits;
		short maskB = fixture_b.getFilterData().maskBits;

		if ((catA & maskB) != 0 && (catB & maskA) != 0)
		{
			return true;
		}
		
		return false;*/
	}

}
