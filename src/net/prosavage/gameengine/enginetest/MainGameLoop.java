package net.prosavage.gameengine.enginetest;

import net.prosavage.gameengine.renderengine.DisplayManager;
import net.prosavage.gameengine.renderengine.Loader;
import net.prosavage.gameengine.renderengine.RawModel;
import net.prosavage.gameengine.renderengine.Renderer;
import org.lwjgl.opengl.Display;

public class MainGameLoop {


	/**
	 * Loads up the position data for two triangles (which together make a quad)
	 * into a VAO. This VAO is then rendered to the screen every frame.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();

		float[] vertices = {
				  // Left bottom triangle
				  -0.5f, 0.5f, 0f,
				  -0.5f, -0.5f, 0f,
				  0.5f, -0.5f, 0f,
				  // Right top triangle
				  0.5f, -0.5f, 0f,
				  0.5f, 0.5f, 0f,
				  -0.5f, 0.5f, 0f
		};

		int[] indices = {
				  0, 1, 3,
				  3, 1, 2
		};

		RawModel model = loader.loadToVAO(vertices, indices);

		while (!Display.isCloseRequested()) {
			// game logic
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}