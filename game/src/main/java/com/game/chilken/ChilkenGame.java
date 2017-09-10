package com.game.chilken;

import com.game.AbstractGame;
import com.game.Part;
import com.google.common.collect.Lists;
import com.game.snake.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ChilkenGame extends AbstractGame{

    public ChilkenGame(int rows, int clos) {
        this.ROWS = rows;
        this.CLOS = clos;
        lanch();
    }

    public void lanch() {
        this.setTitle("小鸡下蛋");
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setSize(this.Block_SIZE * this.CLOS, this.Block_SIZE * this.ROWS);
        this.setBackground(Color.GRAY);
        this.addKeyListener(new KeyMonitor());
//        this.setResizable(false);
        this.setVisible(true);

    }



    public static void main(String[] args) {

    }

}
