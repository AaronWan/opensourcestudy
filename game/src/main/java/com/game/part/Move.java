package com.game.part;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public interface Move extends Runnable{
    @Override
    default void run(){
        move();
    }
    void move();
}
