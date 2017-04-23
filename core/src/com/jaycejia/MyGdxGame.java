package com.jaycejia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.jaycejia.utils.U;

import java.io.File;

public class MyGdxGame extends ApplicationAdapter {
    private WorldHolder worldHolder;
    private Box2DDebugRenderer renderer;
    private Matrix4 projMatrix;

    @Override
	public void create () {
        worldHolder = new WorldHolder();
        renderer = worldHolder.getRenderer();
        projMatrix = new OrthographicCamera(U.W_W, U.W_H).combined;
        projMatrix.translate(U.W_W / -2.0f, U.W_H / -2.0f, 0);
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyTyped(char character) {
                if (character == 27) {
                    System.exit(0);
                }
                return false;
            }
        });
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor( 0, 0, 0, 0 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
//		batch.begin();
//        batch.draw(img, 0, 0, 100, 100);
//        batch.end();
        worldHolder.step();
        renderer.render(worldHolder.getWorld(), projMatrix);
        projMatrix.translate(U.W_W / 2.0f, U.W_H / 2.0f, 0);
        projMatrix.rotate(0, 0, 1, 2);
        projMatrix.translate(U.W_W / -2.0f, U.W_H / -2.0f, 0);
        worldHolder.rotateGravity(-2);
    }
}
