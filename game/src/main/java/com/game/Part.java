package com.game;

import java.awt.*;
import java.io.IOException;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public interface Part {
    void paint(Graphics g);

    default void say(String something){
        try {
            Runtime.getRuntime().exec("say "+something);
        } catch (IOException e) {

        }
    }
}
