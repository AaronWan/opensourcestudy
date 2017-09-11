package com.game.chick;

import com.game.AbstractGame;
import com.game.Part;
import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ChickGame extends AbstractGame implements AbstractGame.KeyHandler, Part {
    private int number;
    private int errTimes;
    private Random random = new Random();
    private List<NumberButton> numbers = Lists.newArrayList();

    {
        for (int i = 0; i < 10; i++) {
            numbers.add(new NumberButton(i));
        }
    }

    private boolean start;

    private Thread changeEggStatThread = new Thread(() -> {
        while (true) {
            for (int i = 0; i < parts.size(); i++) {
                Part part = parts.get(i);
                if (part instanceof Egg) {
                    Egg egg = (Egg) part;
                    if (egg.needToBeChilken()) {
                        parts.add(new Chick(egg.getSize(), egg.getX(), egg.getY()));
                        parts.remove(part);
                        System.out.println("change to chilken");
                    }
                }
                if (part instanceof Deadable) {
                    if (((Deadable) part).isDead()) {
                        parts.remove(part);
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    });

    public ChickGame(int rows, int clos) {
        this.ROWS = rows;
        this.CLOS = clos;
        this.number = random.nextInt(10);
        this.changeEggStatThread.start();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lanch();
    }

    public void lanch() {
        this.say("万铮，小鸡下蛋 的游戏要开始了，先输入密码数字," + number);
        this.setTitle("小鸡下蛋");
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setSize(this.Block_SIZE * this.CLOS, this.Block_SIZE * this.ROWS);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 10));
        numbers.forEach(numberButton -> this.add(numberButton));
        this.setVisible(true);
    }


    public void warn(String e) {
        errTimes++;
        if (errTimes < 10) {
            this.say("万铮，你输入的是," + e + "请输入 数字 ," + number);
        } else {
            this.say("万铮，可以问一下妈妈和爸爸哪个是 数字 " + number);
        }
    }


    public int checkSuccess() {

        if (Egg.badCount > 10) {
            this.gameOver = true;
            return 1;
        }

        if (parts.size() >= 10000) {
            this.gameOver = true;
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

        if (checkSuccess() == 2) {
            say("真棒，今天的蛋下完了，明天再来吧");
        } else if (checkSuccess() == 1) {
            say("今天的蛋被压碎的太多了，今天任务失败，明天再来吧");
            say("bye bye");
            System.exit(0);
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

    void addGlasses(){
        for(int i=0;i<100;i++)
        this.parts.add(new Glass(random.nextInt(ROWS*Block_SIZE),random.nextInt(CLOS*Block_SIZE)));
    }

    class NumberButton extends JButton {
        private int number;

        public NumberButton(int number) {
            super("" + number);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            this.setBackground(getRandomColor());
            this.number = number;
            this.addLisner();
        }

        public void addLisner() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (number == ChickGame.this.number) {
                        ChickGame.this.init();
                    } else {
                        warn(number + "");
                    }
                }
            });

        }
    }

    private void init() {
        numbers.forEach(numberButton -> {
            remove(numberButton);
        });
        addGlasses();
        if (!start)
            say("开始下蛋吧");
        start = true;
        start();
    }

    public static void main(String[] args) {
        ChickGame chickGame = new ChickGame(100, 100);
    }

}
