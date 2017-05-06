package com.jaycejia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.jaycejia.utils.U;

public class MyGdxGame extends ApplicationAdapter implements Freezer {
//    private Box2DDebugRenderer renderer;
//    private Matrix4 projMatrix;
    private MainStage mainStage;

    @Override
    public void create() {
        mainStage = new MainStage();
//        renderer = new Box2DDebugRenderer();
//        projMatrix = new OrthographicCamera(U.W_W, U.W_H).combined;
//        projMatrix.translate(U.W_W / -2.0f, U.W_H / -2.0f, 0);

    }

    @Override
    public void dispose() {
        super.dispose();
        mainStage.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (mainStage != null) mainStage.draw();
    }

    @Override
    public void addFood(long id, String name) {
        if (mainStage != null) mainStage.addFood(id, name);
    }

    @Override
    public void removeFood(long id) {
        if (mainStage != null) mainStage.removeFood(id);
    }

    @Override
    public void clearFood() {
        if (mainStage != null) mainStage.clear();
    }
}
