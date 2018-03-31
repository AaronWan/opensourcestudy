package com.game.part;

import com.game.Part;
import com.game.part.Deadable;
import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Bird extends Thread implements Deadable, Part {
    private final int maxX;
    private final int maxY;
    private Point center;
    private Point currentLocation;
    private volatile int grow_time = 10;
    private long createTime = System.currentTimeMillis();
    private Stat stat = Part.Stat.RUN;
    private java.util.List<BufferedImage> moveIcon = Lists.newArrayList();

    {
        try {
            moveIcon.add(getPartIcon("/icon/chick/left.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bird(int maxX,int maxY) {
        this.center = new Point(random.nextInt(maxX/3), random.nextInt(maxY/3));
        this.currentLocation = new Point(this.center.x, this.center.y);
        this.maxX=maxX/3;
        this.maxY=maxY/3;
    }

    @Override
    public void run() {
        while (true && stat!= Part.Stat.DEAD) {
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
        if(this.currentLocation.x>maxX){
            if(this.currentLocation.y<maxY&&this.currentLocation.y>0){
                dirY=-1;
            }else if(this.currentLocation.y<0){
                dirY=1;
            }else {
                dirY=-1;
            }
            dirX=-1;
        }

        if(this.currentLocation.x<0){
            if(this.currentLocation.y<maxY&&this.currentLocation.y>0){
                dirY=1;
            }else if(this.currentLocation.y<0){
                dirY=1;
            }else {
                dirY=-1;
            }
            dirX=1;
        }

        if(this.currentLocation.y>maxY){
            if(this.currentLocation.x<maxX&&this.currentLocation.x>0){
                dirX=-1;
            }else if(this.currentLocation.x<0){
                dirX=1;
            }else {
                dirX=-1;
            }
            dirY=-1;
        }

        if(this.currentLocation.y<0){
            if(this.currentLocation.x<maxX&&this.currentLocation.x>0){
                dirX=1;
            }else if(this.currentLocation.x<0){
                dirX=1;
            }else {
                dirX=-1;
            }
            dirY=1;
        }
        this.currentLocation.setLocation(this.currentLocation.x+dirX,this.currentLocation.y+dirY);
    }

    private void grow() {
        if (grow_time < 40)
            grow_time += 1;
    }

    private void checkStat() {
        if (getLifeTime() > 10 * 1000 * 60) {
            this.stat = Part.Stat.DEAD;
        } else {
            this.stat = Part.Stat.RUN;
        }
    }

    private long getLifeTime() {
        return System.currentTimeMillis() - createTime;
    }

    @Override
    public void paint(Graphics g) {
        if(stat!= Part.Stat.DEAD)
            g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                    ((Double) this.currentLocation.getY()).intValue(), getSize(), getSize(), null);
    }
    private Image getIcon() {
        return moveIcon.get(0);
    }

    public int getSize() {
        return grow_time;
    }
    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), getSize(), getSize()).getBounds();
    }

}
