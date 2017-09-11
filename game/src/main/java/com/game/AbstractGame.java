package com.game;

import com.game.chick.Egg;
import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public abstract class AbstractGame extends JFrame {
    protected PaintThread paintThread = new PaintThread();// 这样也可以定义一个新线程，并非只有那种定义
    public List<Part> parts = Lists.newArrayList();
    protected Image offScreenImage = null;
    public int ROWS;
    public int CLOS;
    public static final int Block_SIZE = 15;
    public boolean gameOver = false;

    {
        this.addKeyListener(new KeyMonitor());
        this.addMouseListener(new MouseMonitor());
    }

    private class PaintThread extends Thread {
        boolean running = true;

        public PaintThread() {
            this.setDaemon(true);
        }

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
                if (part instanceof KeyHandler) {
                    ((KeyHandler) part).keyEventProcess(e);
                }
            });
            if (AbstractGame.this instanceof KeyHandler) {
                ((KeyHandler) AbstractGame.this).keyEventProcess(e);
            }
        }
    }

    public class MouseMonitor extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            parts.forEach(part -> {
                if (part instanceof KeyHandler) {
                    ((KeyHandler) part).mouseClickHandler(e);
                }
            });
            if (AbstractGame.this instanceof KeyHandler) {
                ((KeyHandler) AbstractGame.this).mouseClickHandler(e);
            }
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
        g.setColor(Color.GREEN);
        g.drawString("Score:" + Egg.count * 5, 10, 60);

        if (gameOver) {
            g.setFont(new Font("楷体_GB2312", Font.BOLD, 50));
            g.drawString("游戏结束", this.getWidth() / 4, this.getHeight() / 2);
            paintThread.gameOver();
        }

        g.setColor(c);
        for (int i = 0; i < parts.size(); i++) {
            Part part=parts.get(i);
            part.paint(g);
        }

    }

    public void start() {
        paintThread.start();
        this.parts.forEach(part -> {
            if (part instanceof Thread) {
                ((Thread) part).start();
            }
        });
    }

    public interface KeyHandler {
        default void keyEventProcess(KeyEvent e) {

        }

        default void mouseClickHandler(MouseEvent e) {

        }

        default void mouseMoveHandler(MouseEvent e) {

        }
    }

    public abstract void lanch();
}
