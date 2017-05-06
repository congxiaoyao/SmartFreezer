package com.jaycejia;

/**
 * Created by congxiaoyao on 2017/5/5.
 */

public interface Freezer {

    void addFood(long id, String name);

    void removeFood(long id);

    void clearFood();
}
