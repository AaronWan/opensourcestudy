package com.game.chilken;

import com.game.Part;
import com.google.common.collect.Lists;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;
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
    private Color color;
    boolean isWell = true;
    private BufferedImage icon;

    public Egg(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = getRandomColor();
        count++;
        this.intersects();
        eggs.add(this);
    }

    Random random = new Random();

    private Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public boolean intersects() {
        for (Egg egg : eggs) {
            if (egg.getRect().intersects(this.getRect().getBounds())) {
                isWell=false;
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

    @Override
    public void paint(Graphics g) {
//        g.drawImage(image,x,y,40,40,null);
        if (this.isWell) {
            g.setColor(color);
            g.fillOval(x, y, 20, 25);
        }else{
            g.setColor(Color.gray);
            g.fillOval(x,y,20,2);
        }
    }


}
