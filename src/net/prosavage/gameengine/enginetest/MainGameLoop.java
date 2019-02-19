package net.prosavage.gameengine.enginetest;

import net.prosavage.gameengine.renderengine.DisplayManager;
import net.prosavage.gameengine.renderengine.Loader;
import net.prosavage.gameengine.renderengine.RawModel;
import net.prosavage.gameengine.renderengine.Renderer;
import net.prosavage.gameengine.shaders.StaticShader;
import org.lwjgl.opengl.Display;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = {
                -0.5f, 0.5f, 0,   //V0
                -0.5f, -0.5f, 0,  //V1
                0.5f, -0.5f, 0,   //V2
                0.5f, 0.5f, 0     //V3
		};

		int[] indices = {
                0, 1, 3,  //Top left triangle (V0,V1,V3)
                3, 1, 2   //Bottom right triangle (V3,V1,V2)
		};

       RawModel model = loader.loadToVAO(vertices, indices);

       while (!Display.isCloseRequested()) {
          //game logic
			renderer.prepare();
          shader.start();
			renderer.render(model);
          shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

    }
}
