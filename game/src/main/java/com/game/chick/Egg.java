package com.game.chick;

import com.game.Part;
import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Egg implements Part {
    private static List<Egg> eggs = Lists.newArrayList();
    public static int count;
    public static int badCount;
    private int x;
    private int y;
    private volatile int width;
    private volatile int height;
    private Color color;
    private long createTime = System.currentTimeMillis();
    boolean isWell = true;
    private int growTime = 2000;

    public Egg(int x, int y) {
        this.x = x;
        this.y = y;
        this.setSize();
        this.color = getRandomColor();
        count++;
        this.intersects();
        eggs.add(this);
    }


    public boolean intersects() {
        for (Egg egg : eggs) {
            if (egg.getRect().intersects(this.getRect().getBounds())) {
                isWell = false;
                badCount++;
                this.say("鸡蛋压坏了");
                return true;
            }
        }
        return false;
    }


    public Ellipse2D getRect() {
        return new Ellipse2D.Double(x, y, 20, 25);

    }

    private void setSize() {
        int size = random.nextInt(40);
        if (size < 10) {
            size = 10;
        }
        this.width = size;
        this.height = size * 6 / 5;
    }

    @Override
    public void paint(Graphics g) {
        if (this.isWell) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        } else {
            g.setColor(Color.gray);
            g.fillOval(x, y, 20, 2);
        }
    }

    public boolean needToBeChilken() {
        if ((System.currentTimeMillis() - createTime) > growTime&&isWell) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return width;
    }
}
