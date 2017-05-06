package com.jaycejia.element;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jaycejia.utils.ImageFactory;
import com.jaycejia.utils.U;

/**
 * Created by congxiaoyao on 2017/5/5.
 */

public class FoodElement extends GameElement {

    private World world;
    private long foodId;
    private String foodName;
    private Sprite sprite;

    public FoodElement(Body body, Fixture fixture) {
        super(body, fixture);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public void destroy() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public FoodElement updatePosition() {
        Vector2 center = getCenterInScreen();
        sprite.setCenter(center.x, center.y);
        return this;
    }

    public void setPosition(Vector2 vector2) {
        body.setTransform(vector2, 0);
        sprite.setCenter(vector2.x, vector2.y);
    }

    public long getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public static Builder newBuilder(World world) {
        Builder builder = new Builder(world);
        return builder;
    }

    public static class Builder {

        CircleShape circleShape;
        BodyDef ballBodyDef;
        FixtureDef ballFixDef;
        World world;
        Sprite sprite;
        String name;
        long foodId;

        Builder(World world) {
            this.world = world;
            circleShape = new CircleShape();
            circleShape.setPosition(Vector2.Zero);
            circleShape.setRadius(U.World.FOOD_RADIUS);

            ballBodyDef = new BodyDef();
            ballBodyDef.type = BodyDef.BodyType.DynamicBody;

            ballFixDef = new FixtureDef();
            ballFixDef.friction = 0.8f;
            ballFixDef.restitution = 0.05f;
            ballFixDef.density = (float) (5f + Math.random());
            ballFixDef.shape = circleShape;
        }

        public Builder name(String name) {
            this.name = name;
            sprite = new Sprite(ImageFactory.getTextureByName(name));
            sprite.setSize(U.Screen.FOOD_DIAMETER, U.Screen.FOOD_DIAMETER);
            sprite.setScale(U.Screen.SCALE_X);
            return this;
        }

        public Builder foodId(long id) {
            this.foodId = id;
            return this;
        }

        public Builder positionInWorld(float x, float y) {
            ballBodyDef.position.set(x, y);
            return this;
        }

        public FoodElement build() {
            Body body = world.createBody(ballBodyDef);
            circleShape.dispose();
            FoodElement foodElement = new FoodElement(body, body.createFixture(ballFixDef));
            foodElement.world = this.world;
            foodElement.foodName = this.name;
            foodElement.sprite = this.sprite;
            foodElement.foodId = this.foodId;
            return foodElement;
        }
    }

}
