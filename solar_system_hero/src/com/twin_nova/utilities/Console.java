package com.twin_nova.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.solar_system_hero.simulation.Space;

public class Console {
	private static StringBuffer buffer = new StringBuffer();
	
	public static void write_line(String tag, Object output) {
		write_line(String.format("%s:%s", tag, output.toString()));
	}
	
	public static void write_line_f(String input, Object... args) {
		write_line(String.format(input, args));
	}
	
	public static void write_line(String input) {
		buffer.append(input);
		buffer.append("\n");
	}
	
	public static void flush_buffer(SpriteBatch batch) {
		Vector2 position = new Vector2(Space.instance().player().get_body().getPosition().x - (Gdx.graphics.getWidth() / 2),
				Space.instance().player().get_body().getPosition().y + (Gdx.graphics.getHeight() / 2));
		StringBuild sb = new StringBuild(position, buffer.toString());
		sb.render(batch);
		buffer.delete(0, buffer.length());
	}
}
