package com.game.chilken;

import com.game.AbstractGame;
import com.game.Part;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ChilkenGame extends AbstractGame implements AbstractGame.KeyHandler, Part {
    private int number;
    private int errTimes;
    private Random random = new Random();
    private boolean start;

    public ChilkenGame(int rows, int clos) {
        this.ROWS = rows;
        this.CLOS = clos;
        this.number = random.nextInt(10);
        lanch();
    }

    public void lanch() {
        this.say("万铮，小鸡下蛋 的游戏要开始了，先输入密码数字 " + number);
        this.setTitle("小鸡下蛋");
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setSize(this.Block_SIZE * this.CLOS, this.Block_SIZE * this.ROWS);
        this.setBackground(Color.orange);
        this.setVisible(true);
    }

    @Override
    public void keyEventProcess(KeyEvent e) {
        if (!this.start)
            if (e.getKeyChar() == (number + "").charAt(0)) {
                this.start = true;
            } else {
                warn(e.getKeyChar() + "");
            }
    }

    public void warn(String e) {
        errTimes++;
        if (errTimes < 10) {
            this.say("万铮，你输入的是" + e + "请输入 数字 " + number);
        } else {
            this.say("万铮，可以问一下妈妈和爸爸哪个是 数字 " + number);
        }
    }


    public int checkSuccess(){

        if(Egg.badCount>10){
            this.gameOver=true;
            return 1;
        }

        if(parts.size()==50){
            this.gameOver=true;
            return 2;
        }

        return 0;
    }

    @Override
    public void mouseClickHandler(MouseEvent e) {
        if (this.start) {
            Egg egg;
            this.parts.add(egg = new Egg(e.getX(), e.getY()));
            this.tips(egg);
        } else {
            warn("鼠标");
        }
    }

    public void tips(Egg egg) {

        if(checkSuccess()==2){
            say("真棒，今天的蛋下完了，明天再来吧");
        }else if(checkSuccess()==1){
            say("今天的蛋被压碎的太多了，今天任务失败，明天再来吧");
            try {
                TimeUnit.SECONDS.sleep(1000);
                say("bye bye");
                System.exit(0);
            } catch (InterruptedException e) {

            }
        }

        if (egg.isWell) {
            int last = this.parts.size();
            if (last % 30 == 0) {
                this.say("下蛋下蛋，总是下蛋，生活中一定有比下蛋更有意义的事情");
            } else if (last % 20 == 0) {
                this.say("儿子，good，不好被发现是爸爸了");
            } else {
                this.say("well done");
            }
        }
    }

    public static void main(String[] args) {
        ChilkenGame chilkenGame = new ChilkenGame(50, 50);
        chilkenGame.start();
    }

}
