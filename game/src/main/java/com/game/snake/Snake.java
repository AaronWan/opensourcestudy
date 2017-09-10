package com.game.snake;

import com.game.Part;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collection;

public class Snake extends Thread implements Part, SnakeGame.KeyHandler{
    private final Collection<Egg> eggs;
    Node head;
    Node tail;
    Node node = new Node(20, 30, Dir.L);
    static int size = 0;
    private Yard y;
    private int speed = 500;
    public volatile boolean isStop = false;

    public Snake(Yard y, Collection<Egg> eggs) {
        this.head = node;
        this.tail = node;
        size = 1;
        this.y = y;
        this.eggs = eggs;
    }

    public void eat() {
        for (Egg e : eggs) {
            if (this.getRect().intersects(e.getRect())) {//Intersects相交
                e.rAppear();
                this.addTail();
                size++;
                updateSpeed();
            }
        }
    }

    private void updateSpeed() {
        if (size % 20 == 0) {
            setSpeed(size / 20);
        }
    }

    private Rectangle getRect() {
        return new Rectangle(Yard.Block_SIZE * head.col, Yard.Block_SIZE
                * head.row, head.w, head.h);

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

    public void draw(Graphics g) {
        if (size <= 0) {
            return;
        }

        for (Node n = head; n != null; n = n.next) {
            n.draw(g);
        }

    }

    private void move() {
        addToHead();
        delFromTail();
        checkDead();
    }

    private void checkDead() {
        if (head.row < 2 || head.col < 0 || head.row >= y.ROWS + 1 || head.col >= 1 + y.CLOS) {
            isStop = true;
        }
    }

    private void delFromTail() {
        if (tail == null)
            return;
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
    public void run() {
        while (true)
        while (!isStop) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            eat();
        }
    }

    public void setSpeed(int level) {
        this.speed = 500 / level;
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

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.isStop=!this.isStop;
            return ;
        }
        if(!isStop)
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

}