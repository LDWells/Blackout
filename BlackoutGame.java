//LaDarion Wells
package Blackout2;

import edu.princeton.cs.introcs.StdDraw;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileOutputStream;

import edu.princeton.cs.introcs.StdDraw;

public class BlackoutGame {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		boolean userQuits= false;
		String filename;
		BlackoutGameboard bg=null; // will be used to create grid	
		char inputChar = ' ';
		FileOutputStream fileOutput = null;
		PrintWriter outputStream = null;
		
		
		System.out.println("Please enter filename of stored game:");
		filename = keyboard.next();
		try {
			// creates a board with predefined grid if that files found
			bg = new BlackoutGameboard(new Scanner(new FileInputStream(filename + ".txt"))); // can just type in filename without .txt
		}
		catch (FileNotFoundException e) {
			// creates a 5 by 5 grid if file's not found
			System.out.println("File " + filename + " not found, initializing 5 by 5 grid randomly.");
			bg = new BlackoutGameboard(5,5);
		}
		bg.draw();
		
		System.out.println("Game starting. Press q to quit. Press s to save game.");

		StdDraw.enableDoubleBuffering();
		while (!bg.allOff() && !bg.allOn() &&!userQuits) {
			if (StdDraw.isMousePressed()) {
				bg.clickLight(StdDraw.mouseX(),StdDraw.mouseY());
				bg.draw();
			}
			if (StdDraw.hasNextKeyTyped()) {
				inputChar = StdDraw.nextKeyTyped();
				if (inputChar=='q' || inputChar=='s') {
					userQuits = true;
				}
			}		

			StdDraw.show();
			StdDraw.pause(150);
		}
		// The game is over: give a message to the user
		if (bg.allOff()) {
			bg.endMessage("Blackout! You win! All the lights are off."); 
		} else if (bg.allOn()) {
			bg.endMessage("You lose! All the lightd are on!");
		} else if (inputChar == 'q') {
			bg.endMessage("Game is over because you quit!");
		} else if (inputChar == 's') {
			System.out.println("Enter file name where you'd like to store the game:");
			filename = keyboard.next();
			try {
				outputStream = new PrintWriter(new FileOutputStream(filename));
				System.out.println("Game saved.");

				outputStream.println();
				outputStream.flush();
			}
			catch (FileNotFoundException e) {
				System.out.println("Unable to save game to given file.");
			}
		}
		StdDraw.show();	
		
		outputStream.print(bg.outputGame(outputStream));
		outputStream.flush();

	}
}
