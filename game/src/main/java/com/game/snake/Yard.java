package com.game.snake;

import com.game.Part;

import java.awt.*;

public class Yard implements Part {
    public int ROWS;
    public int CLOS;
    public static final int Block_SIZE = 15;

    public Yard(int rows,int clos) {
        this.ROWS=rows;
        this.CLOS=clos;
    }

    public void draw(Graphics g){
        g.setColor(Color.DARK_GRAY);

        g.drawRect(0, 0, this.Block_SIZE * this.ROWS, this.Block_SIZE
                * this.CLOS);
        for (int i = 0; i < this.CLOS - 1; i++) {
            g.drawLine(0, Block_SIZE * (i + 1), this.CLOS * 100, Block_SIZE
                    * (i + 1));
            g.drawLine(this.Block_SIZE * (i + 1), 0, this.Block_SIZE * (i + 1),
                    this.ROWS * 100);
        }
        for (int i = 0; i < this.ROWS - 1; i++) {
            g.drawLine(this.Block_SIZE * (i + 1), 0, this.Block_SIZE * (i + 1),
                    this.CLOS * 100);
        }
    }
}