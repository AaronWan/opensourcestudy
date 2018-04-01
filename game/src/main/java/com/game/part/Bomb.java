package com.game.part;

import com.game.Part;
import com.game.chick.Chick;
import com.game.chick.Egg;
import com.game.config.GameConfigManager;
import com.game.snake.Snake;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;

@Getter
@Setter
public class Bomb implements Move, Deadable, Part {
    private List<Part> parts;
    private BufferedImage moveIcon;

    {
        try {
            moveIcon = getPartIcon("/icon/bomb/bomb.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private volatile Stat stat = Part.Stat.RUN;
    private Point center;
    private Point currentLocation;
    private final int maxX;
    private final int maxY;

    public Bomb(int x, int y, int maxX, int maxY, List<Part> parts) {
        this.center = new Point(10, random.nextInt(maxY / 5));
        this.currentLocation = new Point(x, y);
        this.maxX = maxX;
        this.maxY = maxY;
        this.setParts(parts);
        parts.add(this);
        start(this);
    }

    @Override
    public void move() {
        while (!stat.equals(Part.Stat.DEAD)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            this.currentLocation.setLocation(this.currentLocation.x + dirX, this.currentLocation.y + dirY);
            eat();
            checkStat();
        }
        System.out.println("bomb stop");
    }

    public void eat() {
        if (parts != null) {
            for (int i = 0; i < parts.size(); i++) {
                Part e = parts.get(i);
                if (e instanceof Egg || e instanceof com.game.chick.Egg || e instanceof Chick || e instanceof Snake)
                    if (this.getRect().intersects(e.getRect())) {//Intersects相交
                        e.rAppear();
                        if (e instanceof Deadable) {
                            ((Deadable) e).setStat(Stat.DEAD);
                            parts.remove(e);
                            System.out.println(this.hashCode()+""+this.getStat());
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
private String font;
    @Override
    public void paint(Graphics g) {
        System.out.println(this.hashCode()+" paint "+this.getStat());
        if (stat != Part.Stat.DEAD) {
            g.drawImage(getIcon(), ((Double) this.currentLocation.getX()).intValue(),
                    ((Double) this.currentLocation.getY()).intValue(), 20, 25, null);
        } else {
            if(font==null) {
                font = GameConfigManager.getRandomStr(1).toString();
            }
            g.drawString(font, (int) this.currentLocation.getX(), (int) this.currentLocation.getY());
        }
    }

    private Image getIcon() {
        return moveIcon;
    }

    private void checkStat() {
        if (this.currentLocation.y > maxY) {
            parts.remove(this);
            this.stat = Part.Stat.DEAD;
        }
    }

    @Override
    public Rectangle getRect() {
        return new Ellipse2D.Double(this.currentLocation.getX(), this.currentLocation.getY(), 10, 10).getBounds();
    }

    @Override
    public void rAppear() {

    }

    private int dirX = 1;
    private int dirY = 1;

    @Override
    public Stat checkState() {
        return null;
    }
}