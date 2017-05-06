package com.jaycejia.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by congxiaoyao on 2016/5/27.
 */
public class U {

    private static final String ASSETS_BASE = "";

    private static final Vector2 mouse = new Vector2();
    //屏幕宽度
    public static final float S_W = Gdx.graphics.getWidth();
    //屏幕高度
    public static final float S_H = Gdx.graphics.getHeight();
    static {
        System.out.println("cxy " + S_H);
    }
    //屏幕或世界的高宽比
    public static final float WH_RADIO = S_H / S_W;
    //物理世界的宽度（米）
    public static final float W_W = 2.7f;

    //物理世界的高度（米）
    public static final float W_H = W_W * WH_RADIO;
    //屏幕像素数与物理世界的大小的比
    public static final float SW_RADIO = S_W / W_W;

    //屏幕宽度与标准屏幕的比例
    public static final float STD_HOR_RADIO = S_W / 1080;

    //屏幕高度与标准屏幕的比例
    public static final float STD_VER_RADIO = S_H / 1589;

    /**
     * 在屏幕上的各个物体显示出来的宽高及位置等常量
     */
    public static final class Screen {
        //食物直径
        public static final float FOOD_DIAMETER = byStdHor(160);

        //屏幕中心
        public static final Vector2 CENTER_SCREEN = new Vector2(S_W / 2, S_H / 2);

        public static final float SCALE_X = S_W / 1080;
        public static final float SCALE_Y = S_H / 1589;
        public static final float SCALE = SCALE_X;
    }

    /**
     * 在物理世界中各个物体的位置尺寸参数
     */
    public static final class World {

        //左边的墙的中心点
        public static final Vector2 WALL_LEFT_CENTER =
                new Vector2(toWorld(byStdHor(110)), W_H / 2);
        //右边的墙的中心点
        public static final Vector2 WALL_RIGHT_CENTER =
                new Vector2(toWorld(byStdHor(U.S_W - 110f)), W_H / 2);
        //竖直墙半高
        public static final float WALL_VER_HALF_HEIGHT = U.W_H / 2;
        //竖直墙半宽
        public static final float WALL_VER_HALF_WIDTH = toWorld(byStdHor(20f));
        //上墙的中心点
        public static final Vector2 WALL_TOP_CENTER =
                new Vector2(W_W / 2, toWorld(byStdVer(U.S_H - 90f)));
        //下墙的中心点
        public static final Vector2 WALL_BOTTOM_CENTER =
                new Vector2(W_W / 2, toWorld(byStdVer(210f)));
        //水平墙半高
        public static final float WALL_HOR_HALF_HEIGHT = toWorld(byStdVer(35f));

        //水平墙半宽
        public static final float WALL_HOR_HALF_WIDTH = U.W_W / 2;
        //食物的半径
        public static final float FOOD_RADIUS = toWorld(Screen.FOOD_DIAMETER / 2);

        //世界中心
        public static final Vector2 CENTER_WORLD = toWorld(Screen.CENTER_SCREEN.cpy());
    }

    public static final class Image {

        public static final String BADLOGIC = ASSETS_BASE + "badlogic.jpg";
        public static final String BACKGROUND = ASSETS_BASE + "icon_freezer_bg.png";
        public static final String NONE = ASSETS_BASE + "icon_circle_none.png";
        public static final String CABBAGE = ASSETS_BASE + "icon_circle_cabbage.png";
        public static final String CHIPS = ASSETS_BASE + "icon_circle_chips.png";
        public static final String CUCUMBER = ASSETS_BASE + "icon_circle_cucumber.png";
        public static final String DRUMSTICK = ASSETS_BASE + "icon_circle_drumstick.png";
        public static final String EGG = ASSETS_BASE + "icon_circle_egg.png";
        public static final String EGGPLANT = ASSETS_BASE + "icon_circle_eggplant.png";
        public static final String MAIZE = ASSETS_BASE + "icon_circle_maize.png";
        public static final String PORK = ASSETS_BASE + "icon_circle_pork.png";
        public static final String SAUSAGE = ASSETS_BASE + "icon_circle_sausage.png";
        public static final String STEAK = ASSETS_BASE + "icon_circle_steak.png";
        public static final String STRAWBERRY = ASSETS_BASE + "icon_circle_strawberry.png";
        public static final String TOMATO = ASSETS_BASE + "icon_circle_tomato.png";
        public static final String WATERMELON = ASSETS_BASE + "icon_circle_watermelon.png";
    }

    /**
     * 将一个适用于屏幕上的值转换到物理世界中去
     * @param value
     * @return 这个值在物理世界中的大小
     */
    public static float toWorld(float value) {
        return value / SW_RADIO;
    }

    public static Vector2 toWorld(Vector2 value) {
        value.scl(1 / SW_RADIO);
        return value;
    }

    /**
     * 将一个适用于物理世界里的值转换到屏幕上去
     * @param value
     * @return 这个值在屏幕上的大小
     */
    public static float toScreen(float value) {
        return value * SW_RADIO;
    }

    public static Vector2 toScreen(Vector2 value) {
        value.scl(SW_RADIO);
        return value;
    }

    /**
     * 由于设计时是按照1080P的屏幕设计的，所以对于每个分辨率的屏幕而言
     * 是有可能会存在一些缩放的，但是我们想利用已经计算好的数据
     * 于是可以通过此方法，将那些以1080P屏幕作为标准得到的水平方向上的数据转换为适应当前屏幕的数据
     * @param value
     * @return 比如在1080P的屏幕中，一个物体的宽度是100px 但是用户的屏幕宽度为108px
     *          所以转换后的物体宽度应为10px
     */
    public static float byStdHor(float value) {
        return value * STD_HOR_RADIO;
    }

    /**
     * 同上 竖直方向上的比例换算
     * @param value
     * @return
     */
    public static float byStdVer(float value) {
        return value * STD_VER_RADIO;
    }

    /**
     * 时间间隔
     * @return
     */
    public static float DT() {
        return Gdx.graphics.getDeltaTime();
    }

    /**
     * 鼠标在物理世界中的坐标
     * @return
     */
    public static Vector2 MouseW(int pointer) {
        float x = Gdx.input.getX(pointer) / SW_RADIO;
        float y = (U.S_H - Gdx.input.getY(pointer)) / SW_RADIO;
        return mouse.set(x, y);
    }

    public static Vector2 MouseS(int pointer) {
        float x = Gdx.input.getX(pointer);
        float y = S_H - Gdx.input.getY(pointer);
        return mouse.set(x, y);
    }
}