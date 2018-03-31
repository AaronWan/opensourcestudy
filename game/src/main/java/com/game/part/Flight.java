package com.game.part;

import com.game.Part;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class Flight extends Thread implements Deadable, Part {
    private final int maxX;
    private final int maxY;
    private List<Part> parts;
    private long lastBombTime = System.currentTimeMillis();
    private Point center;
    private Point currentLocation;
    private long createTime = System.currentTimeMillis();
    private Stat stat = Part.Stat.RUN;
    private BufferedImage moveIcon;

    {

        try {
            moveIcon=getPartIcon("/icon/flight/flight.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Flight(int maxX, int maxY, List<Part> parts) {
        this.center = new Point(10, random.nextInt(maxY / 5));
        this.currentLocation = new Point(this.center.x, this.center.y);
        this.maxX = maxX;
        this.maxY = maxY;
        this.parts = parts;
        start(this);
    }

    @Override
    public void run() {
        while (true && stat != Part.Stat.DEAD) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            throwBomb();
            move();
            checkStat();
        }
    }


    private void throwBomb() {
        if (System.currentTimeMillis() - lastBombTime > 10000) {
            lastBombTime = System.currentTimeMillis();
            new Bomb(this.currentLocation.x, Double.valueOf(this.currentLocation.y + this.getRect().getHeight()).intValue(), maxX, maxY,parts);
        }
    }

    private int dirX = 1;

    private void move() {
        this.currentLocation.setLocation(this.currentLocation.x + dirX, this.currentLocation.y);
    }

    private void checkStat() {
        if (this.currentLocation.x > maxX) {
            this.currentLocation.x = 0;
            try {
                sleep(10000);
            } catch (InterruptedException e) {
            }
        } else {
            this.stat = Part.Stat.RUN;
        }
    }

    private long getLifeTime() {
        return System.currentTimeMillis() - createTime;
    }

    @Override
    public void paint(Graphics g) {
        if (stat != Part.Stat.DEAD) {
            g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                    ((Double) this.currentLocation.getY()).intValue(), 80, 50, null);
        }

    }

    private Image getIcon() {
        return moveIcon;
    }

    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), 50, 20).getBounds();
    }


    @Override
    public Stat checkState() {
        return null;
    }
}
