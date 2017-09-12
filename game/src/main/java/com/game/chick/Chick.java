package com.game.chick;

import com.game.AbstractGame;
import com.game.Part;
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
    private Point center;
    private Point currentLocation;
    private long r = 100;
    private volatile int grow_time = 1;
    private long createTime = System.currentTimeMillis();
    private Stat stat = Stat.RUN;
    private BufferedImage icon1;
    private List<BufferedImage> moveIcon = Lists.newArrayList();

    {
        try {
            icon1 = ImageIO.read(new File("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/game/src/main/java/com/game/chick/icon/1.png"));
            moveIcon.add(ImageIO.read(new File("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/game/src/main/java/com/game/chick/icon/0.png")));
            moveIcon.add(ImageIO.read(new File("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/game/src/main/java/com/game/chick/icon/0_1.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Chick(int grow_time, int x, int y) {
        this.center = new Point(x, y);
        this.currentLocation = new Point(x, y);
        this.grow_time = grow_time;
        this.start();
    }

    @Override
    public void run() {
        while (true && !isDead()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            grow();
            move();
            checkStat();
        }
    }

    private int dir = 1;

    private void move() {
        if (this.currentLocation.distance(center) > r) {
            this.dir = -dir;
        }
        this.currentLocation.setLocation(this.currentLocation.getX() + dir, this.currentLocation.getY() + dir);

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

    @Override
    public boolean isDead() {
        return this.stat == Stat.DEAD;
    }

    private long getLifeTime() {
        return System.currentTimeMillis() - createTime;
    }

    @Override
    public void paint(Graphics g) {
        if(stat!=Stat.DEAD)
        g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                ((Double) this.currentLocation.getY()).intValue(), getSize(), getSize(), null);
    }

    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), getSize(), getSize()).getBounds();
    }

    @Override
    public void rAppear() {
        this.stat=Stat.DEAD;
    }

    private Image getIcon() {
        if (getLifeTime() / 2.0 < 300) {
            return icon1;
        } else {
            return moveIcon.get(0);
        }
    }

    public int getSize() {
        return grow_time;
    }


    enum Stat {
        RUN, DEAD;
    }
}
