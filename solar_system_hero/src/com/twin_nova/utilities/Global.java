package com.twin_nova.utilities;
import com.badlogic.gdx.math.Vector2;

public class Global {
	
	public enum Dir {
		Left (180),
		Right (0),
		Up (90),
		Down (270);
		
		private float angle;
		
		private Dir(float a) {
			angle = a;
		}
		
		public float angle() {
			return angle;
		}
	}
	
	public static TextureCache textures = new TextureCache();
	public static Vector2 target_resolution = new Vector2(640, 480);
	
	private static float pixels_per_metre = 60.0f;
	private static float degrees_per_radian = 57.2957795f;
	private static float step_delta = 1.0f / 30.0f;
			
	public static float to_meters(float pixels) {
		return (pixels / pixels_per_metre);
	}
	
	public static float to_pixels(float meters) {
		return (meters * pixels_per_metre);
	}
	
	public static float to_degrees(float radians) {
		return (radians * degrees_per_radian);
	}
	
	public static float pi(){
		return (float)Math.PI;
	}
	
	public static float density(float mass, float radius) {
		return (mass / (radius * radius * pi()));
	}
	
	public static float to_radians(float degrees) {
		return (degrees / degrees_per_radian);
	}

	public static float step_delta() {
		return step_delta;
	}
}
