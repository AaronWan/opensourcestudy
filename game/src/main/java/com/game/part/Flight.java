package com.game.part;

import com.game.Part;
import com.game.chick.Chick;
import com.game.snake.Egg;
import com.game.snake.Snake;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
@Slf4j
public class Flight extends Thread implements Deadable, Part {
    ExecutorService executorService;
    private final int maxX;
    private final int maxY;
    private List<Part> parts;
    private List<Bomb> bombs = Lists.newArrayList();
    private long lastBombTime = System.currentTimeMillis();
    private Point center;
    private Point currentLocation;
    private long createTime = System.currentTimeMillis();
    private Stat stat = Part.Stat.RUN;
    private BufferedImage moveIcon;

    {
        executorService = Executors.newCachedThreadPool();
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
            Bomb bomb = new Bomb(this.currentLocation.x, Double.valueOf(this.currentLocation.y + this.getRect().getHeight()).intValue(), maxX, maxY);
            bombs.add(bomb);
            executorService.execute(bomb);
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

        for (int i = 0; i < this.bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.stat != Stat.DEAD) {
                bomb.paint(g);
            }
        }
    }

    private Image getIcon() {
        return moveIcon;
    }

    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), 50, 20).getBounds();
    }

    public class Bomb implements Runnable, Deadable, Part {
        private BufferedImage moveIcon;

        {
            try {
                moveIcon =getPartIcon("/icon/bomb/bomb.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private Stat stat = Part.Stat.RUN;
        private Point center;
        private Point currentLocation;
        private final int maxX;
        private final int maxY;

        public Bomb(int x, int y, int maxX, int maxY) {
            this.center = new Point(10, random.nextInt(maxY / 5));
            this.currentLocation = new Point(x, y);
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public void run() {
            while (true && stat != Part.Stat.DEAD) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                move();
                eat();
                checkStat();
            }
        }

        public void eat() {
            if (parts != null) {
                for (int i = 0; i < parts.size(); i++) {
                    Part e = parts.get(i);
                    if (e instanceof Egg || e instanceof com.game.chick.Egg || e instanceof Chick || e instanceof Snake)
                        if (this.getRect().intersects(e.getRect())) {//Intersects相交
                            e.rAppear();
                            if (e instanceof Deadable) {
                                parts.remove(e);
                            }
                            if (e instanceof Snake) {
                                say("Game Over");
                                System.exit(0);
                            }
                            this.stat = Stat.DEAD;
                        }
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            if (stat != Part.Stat.DEAD) {

                g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                        ((Double) this.currentLocation.getY()).intValue(), 20, 25, null);
            }
        }

        private Image getIcon() {
            return moveIcon;
        }
        private void checkStat() {
            if (this.currentLocation.y > maxY) {
                Flight.this.bombs.remove(this);
                this.stat = Part.Stat.DEAD;
            } else {
                this.stat = Part.Stat.RUN;
            }
        }

        @Override
        public Rectangle getRect() {
            return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), 40, 40).getBounds();
        }

        @Override
        public void rAppear() {

        }

        private int dirX = 1;
        private int dirY = 1;

        private void move() {
            this.currentLocation.setLocation(this.currentLocation.x + dirX, this.currentLocation.y + dirY);
        }
    }

}
