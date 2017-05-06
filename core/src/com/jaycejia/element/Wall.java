package com.jaycejia.element;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Created by congxiaoyao on 2016/5/31.
 */
public class Wall extends GameElement {

    public static final int TYPE_L =  0;
    public static final int TYPE_R =  1;
    public static final int TYPE_T = 3;
    public static final int TYPE_B = 4;

    private int type = -1;

    public Wall(Body body, Fixture fixture, int type) {
        super(body, fixture);
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
