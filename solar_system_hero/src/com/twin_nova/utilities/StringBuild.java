package com.twin_nova.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.twin_nova.utilities.TextureCache.Texture;

public class StringBuild {
	
	//public static Texture alphabet;
	public static TextureRegion chars[];
	public static char tag[];
	private static int columns;
	private static int rows;
	private static int cell_width = 131;
	private static int cell_height = 238;
	private static int char_distance = 5;
	
	private String stringToWrite;
    private Vector2 position;
    private float width;
    private float height;
    private float incriment;
	private EDisplayType display_type = EDisplayType.ENormal;
	private double display_type_duration;
    
    private static final int CHAR_NOT_FOUND = -1;
    
    private static Sprite digit_0;
    private static Sprite digit_1;
    private static Sprite digit_2;
    private static Sprite digit_3;
    private static Sprite digit_4;
    private static Sprite digit_5;
    private static Sprite digit_6;
    private static Sprite digit_7;
    
    private static int t = 1;
    private static int tl = 2;
    private static int tr = 4;
    private static int m = 8;
    private static int bl = 16;
    private static int br = 32;
    private static int b = 64;
    
    public static int random_segment = 0x1000;
    
    private static float char_scale = 0.1f;
    
    private static int all = t + tl + tr + m + bl + br + b;
    
    static HashMap<Character, Integer> char_hash = new HashMap<Character, Integer>();
    
    public StringBuild(Vector2 pos, String words)
    {
            position = pos;
            stringToWrite = words;
            incriment = 1;
            width = (cell_width) * incriment;
            height = (cell_height) * incriment;
            //resize();
            
            load_alphabet();
            
            digit_0 = new Sprite(Global.textures.get(Texture.digit_0), cell_width, cell_height);
            digit_1 = new Sprite(Global.textures.get(Texture.digit_1), cell_width, cell_height);
            digit_2 = new Sprite(Global.textures.get(Texture.digit_2), cell_width, cell_height);
            digit_3 = new Sprite(Global.textures.get(Texture.digit_3), cell_width, cell_height);
            digit_4 = new Sprite(Global.textures.get(Texture.digit_4), cell_width, cell_height);
            digit_5 = new Sprite(Global.textures.get(Texture.digit_5), cell_width, cell_height);
            digit_6 = new Sprite(Global.textures.get(Texture.digit_6), cell_width, cell_height);
            digit_7 = new Sprite(Global.textures.get(Texture.digit_7), cell_width, cell_height);
    }
    
	public static void load_alphabet()
    {
      /*  alphabet = new Texture(Gdx.files.internal("basic_char_map.png"));
        tag = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        				 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
        				 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
        				 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', 
        				 '5', '6', '7', '8', '9', '0', ':', ';', '+', '-', '=', '*', '/', '(', 
        				 ')', '"', '\'', '?', '!', '.'};
        
        chars = new TextureRegion[tag.length];
        columns = 9;
        rows = 12;
        
        cell_width = alphabet.getWidth() / rows;
		cell_height = alphabet.getHeight() / columns;
        
        disect();*/

		char_hash.put(' ', 0);
		
		char_hash.put('a', all ^ b);
		char_hash.put('A', char_hash.get('a'));
		
		char_hash.put('b', all ^ (t | tr));
		char_hash.put('B', char_hash.get('b'));
		
		char_hash.put('c', m | bl | b);
		char_hash.put('C', t | tl | bl | b);
		
		char_hash.put('d', all ^ (t | tl));
		char_hash.put('D', char_hash.get('d'));

		char_hash.put('E', all ^ (tr | br));
		char_hash.put('e', char_hash.get('E'));
		
		char_hash.put('F', all ^ (tr | br | b));
		char_hash.put('f', char_hash.get('F'));
		
		char_hash.put('g', all ^ bl);
		char_hash.put('G', char_hash.get('g'));
		
		char_hash.put('h', all ^ (b | tr | t));
		char_hash.put('H', all ^ (t | b));
		
		char_hash.put('i', br);
		char_hash.put('I', char_hash.get('i'));
		
		char_hash.put('j', tr | br | b);
		char_hash.put('J', tr | br | b | bl);
		
		char_hash.put('k', all);
		char_hash.put('K', all);
		
		char_hash.put('l', tr | br);
		char_hash.put('L', tl | bl | b);
		
		char_hash.put('m', bl | m | br);
		char_hash.put('M', bl | m | br);
		
		char_hash.put('n', bl | m | br);
		char_hash.put('N', bl | m | br);
		
		char_hash.put('o', bl | m | br | b);
		char_hash.put('O', all ^ m);
		
		char_hash.put('p', all ^ (br | b));
		char_hash.put('P', all ^ (br | b));
		
		char_hash.put('q', all ^ (bl | b));
		char_hash.put('Q', all ^ (bl | b));
		
		char_hash.put('r', bl | m);
		char_hash.put('R', bl | m);
		
		char_hash.put('s', all ^ (tr | bl));
		char_hash.put('S', all ^ (tr | bl));
		
		char_hash.put('t', all ^ (t | tr | br));
		char_hash.put('T', all ^ (t | tr | br));
		
		char_hash.put('u', bl | b | br);
		char_hash.put('U', all ^ (t | m));
		
		char_hash.put('v', bl | b | br);
		char_hash.put('V', all ^ (t | m));
		
		char_hash.put('w', all ^ (t | m));
		char_hash.put('W', all ^ (t | m));
		
		char_hash.put('x', all);
		char_hash.put('X', all);
		
		char_hash.put('y', all ^ (bl | t));
		char_hash.put('Y', all ^ (bl | t));
		
		char_hash.put('z', all ^ (tl | br));
		char_hash.put('Z', all ^ (tl | br));
		
		char_hash.put('0', all ^ m);
		char_hash.put('1', tr | br);		
		char_hash.put('2', all ^ (tl | br) );
		char_hash.put('3', all ^ (tl | bl));		
		char_hash.put('4', all ^ (t | bl | b));
		char_hash.put('5', all ^ (tr | bl));		
		char_hash.put('6', all ^ (t | tr));
		char_hash.put('7', t | tr | br);		
		char_hash.put('8', all);
		char_hash.put('9', all ^ (bl | b));		
		char_hash.put('0', all ^ m);
		
		/*, 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
		 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
		 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', 
		 '5', '6', '7', '8', '9', '0', ':', ';', '+', '-', '=', '*', '/', '(', 
		 ')', '"', '\'', '?', '!', '.'
		char_hash */
    }
	
	public void each_letter_random_for(double seconds) {
		display_type = EDisplayType.ERandomEntry;
		display_type_duration = seconds;
	}
	
	public enum EDisplayType
	{
		ERandomEntry,
		ENormal
	}
	
/*	private static void disect()
	{	
		int char_index = 0;
		
		for (int y_pos = 0; y_pos < columns; ++y_pos)
		{
			for (int x_pos = 0; x_pos < rows; ++x_pos)
			{
				chars[char_index++] = new TextureRegion(alphabet, 
														x_pos * cell_width, 
														y_pos * cell_height, 
														cell_width, 
														cell_height);
				
				if (char_index >= chars.length)
				{
					break;
				}
			}
			
			if (char_index >= chars.length)
			{
				break;
			}
		}
	}*/
	
	/*private void resize()
    {
        float x = Gdx.graphics.getWidth();
        float y = Gdx.graphics.getHeight();
        
        float changeX = x / Global.target_resolution.x;
        float changeY = y / Global.target_resolution.y;
        
        width = (cell_width * changeX) * incriment;
        height = (cell_height * changeY) * incriment;
        
        position = new Vector2(position.x * changeX, position.y * changeY);
    }*/
	
	private Date last_render_change = null;
	private Integer next_displayed_char = 0;
	
	public void render(SpriteBatch batch)
    {
		if (last_render_change == null)
		{
			last_render_change = new Date();
		}
		
		
		Integer char_index = 0;
		Vector2 next_char_position = new Vector2(position);
		
		while (char_index < stringToWrite.length())
		{
			Date current_render_time = new Date();
			char next_char = stringToWrite.charAt(char_index);
			
			if ((display_type == EDisplayType.ERandomEntry) &&
				(next_char != '\n') &&
				(next_displayed_char <= char_index) &&
				(current_render_time.getTime() - last_render_change.getTime()) < (this.display_type_duration * 1000))
			{
				next_char = '\r';
			}
			else if (next_displayed_char == char_index)
			{				
				next_displayed_char++;				
				last_render_change = current_render_time; 
			}
			
			++char_index;
			
			// Special chars
			switch (next_char) {
				case '\n':
				{
					next_char_position.add(0, - (height * char_scale +  char_distance));
					next_char_position.x = position.x;
					break;
				}
				case '\r' :
				{
					Integer num = 0;
					switch (rg.nextInt(8)) {
						case 1: {
							num = t;
							break;
						}
						case 2: {
							num = tl;
							break;
						}
						case 3: {
							num = tr;
							break;
						}
						case 4: {
							num = m;
							break;
						}
						case 5: {
							num = bl;
							break;
						}
						case 6: {
							num = br;
							break;
						}
						case 7: {
							num = b;
							break;
						}
					}
					
					draw_char(batch, next_char_position, num);
				
					break;
				}
				//case ' ':
				//{
				//	next_char_position.add(width, 0);
				//	break;
				//}
				default:
				{
	                Integer num = char_hash.get(next_char);
	                
	                if (num != null)
	                {
	                	draw_char(batch, next_char_position, num);
	                }
	                else
	                {
	    				Gdx.app.log(String.format("Error, char no found: %c", next_char), "");
	                }
	                
					break;
				}
			}
		}
    }
	
	Random rg = new Random();
	private void draw_char(SpriteBatch batch, Vector2 next_char_position, Integer num) {
		ArrayList<Sprite> sprites_to_draw = new ArrayList<Sprite>();
    	
    	sprites_to_draw.add(digit_0);
    	
    	if ((num & t) != 0) {
    		sprites_to_draw.add(digit_1);
    	}
    	
    	if ((num & tl) != 0) {
    		sprites_to_draw.add(digit_2);
    	}
    	
    	if ((num & tr) != 0) {
    		sprites_to_draw.add(digit_3);
    	}
    	
    	if ((num & m) != 0) {
    		sprites_to_draw.add(digit_4);
    	}
    	
    	if ((num & bl) != 0) {
    		sprites_to_draw.add(digit_5);
    	}
    	
    	if ((num & br) != 0) {
    		sprites_to_draw.add(digit_6);
    	}
    	
    	if ((num & b) != 0) {
    		sprites_to_draw.add(digit_7);
    	}
    	
    	for (Sprite sprite : sprites_to_draw) {
    		batch.draw(sprite, next_char_position.x, next_char_position.y, width * char_scale, height * char_scale);
    	}
    	
    	next_char_position.add(width * char_scale + char_distance, 0);
	}
}
