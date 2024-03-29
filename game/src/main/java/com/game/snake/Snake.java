package com.game.snake;

import com.game.AbstractGame;
import com.game.Part;
import com.game.ScoreAble;
import com.game.chick.Chick;
import com.game.part.Deadable;
import com.game.part.Move;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class Snake implements Move, ScoreAble, Part, Deadable, AbstractGame.KeyHandler {
    private final List<Part> parts;
    Node head;
    Node tail;
    Node node = new Node(20, 30, Dir.L);
    public int score = 0;
    static int size = 0;
    private Stat stat=Stat.RUN;
    public int rows;
    public int cols;

    public Snake(int rows, int cols, List<Part> parts) {
        this.head = node;
        this.tail = node;
        size = 1;
        this.rows = rows;
        this.cols = cols;
        this.parts = parts;
        start(this);
    }

    public void eat() {
        for (int i = 0; i < parts.size(); i++) {
            Part e = parts.get(i);
            if (e instanceof Egg || e instanceof com.game.chick.Egg || e instanceof Chick) {
                if (this.getRect().intersects(e.getRect())) {//Intersects相交
                    e.rAppear();
                    if (e instanceof Deadable) {
                        parts.remove(e);
                        ((Deadable)e).setStat(Stat.DEAD);
                    }
                    this.addTail();
                    size++;
                    if (e instanceof ScoreAble) {
                        int add = ((ScoreAble) e).getScore();
                        if (add < 0) {
                            this.removeTail();
                        }
                        this.score += ((ScoreAble) e).getScore();
                    }
                }
            }
        }
    }

    @Override
    public void setStat(Stat stat) {
        this.stat=stat;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(Yard.Block_SIZE * head.col, Yard.Block_SIZE
                * head.row, head.w, head.h);

    }

    @Override
    public void mouseClickHandler(MouseEvent e) {

    }

    public void addTail() {
        Node node = null;

        switch (tail.dir) {
            case D:
                node = new Node(tail.row - 1, tail.col, tail.dir);
                break;
            case U:
                node = new Node(tail.row + 1, tail.col, tail.dir);
                break;
            case R:
                node = new Node(tail.row, tail.col - 1, tail.dir);
                break;
            case L:
                node = new Node(tail.row, tail.col + 1, tail.dir);
                break;

        }
        tail.next = node;
        node.pre = tail;
        tail = node;
    }

    public void removeTail() {
        if (this.tail != this.head) {
            this.tail = this.tail.pre;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (size <= 0) {
            return;
        }

        for (Node n = head; n != null; n = n.next) {
            n.draw(g);
        }

    }

    private void autoControlHeadDir() {
        if (this.head.col >= this.cols && this.head.dir == Dir.R) {
            this.head.dir = Dir.L;
        } else if (this.head.row >= this.rows && this.head.dir == Dir.D) {
            this.head.dir = Dir.U;
        } else if (this.head.col <= 0 && this.head.dir == Dir.L) {
            this.head.dir = Dir.R;
        } else if (this.head.row <= 0 && this.head.dir == Dir.U) {
            this.head.dir = Dir.D;
        }
    }

    @Override
    public Stat checkState() {
        if (head.row < 2 || head.col < 0 || head.row >= rows + 1 || head.col >= 1 + cols) {
//            isStop = true;
        }
        return null;
    }

    private void delFromTail() {
        if (tail == null) {
            return;
        }
        tail = tail.pre;
        tail.next = null;
    }

    private void addToHead() {

        Node node = null;
        switch (head.dir) {
            case D:
                node = new Node(head.row + 1, head.col, head.dir);
                break;
            case U:
                node = new Node(head.row - 1, head.col, head.dir);
                break;
            case R:
                node = new Node(head.row, head.col + 1, head.dir);
                break;
            case L:
                node = new Node(head.row, head.col - 1, head.dir);
                break;

        }
        node.next = head;
        head.pre = node;
        head = node;
        // System.out.println("addHead");
    }

    @Override
    public void move() {
        while (true&&stat.equals(Stat.RUN)) {
            try {
                Thread.sleep(getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            autoControlHeadDir();
            addToHead();
            delFromTail();
            checkState();
            eat();
        }
    }

    public int getSleepTime() {
        return Double.valueOf(200 * Math.exp(-size / 10)).intValue() + 100;
    }

    private static class Node {

        int w = Yard.Block_SIZE;
        int h = Yard.Block_SIZE;
        int row, col;
        Dir dir = Dir.L;
        Node next = null;
        Node pre = null;

        public Node(int row, int col, Dir dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        private void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(Yard.Block_SIZE * col, Yard.Block_SIZE * row, w, h);
            g.setColor(c);

        }
    }

    @Override
    public void keyEventProcess(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("----------" + e.getKeyCode());
//        if(e.getKeyCode() == KeyEvent.VK_SPACE){
//            this.isStop=!this.isStop;
//            return ;
//        }
//        if(!isStop)
        switch (key) {
            case KeyEvent.VK_UP:
                if (head.dir != Dir.D) head.dir = Dir.U;
                break;
            case KeyEvent.VK_W:
                if (head.dir != Dir.D) head.dir = Dir.U;
                break;
            case KeyEvent.VK_DOWN:
                if (head.dir != Dir.U) head.dir = Dir.D;
                break;
            case KeyEvent.VK_S:
                if (head.dir != Dir.U) head.dir = Dir.D;
                break;
            case KeyEvent.VK_LEFT:
                if (head.dir != Dir.R) head.dir = Dir.L;
                break;
            case KeyEvent.VK_A:
                if (head.dir != Dir.R) head.dir = Dir.L;
                break;
            case KeyEvent.VK_RIGHT:
                if (head.dir != Dir.L) head.dir = Dir.R;
            case KeyEvent.VK_D:
                if (head.dir != Dir.L) head.dir = Dir.R;
                break;
        }

    }

    @Override
    public int getScore() {
        return size * 5;
    }

}