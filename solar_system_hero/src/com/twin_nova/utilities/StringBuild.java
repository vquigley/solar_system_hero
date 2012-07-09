package com.twin_nova.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;

public class StringBuild {
	
	public static Texture alphabet;
	public static TextureRegion chars[];
	public static char tag[];
	private static int columns;
	private static int rows;
	private static int cell_width;
	private static int cell_height;
	
	private String stringToWrite;
    private Vector2 position;
    private float width;
    private float height;
    private float incriment;
    
    private static final int CHAR_NOT_FOUND = -1;
    
    public StringBuild(Vector2 pos, String words)
    {
            position = pos;
            stringToWrite = words;
            incriment = 1;
            width = (cell_width) * incriment;
            height = (cell_height) * incriment;
            //resize();
    }
    
	public static void load_alphabet()
    {
        alphabet = new Texture(Gdx.files.internal("basic_char_map.png"));
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
        
        disect();
    }
	
	private static void disect()
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
	}
	
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
	
	public void render(SpriteBatch batch)
    {
		int char_index = 0;
		Vector2 next_char_position = new Vector2(position);
		
		while (char_index < stringToWrite.length())
		{
			char next_char = stringToWrite.charAt(char_index++);
			
			// Special chars
			switch (next_char) {
				case '\n':
				{
					next_char_position.add(0, -height);
					next_char_position.x = position.x;
					break;
				}
				case ' ':
				{
					next_char_position.add(width, 0);
					break;
				}
				default:
				{
					int num = CHAR_NOT_FOUND;
	                
	                for (int t = 0; t < tag.length; t++)
	                {
	                    if (next_char == tag[t])
	                    {
	                        num = t;
	                        break;
	                    }
	                }
	                
	                if (num != CHAR_NOT_FOUND)
	                {
	                	batch.draw(chars[num], next_char_position.x, next_char_position.y, width, height);
	                	next_char_position.add(width, 0);
	                }
	                else
	                {
	    				//Gdx.app.log(String.format("Error, char no found: %c", next_char), "");
	                }
	                
					break;
				}
			}
		}
    }
}
