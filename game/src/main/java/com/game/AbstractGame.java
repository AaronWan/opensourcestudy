package com.game;

import com.game.chilken.Egg;
import com.game.snake.SnakeGame;
import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public abstract class AbstractGame extends JFrame{
    protected PaintThread paintThread = new PaintThread();// 这样也可以定义一个新线程，并非只有那种定义
    public List<Part> parts = Lists.newArrayList();
    protected Image offScreenImage = null;
    public int ROWS;
    public int CLOS;
    public static final int Block_SIZE = 15;
    boolean gameOver = false;

    private class PaintThread extends Thread {
        boolean running = true;

        public void run() {
            while (running) {

                if (AbstractGame.this.isActive()) {
                    update(AbstractGame.this.getGraphics());
                }
                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        public void gameOver() {
            running = false;
        }

    }

    public class KeyMonitor extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            parts.forEach(part -> {
                if (part instanceof SnakeGame.KeyHandler) {
                    ((SnakeGame.KeyHandler) part).keyPressed(e);
                }
            });

        }
    }


    /**
     * 双缓冲技术
     */
    @SuppressWarnings("static-access")
    public void update(Graphics g) {
        offScreenImage = this.createImage(this.Block_SIZE * this.CLOS,
                this.Block_SIZE * this.ROWS);
        Graphics gcs = offScreenImage.getGraphics();
        paint(gcs);
        g.drawImage(offScreenImage, 0, 0, this);
    }

    @SuppressWarnings("static-access")
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("Score:" + Egg.count * 5, 10, 60);

        if (gameOver) {
            g.setFont(new Font("楷体_GB2312", Font.BOLD, 50));
            g.drawString("游戏结束", this.getWidth() / 4, this.getHeight() / 2);
            paintThread.gameOver();
        }

        g.setColor(c);
        parts.forEach(part -> part.draw(g));

    }

    public void start() {
        paintThread.start();
        this.parts.forEach(part -> {
            if (part instanceof Thread) {
                ((Thread) part).start();
            }
        });
    }

   public abstract void lanch();
}
