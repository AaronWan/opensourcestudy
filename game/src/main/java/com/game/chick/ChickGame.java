package com.game.chick;

import com.game.AbstractGame;
import com.game.Part;
import com.game.chick.music.PlayMusic;
import com.game.config.GameConfigManager;
import com.game.part.Bird;
import com.game.part.Flight;
import com.game.part.Glass;
import com.game.snake.Snake;
import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ChickGame extends AbstractGame implements AbstractGame.KeyHandler, Part {
    private String number;
    private int errTimes;
    private JPanel btnPanel;
    private Snake snake;
    private List<GamePasswordButton> numbers = Lists.newArrayList();

    {
        Set<String> passwordChars = GameConfigManager.getRandomStr(20);
        passwordChars.forEach(item -> {
            numbers.add(new GamePasswordButton(item, getWidth() / passwordChars.size(), 50));
        });
    }

    private boolean start;


    public ChickGame(int rows, int clos) {
        this.ROWS = rows;
        this.CLOS = clos;
        this.number = numbers.get(random.nextInt(numbers.size())).key;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lanch();
    }

    public void lanch() {
        this.say("小鸡下蛋 的游戏要开始了，先输入密码, " + number);
        this.setTitle("小鸡下蛋" + number);
        this.setLocation(300, 300);
        this.setBackground(Color.white);
        btnPanel = new JPanel(new GridLayout(2, 5, 5, 4));
        for (int i = 0; i < numbers.size(); i++) {
            GamePasswordButton btn = numbers.get(i);
            btnPanel.add(btn);
        }
        this.add(btnPanel);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


    public void warn(String e) {
        errTimes++;
        if (errTimes < 4) {
            this.say("万铮，你输入的是," + e + ",请输入  ," + number + ",");
        } else {
            this.say("万铮，可以问一下妈妈和爸爸哪个是  " + number);
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

    void addGlasses() {
        for (int i = 0; i < 100; i++)
            this.parts.add(new Glass(random.nextInt(ROWS * Block_SIZE), random.nextInt(CLOS * Block_SIZE)));
    }

    void addFlight() {
        new Thread(() -> {
            while (true) {
                parts.add(new Flight(ROWS * Block_SIZE, CLOS * Block_SIZE, parts));
                try {
                    Thread.sleep(random.nextInt(100000));
                } catch (InterruptedException e) {
                }
                parts.add(new Flight(ROWS * Block_SIZE, CLOS * Block_SIZE, parts));
            }
        }).start();

    }

    void addBirds() {
//        for (int i = 0; i < 1; i++)
//            this.parts.add(new Bird(ROWS * Block_SIZE,CLOS * Block_SIZE));
    }

    class GamePasswordButton extends JButton {
        private String key;

        public GamePasswordButton(String label, int width, int height) {
            super("" + label);
            this.setSize(width, height);
            this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            this.setBackground(getRandomColor());
            this.key = label;
            this.addLisner();
        }

        public void addLisner() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (key.equals(ChickGame.this.number)) {
                        ChickGame.this.init();
                    } else {
                        warn(key + "");
                    }
                }
            });

        }
    }

    private void init() {
        this.remove(btnPanel);
        setCenter();
        addGlasses();
        addFlight();
        addBirds();
        addSnake();
        if (!start) {
            say("开始下蛋吧");
        }
        start = true;
        this.changeEggStatThread.start();
        start();
    }

    private void addSnake() {
        this.snake = new Snake(ROWS, CLOS, parts);
        this.parts.add(snake);
    }

    private void setCenter() {
        this.setSize(this.Block_SIZE * this.CLOS, this.Block_SIZE * this.ROWS);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int centerX = screenSize.width / 2;

        int centerY = screenSize.height / 2;

        this.setLocation(centerX - this.getWidth() / 2, centerY - this.getHeight() / 2);

    }

    private Thread changeEggStatThread = new Thread(() -> {
        while (true) {
            for (int i = 0; i < parts.size(); i++) {
                Part part = parts.get(i);
                if (part instanceof Egg) {
                    Egg egg = (Egg) part;
                    if (egg.needToBeChilken()) {
                        parts.add(new Chick(egg.getSize(), egg.getX(), egg.getY(), ROWS * Block_SIZE, CLOS * Block_SIZE));
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

    public static void main(String[] args) {
        ChickGame chickGame = new ChickGame(80, 80);
    }

}
