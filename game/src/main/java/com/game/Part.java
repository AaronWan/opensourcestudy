package com.game;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public interface Part {
    Random random = new Random();
    void paint(Graphics g);

    default void say(String something){
        try {
            Runtime.getRuntime().exec("say "+something);
        } catch (IOException e) {

        }
    }
    default Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    default Rectangle getRect(){
        throw new RuntimeException("getRect no impl");
    }

    default void rAppear(){

    }

    public enum Stat {
        RUN, DEAD;
    }
}
