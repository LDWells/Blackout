//LaDarion Wells
package Blackout2;

import edu.princeton.cs.introcs.StdDraw;


public class BlackoutWindow {
	
	private  boolean lightOn;	// indicates if the light is on (true) or off (false)
	private  int row;				// row position on the canvas. Indexed starting at 0
	private  int column;			// column position on the canvas. Indexed starting at 0
	
	// Constructor, first parameter says whether light should be initialized to be on or off
	// r and c indicate the location of the window in the 2D array (indexed starting at 0)
	public BlackoutWindow(boolean l, int r, int c) {
		lightOn = l;
		row = r;
		column = c;
	}
	
	// Draw the window, yellow if on, black if off
	// Assumes the scale on the StdDraw canvas is 1 unit per array element
	public void draw()
	{
		StdDraw.setPenColor(lightOn ? StdDraw.YELLOW : StdDraw.BLACK);
		StdDraw.filledRectangle(column+0.5,row+0.5, 0.5, 0.5);
	}
	
	// If the light in the window is on, turn it off. If it is off, turn it on
	// Note that this is actually a setter method (also known as a "mutator").
	public void toggle() {
		lightOn = !lightOn;
	}
	
	// getter method (also known as "accessor" method, which returns a boolean
	// indicating if the light is on in this window
	public boolean isLightOn()
	{
		if (lightOn) {
			return true;
		} else {
			return false;
		}
	}

}
