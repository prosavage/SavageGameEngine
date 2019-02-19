package net.prosavage.gameengine.shaders;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/net/prosavage/gameengine/shaders/vertexShader.glsl";
	private static final String FLAGMENT_FILE = "src/net/prosavage/gameengine/shaders/fragmentShader.glsl";

	public StaticShader() {
		super(VERTEX_FILE, FLAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
}
