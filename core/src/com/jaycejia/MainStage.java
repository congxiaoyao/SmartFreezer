package com.jaycejia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jaycejia.element.FoodElement;
import com.jaycejia.element.Wall;
import com.jaycejia.utils.ImageFactory;
import com.jaycejia.utils.U;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by congxiaoyao on 2016/5/31.
 */
public class MainStage extends Stage implements Freezer{

    private static final int GRAVITY_MOD = 10;

    private ReentrantLock lock;
    private Queue<Runnable> events;

    private Texture background;

    private Batch batch;
    private World world;
    private Vector2 force;
    private List<FoodElement> foods;
    private Wall[] walls;

    public MainStage() {
        super();
        batch = getBatch();
        force = new Vector2(0, -10);
        world = new World(force, false);

        ImageFactory.initAll();
        background = ImageFactory.getTextureByName(U.Image.BACKGROUND);

        foods = new ArrayList<FoodElement>();
        initWalls();

        events = new LinkedList<Runnable>();
        lock = new ReentrantLock(false);
    }

    @Override
    public void addFood(final long id, final String name) {
        Vector2 centerWorld = U.World.CENTER_WORLD;
        final FoodElement foodElement = FoodElement.newBuilder(world)
                .name(name)
                .foodId(id)
                .positionInWorld((float) (centerWorld.x + U.W_W / 4
                                - (Math.random() * U.W_W / 2)),
                        centerWorld.y + U.W_H / 4)
                .build();

        sendMessage(new Runnable() {
            @Override
            public void run() {
                foods.add(foodElement);
            }
        });
    }

    @Override
    public void removeFood(final long id) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Iterator<FoodElement> iterator = foods.iterator();
                while (iterator.hasNext()) {
                    FoodElement foodElement = iterator.next();
                    if (foodElement.getFoodId() == id) {
                        foodElement.destroy();
                        iterator.remove();
                        return;
                    }
                }
            }
        };
        sendMessage(runnable);
    }

    @Override
    public void clearFood() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (FoodElement food : foods) {
                    food.destroy();
                }
                foods.clear();
            }
        };
        sendMessage(runnable);
    }

    @Override
    public void draw() {
        if (events.size() != 0) {
            lock.lock();
            try{
                for (Runnable event : events) {
                    event.run();
                }
                events.clear();
            }finally {
                lock.unlock();
            }
        }
        force.set(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY())
                .scl(-4f);
//        world.setGravity(force);
        world.step(1.0f / 60, 6, 2);
        batch.begin();
        batch.draw(background, 0, 0, U.S_W, U.S_H);
        for (FoodElement food : foods) {
            food.getBody().applyForceToCenter(force, false);
            food.updatePosition().draw(batch);
        }
        batch.end();
    }

    /**
     * 初始化四面墙
     */
    private void initWalls() {
        PolygonShape wallShape = new PolygonShape();
        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.type = BodyDef.BodyType.StaticBody;
        wallBodyDef.position.set(0, 0);
        FixtureDef wallFixDef = new FixtureDef();
        wallFixDef.density = 0;
        wallFixDef.restitution = 0.05f;
        wallFixDef.friction = 0.8f;
        wallFixDef.shape = wallShape;

        walls = new Wall[4];

        //定义四面墙
        Body tempBody = createWallBody(U.World.WALL_LEFT_CENTER, wallBodyDef);
        walls[0] = new Wall(tempBody, createVerticalWallFixture(tempBody, wallShape,
                wallFixDef), Wall.TYPE_L);
        tempBody = createWallBody(U.World.WALL_RIGHT_CENTER, wallBodyDef);
        walls[1] = new Wall(tempBody, createVerticalWallFixture(tempBody, wallShape,
                wallFixDef), Wall.TYPE_R);
        tempBody = createWallBody(U.World.WALL_TOP_CENTER, wallBodyDef);
        walls[2] = new Wall(tempBody, createWallHorizontalFixture(tempBody, wallShape,
                wallFixDef), Wall.TYPE_T);
        tempBody = createWallBody(U.World.WALL_BOTTOM_CENTER, wallBodyDef);
        walls[3] = new Wall(tempBody, createWallHorizontalFixture(tempBody, wallShape,
                wallFixDef), Wall.TYPE_B);

        wallShape.dispose();
    }

    private Body createWallBody(Vector2 center, BodyDef wallBodyDef) {
        wallBodyDef.position.set(center);
        return world.createBody(wallBodyDef);
    }

    private Fixture createVerticalWallFixture(Body wallBody, PolygonShape wallShape, FixtureDef wallFixDef) {
        wallShape.setAsBox(U.World.WALL_VER_HALF_WIDTH,
                U.World.WALL_VER_HALF_HEIGHT);
        return wallBody.createFixture(wallFixDef);
    }

    private Fixture createWallHorizontalFixture(Body wallBody, PolygonShape wallShape, FixtureDef wallFixDef) {
        wallShape.setAsBox(U.World.WALL_HOR_HALF_WIDTH,
                U.World.WALL_HOR_HALF_HEIGHT);
        return wallBody.createFixture(wallFixDef);
    }

    private void sendMessage(Runnable runnable) {
        lock.lock();
        try {
            events.add(runnable);
        }finally {
            lock.unlock();
        }
    }

    public World getWorld() {
        return world;
    }
}