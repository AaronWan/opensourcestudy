package point;

import com.google.common.collect.Lists;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class PointTest {
    public static void main(String[] args) throws InterruptedException {
        int width = 200;
        int height = 200;
        List<Point> points = new PointManager().getRandomPoints(width, height, 100);
        GraphicFrame g=new GraphicFrame();
        Thread.sleep(1000);
        g.addPoints(points);
    }

    static class GraphicFrame extends JFrame {
        private Graphics graphics;
        private JButton retry;

        public GraphicFrame() {
            init(200, 200);
        }

        public void init(int width, int height) {
            this.setSize(width, height);
            this.setTitle("随机分布");
            retry=new JButton("retry");
            BorderLayout layout;
            this.setLayout(layout=new BorderLayout());
            retry.setSize(20,20);
            retry.setLocation(0,0);
            layout.addLayoutComponent(retry,BorderLayout.NORTH);
            this.addListener();
            this.setVisible(true);
        }

        public void addListener() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            retry.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    addPoints(new PointManager().getRandomPoints(200,100,100));
                }
            });
        }

        public void paint(Graphics g){
            this.graphics=g;
            super.paint(g);
        }
        public GraphicFrame addPoints(List<Point> points) {
            graphics.setColor(Color.BLUE);
            points.forEach(point -> graphics.drawRect(Double.valueOf(point.getX()).intValue(), Double.valueOf(point.getY()).intValue(), 2, 2));
            this.repaint();
            return this;
        }


    }

    static class PointManager {
        private List<Point> getRandomPoints(int width, int height, int n) {
            List<Point> points = Lists.newArrayList();
            for (int i = 0; i < n; i++) {
                points.add(getRandomPoint(width, height));
            }
            return points;
        }

        private static Random random = new Random();

        private Point getRandomPoint(int width, int height) {
            return new Point(random.nextInt(width), random.nextInt(height));
        }

    }

}
