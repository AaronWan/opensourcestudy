package com.game.chilken;

import java.awt.*;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Egg  {
    public static int count;
    private int x;
    private int y;
    private Color color;

    public Egg(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        count++;
    }
}
