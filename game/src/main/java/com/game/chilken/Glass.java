package com.game.chilken;

import com.game.Part;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Glass implements Part {
    private int x;
    private int y;
    private int with=random.nextInt(100);
    private BufferedImage icon1;
    {
        try {
            icon1 = ImageIO.read(new File("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/game/src/main/java/com/game/chilken/icon/glass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Glass(int x,int y) {
        this.x=x;this.y=y;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(icon1, x,y, with, with*3/4, null);
    }
}
