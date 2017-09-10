package com.game.snake;

import com.game.AbstractGame;
import com.google.common.collect.Sets;

import java.awt.*;
import java.util.Set;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class SnakeGame extends AbstractGame {

    public SnakeGame(int rows, int clos) {
        this.ROWS = rows;
        this.CLOS = clos;
        lanch();
    }

    public void lanch() {
        this.setTitle("贪吃蛇");
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setSize(this.Block_SIZE * this.CLOS, this.Block_SIZE * this.ROWS);
        this.setBackground(Color.GRAY);
//        this.setResizable(false);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        int rows = 50;
        int cols = 50;
        Yard yard = new Yard(rows, cols);
        Set<Egg> eggs = Sets.newHashSet(new Egg(yard), new Egg(yard));
        Snake snake = new Snake(yard, eggs);
        SnakeGame snakeGame = new SnakeGame(rows, cols);
        snakeGame.parts.add(snake);
        snakeGame.parts.add(yard);
        snakeGame.parts.addAll(eggs);
        snakeGame.start();

    }

}
