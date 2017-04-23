package com.jaycejia;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.jaycejia.element.Wall;
import com.jaycejia.utils.U;

/**
 * Created by congxiaoyao on 2016/5/31.
 */
public class WorldHolder{

    private Vector2 gravity;
    private World world;

    private Wall[] walls = new Wall[4];

    private BodyDef wallBodyDef;
    private BodyDef ballBodyDef;

    private FixtureDef wallFixDef;
    private FixtureDef ballFixDef;

    private PolygonShape wallShape;
    private CircleShape circleShape;

    public WorldHolder() {
        gravity = new Vector2(0, -30);
        world = new World(gravity, true);
        initWalls();
        initBalls();
        circleShape.dispose();
        wallShape.dispose();
    }

    /**
     * 初始化PlayerN PlayerS Hockey
     */
    private void initBalls() {
        ballBodyDef = new BodyDef();
        ballBodyDef.type = BodyDef.BodyType.DynamicBody;

        ballFixDef = new FixtureDef();
        ballFixDef.friction = 0.4f;
        ballFixDef.restitution = 0.8f;
        ballFixDef.density = 0.5f;

        circleShape = new CircleShape();
        circleShape.setPosition(Vector2.Zero);
        ballFixDef.shape = circleShape;

        //定义hockey
        for (int i = 0; i < 10; i++) {
            circleShape.setRadius(U.World.HOCKEY_RADIUS);
            ballBodyDef.position.set((float) (Math.random() * 5f + 1),
                    (float) (Math.random() * 5f + 3f));
            ballBodyDef.linearDamping = 0.6f;
            ballBodyDef.angularDamping = 0.6f;
            Body tempBody = world.createBody(ballBodyDef);
            tempBody.createFixture(ballFixDef);
        }
    }

    /**
     * 初始化六面墙
     */
    private void initWalls() {

        wallShape = new PolygonShape();
        wallBodyDef = new BodyDef();
        wallBodyDef.type = BodyDef.BodyType.StaticBody;
        wallBodyDef.position.set(0, 0);
        wallFixDef = new FixtureDef();
        wallFixDef.density = 0;
        wallFixDef.restitution = 0f;
        wallFixDef.friction = 0f;
        wallFixDef.shape = wallShape;

        //定义四面墙
        Body tempBody = createWallBody(U.World.WALL_LEFT_CENTER);
        walls[0] = new Wall(tempBody, createVerticalWallFixture(tempBody), Wall.TYPE_L);
        tempBody = createWallBody(U.World.WALL_RIGHT_CENTER);
        walls[1] = new Wall(tempBody, createVerticalWallFixture(tempBody), Wall.TYPE_R);
        tempBody = createWallBody(U.World.WALL_TOP_CENTER);
        walls[2] = new Wall(tempBody, createWallHorizontalFixture(tempBody), Wall.TYPE_TR);
        tempBody = createWallBody(U.World.WALL_BOTTOM_CENTER);
        walls[3] = new Wall(tempBody, createWallHorizontalFixture(tempBody), Wall.TYPE_BL);
    }

    private Body createWallBody(Vector2 center) {
        wallBodyDef.position.set(center);
        return world.createBody(wallBodyDef);
    }

    private Fixture createVerticalWallFixture(Body wallBody) {
        wallShape.setAsBox(U.World.WALL_VER_HALF_WIDTH,
                U.World.WALL_VER_HALF_HEIGHT);
        return wallBody.createFixture(wallFixDef);
    }

    private Fixture createWallHorizontalFixture(Body wallBody) {
        wallShape.setAsBox(U.World.WALL_HOR_HALF_WIDTH,
                U.World.WALL_HOR_HALF_HEIGHT);
        return wallBody.createFixture(wallFixDef);
    }

    public void step() {
        world.step(1.0f / 60, 6, 2);
    }

    public Box2DDebugRenderer getRenderer() {
        return new Box2DDebugRenderer();
    }

    public World getWorld() {
        return world;
    }


    public void rotateGravity(float degree) {
        gravity.rotate(degree);
        world.setGravity(gravity);
    }

    public InputProcessor getInputProcessor() {
        return new InputAdapter(){

        };
    }

    public interface CollisionListener{

        /**
         * 碰撞发生时候的回调
         *
         * @param point 碰撞点
         */
        void onCollision(Vector2 point);
    }

    public interface CrashWallListener{

        /**
         * 球类碰到墙的回调
         * @param velocity
         */
        void onCrash(Vector2 velocity);
    }
}