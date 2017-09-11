package com.game.chick;

import javax.swing.*;
import java.awt.*;

public class AwtTest {
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
          
        JFrame f = new JFrame("GridLayout Test"); // 默认BorderLayout
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 5, 4, 4));
        for (int i = 0; i < 10; i++) {
            p.add(new JButton(i+""));
        }  
        f.add(p); // 默认添加到CENTER  
          
        f.pack();  
        f.setVisible(true);  
    }  
  
}  