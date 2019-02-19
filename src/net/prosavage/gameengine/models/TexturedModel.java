package net.prosavage.gameengine.models;

import net.prosavage.gameengine.textures.ModelTexture;

public class TexturedModel {


	private RawModel rawModel;
	private ModelTexture texture;

	public TexturedModel(RawModel model, ModelTexture texture) {
		this.rawModel = model;
		this.texture = texture;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}
}
