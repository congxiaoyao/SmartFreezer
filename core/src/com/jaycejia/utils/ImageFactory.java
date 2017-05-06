package com.jaycejia.utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by congxiaoyao on 2017/5/5.
 */

public class ImageFactory {

    private static Map<String, String> texturePath;
    static {
        texturePath = new HashMap<String, String>();
        texturePath.put(U.Image.BACKGROUND, U.Image.BACKGROUND);
        texturePath.put(U.Image.NONE, U.Image.NONE);
        texturePath.put("圆白菜", U.Image.CABBAGE);
        texturePath.put("薯条", U.Image.CHIPS);
        texturePath.put("黄瓜", U.Image.CUCUMBER);
        texturePath.put("鸡腿", U.Image.DRUMSTICK);
        texturePath.put("鸡蛋", U.Image.EGG);
        texturePath.put("茄子", U.Image.EGGPLANT);
        texturePath.put("玉米", U.Image.MAIZE);
        texturePath.put("猪肉", U.Image.PORK);
        texturePath.put("香肠", U.Image.SAUSAGE);
        texturePath.put("牛排", U.Image.STEAK);
        texturePath.put("草莓", U.Image.STRAWBERRY);
        texturePath.put("西红柿", U.Image.TOMATO);
        texturePath.put("西瓜", U.Image.WATERMELON);
    }

    private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public static Texture getTextureByName(String name) {
        String path = texturePath.get(name);
        if (path == null) path = U.Image.NONE;
        Texture texture = textureMap.get(path);
        if(texture == null) texture = new Texture(path);
        textureMap.put(path, texture);
        return texture;
    }

    public static void initAll() {
        Set<String> names = texturePath.keySet();
        for (String name : names) {
            getTextureByName(name);
        }
    }

    public static void clear() {
        textureMap.clear();
    }
}
