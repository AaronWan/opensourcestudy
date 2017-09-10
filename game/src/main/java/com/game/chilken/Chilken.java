package com.game.chilken;

import com.game.AbstractGame;
import com.game.Part;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Chilken extends Thread implements Part ,AbstractGame.KeyHandler {
    private int x;
    private int y;
    private BufferedImage icon;
    {
        try {
            icon = ImageIO.read(new File("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/game/src/main/java/com/game/chilken/icon/timg.jpeg"));
        }catch (Exception e){}
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(icon,x,y,null);
    }

    @Override
    public void mouseMoveHandler(MouseEvent e) {
        this.x=e.getX();
        this.y=e.getY();
    }
}
