package net.prosavage.gameengine.renderengine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

	// Store all of these for cleanup later.
	private List<Integer> vaos = new ArrayList<>();
	private List<Integer> vbos = new ArrayList<>();


	/**
	 * Loads a VAO.
	 *
	 * @param positions - positions of vao.
	 * @return - RawModel Object for usage.
	 */
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, positions);
		unbindVAO();
		// Divided by 3 cause each vertex has 3 floats, an X, Y, and Z.
		// There are 3 because models are triangles.
		return new RawModel(vaoID, positions.length / 3);
	}


	/**
	 * Cleans the game on shutdown.
	 */
	public void cleanUp() {
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}


	private int createVAO() {
		// This line generates an empty vao.
		int vaoID = GL30.glGenVertexArrays();
		// This activates vao by binding it.
		GL30.glBindVertexArray(vaoID);
		// store these for removal
		vaos.add(vaoID);
		return vaoID;
	}

	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		// Creates new VBO
		int vboID = GL15.glGenBuffers();
		// Store these for removal
		vbos.add(vboID);
		// Bind so that we can store data into it!
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		// You need
		// number of the attribute list
		// length of each vertex as they are 3D Vectors
		// The type of data: float
		// is data normalized
		// distance between each vertices
		// should it start at beginning of data.
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private void unbindVAO() {
		// Unbinds currently bound vao
		GL30.glBindVertexArray(0);
	}

	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		// flip: basically means to change modes, so it goes from preparing to be
		// read from to preparing to be written to.
		buffer.flip();
		return buffer;
	}


}
