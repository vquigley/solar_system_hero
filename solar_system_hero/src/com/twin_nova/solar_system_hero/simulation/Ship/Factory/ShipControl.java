package com.twin_nova.solar_system_hero.simulation.Ship.Factory;

public abstract class ShipControl {
	protected State state;

	public abstract void update(Ship ship);
	public void set_state(State state) {this.state = state;}
	public State get_state() {return state;}
	
	public enum State {
		Disabled,
		Destroyed,
		ExitingPortal,
		AttackEarth,
		AttackHero,
		Patrol,
		GroupMind
	}
}
