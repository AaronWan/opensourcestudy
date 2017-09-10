package com.game.snake;

import com.game.Part;

import java.awt.*;
import java.util.Random;

public class Egg extends Thread implements Part {
    private Yard yard;
    private int h = Yard.Block_SIZE;
    private int w = Yard.Block_SIZE;
    private int col;
    private int row;
    private Color color = Color.RED;
    private static Random r = new Random();

    private Egg(Yard yard, int col, int row) {
        this.col = col;
        this.row = row;
        this.yard = yard;
        this.setDaemon(true);
    }

    public Egg(Yard yard) {
        this(yard, r.nextInt(yard.CLOS - 2) + 2, r.nextInt(yard.ROWS));
    }

    public void rAppear() {
        this.row = r.nextInt(yard.ROWS - 2) + 2;
        this.col = r.nextInt(yard.CLOS);
        changeColor();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rAppear();
    }

    private void changeColor(){
        if (color == Color.RED) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(Yard.Block_SIZE * col, Yard.Block_SIZE * row, w, h);

    }

    public void paint(Graphics g) {
        // System.out.println("paint egg");
        Color c = g.getColor();
        g.setColor(color);
        g.fillOval(Yard.Block_SIZE * col, Yard.Block_SIZE * row, w, h);
        g.setColor(c);

    }

}