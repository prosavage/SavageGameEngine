package net.prosavage.gameengine.enginetest;

import net.prosavage.gameengine.renderengine.DisplayManager;
import org.lwjgl.opengl.Display;

public class MainGameLoop {


	public static void main(String[] args) {
		// Creates display
		DisplayManager.createDisplay();

		// This will run till the user wants to close the game.
		while (!Display.isCloseRequested()) {
			// game logic
			//render
			DisplayManager.updateDisplay();
		}

		DisplayManager.closeDisplay();
	}

}
