package com.game.chick;

import com.game.AbstractGame;
import com.game.Part;
import com.game.part.Deadable;
import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Chick extends Thread implements Deadable, Part, AbstractGame.KeyHandler {
    private final int maxX;
    private final int maxY;
    private Point center;
    private Point currentLocation;
    private long r = 100;
    private volatile int grow_time = 1;
    private long createTime = System.currentTimeMillis();
    private Stat stat = Stat.RUN;
    private BufferedImage icon1;
    private List<BufferedImage> moveIcon = Lists.newArrayList();

    private BufferedImage left;

    private BufferedImage right;

    {
        try {
            icon1 = getPartIcon("/icon/chick/0.png");
            left = getPartIcon("/icon/chick/left.png");
            right = getPartIcon("/icon/chick/right.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Chick(int grow_time, int x, int y, int maxX, int maxY) {
        this.center = new Point(x, y);
        this.currentLocation = new Point(x, y);
        this.maxX = maxX;
        this.maxY = maxY;
        this.grow_time = grow_time;
        this.start();
    }

    @Override
    public void run() {
        while (true && stat != Stat.DEAD) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            grow();
            move();
            checkStat();
        }
    }

    private int dirX = 1;
    private int dirY = 1;

    private void move() {
        if (this.currentLocation.x > maxX) {
            if (this.currentLocation.y < maxY && this.currentLocation.y > 0) {
                dirY = -1;
            } else if (this.currentLocation.y < 0) {
                dirY = 1;
            } else {
                dirY = -1;
            }
            dirX = -1;
        }

        if (this.currentLocation.x < 0) {
            if (this.currentLocation.y < maxY && this.currentLocation.y > 0) {
                dirY = 1;
            } else if (this.currentLocation.y < 0) {
                dirY = 1;
            } else {
                dirY = -1;
            }
            dirX = 1;
        }

        if (this.currentLocation.y > maxY) {
            if (this.currentLocation.x < maxX && this.currentLocation.x > 0) {
                dirX = -1;
            } else if (this.currentLocation.x < 0) {
                dirX = 1;
            } else {
                dirX = -1;
            }
            dirY = -1;
        }

        if (this.currentLocation.y < 0) {
            if (this.currentLocation.x < maxX && this.currentLocation.x > 0) {
                dirX = 1;
            } else if (this.currentLocation.x < 0) {
                dirX = 1;
            } else {
                dirX = -1;
            }
            dirY = 1;
        }
        this.currentLocation.setLocation(this.currentLocation.x + dirX, this.currentLocation.y + dirY);
    }

    private void grow() {
        if (grow_time < 40)
            grow_time += 1;
    }

    private void checkStat() {
        if (getLifeTime() > 10 * 1000 * 60) {
            this.stat = Stat.DEAD;
        } else {
            this.stat = Stat.RUN;
        }
    }

    private long getLifeTime() {
        return System.currentTimeMillis() - createTime;
    }

    @Override
    public void paint(Graphics g) {
        if (stat != Stat.DEAD) {
            g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                    ((Double) this.currentLocation.getY()).intValue(), getSize(), getSize(), null);
        }
    }

    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), getSize(), getSize()).getBounds();
    }

    @Override
    public void rAppear() {
        this.stat = Stat.DEAD;
    }

    private Image getIcon() {
        if (getLifeTime() / 2.0 < 300) {
            return icon1;
        } else {
            if (dirX > 0)
                return right;
            else
                return left;
        }
    }

    public int getSize() {
        return grow_time;
    }


}
