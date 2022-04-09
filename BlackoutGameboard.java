//LaDarion Wells
package Blackout2;

import java.io.*;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class BlackoutGameboard {
	private BlackoutWindow[][] board;

	// Constructor, two integer arguments give the number of rows and column in the
	// board
	// each lights are initialized to on or off randomly (equal chances of
	public BlackoutGameboard(int r, int c) {
		// Create a new gameboard
		board = new BlackoutWindow[r][c];
		// fill board with windows. lights are initialized on/off randomly with equal
		// likelihood
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				board[i][j] = new BlackoutWindow(Math.random() > 0.5, i, j);
			}
		}
		// Initialize StdDraw
		StdDraw.setCanvasSize(500, 500);
		// Note: the number of columns determines the width in the x-direction
		// while the number of rows determines the with in the y-direction
		StdDraw.setXscale(0, c);
		StdDraw.setYscale(0, r);
	}

	public BlackoutGameboard(Scanner scanner) {
		int r, c;	// number of rows and columns
		String state;  // Store the state of an individual light. "O" means on, "X" means off

		// get the number of rows and columns from the input stream
		r = scanner.nextInt();
		c = scanner.nextInt();

		// Create a new gameboard
		board = new BlackoutWindow[r][c];
		// fill board with windows. Determine if they are on or off by reading from
		// the file
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// input the next word (which will actually be a single letter) from the input stream:
				/* 
				 * Error on state = next();
				 * Changed to state = scanner.next();
				 */
				state = scanner.next();
				/*
				 * Error with board[i,j] = new BlackoutWindow(state.equals("X"), i, j);
				 * change board[i,j] to board[i][j]
				 * also changed "X" to "O"
				 */
				
				board[i][j] = new BlackoutWindow(state.equals("O"), i, j);
			}
		}

		// Initialize StdDraw
		StdDraw.setCanvasSize(500, 500);
		// Note: the number of columns determines the width in the x-direction
		// while the number of rows determines the with in the y-direction
		StdDraw.setXscale(0, c);
		StdDraw.setYscale(0, r);

	}

	public void draw() {
		// Draw all windows in the 2D array. Note that this.board.length is the number
		// of rows
		// while this.board[0].length is the number of columns
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				// ask each window to draw itself
				board[i][j].draw();
			}
		}
		// Draw the lines between the windows:
		StdDraw.setPenColor(StdDraw.GRAY);

		// Draw the vertical lines, including ones at the left and right edge
		for (int i = 0; i < this.board[0].length + 1; i++) {
			StdDraw.line(i, 0, i, this.board.length);
		}
		// Draw the horizontal lines, including ones at the top and bottom
		for (int i = 0; i < this.board.length + 1; i++) {
			StdDraw.line(0, i, this.board[0].length, i);
		}

	}

	/*
	 * This method should be called when a mouse is clicked on a window. It changes
	 * the states of the light at that position and all neighboring lights (above,
	 * below, left and right). xPos and yPos give the position on the screen of the
	 * mouse click. Because of how we have set up the scale, we can turn the mouse
	 * position into row and column indices just by casting (rounding down) to an
	 * integer. Once we know the position, we toggle the state of the light (turn it
	 * off if it is on and turn it on if it is off), and also toggle the states of
	 * each neighbor above, below, left and right
	 */
	public void clickLight(double xPos, double yPos) {
		int r, c;
		c = (int) xPos;
		r = (int) yPos;
		// toggle this light:
		board[r][c].toggle();
		// toggle the light below:
		if (r - 1 >= 0)
			board[r - 1][c].toggle();
		// toggle the light above:
		if (r + 1 < board.length)
			board[r + 1][c].toggle();
		// toggle the light to the left:
		if (c - 1 >= 0)
			board[r][c - 1].toggle();
		// toggle the light to the right:
		if (c + 1 < board[0].length)
			board[r][c + 1].toggle();
	}

	// Return true if all lights are on, false otherwise
	public boolean allOn() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isLightOn()) {
					return (false);
				}
			}
		}
		return true;
	}

	// Return true if all lights are off, false otherwise
	public boolean allOff() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isLightOn()) {
					return (false);
				}
			}
		}
		return true;
	}

    // Output the game to a file.
	public boolean outputGame(PrintWriter p) {
		// output the number of rows and the number of columns
		p.println(this.board.length + " " + this.board[0].length);
		// Now output the state of each window. One row per line of text.
		// Note that we output the board from the top row down to the first row.
		for (int i = 0; i <= this.board.length - 1; i++) { //
			for (int j = 0; j < this.board[0].length; j++) {
				if (!board[i][j].isLightOn()) { //
					/*
					missing quotation mark around the O
					*/
					p.print("O");
				} else {
					p.print("X ");
			}
			}
			p.print("\n");
		}
		return false;
	}

	public void endMessage(String m) {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(this.board[0].length / 2.0, this.board.length / 2.0, m);
	}
}

