package point;

import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class PointTest {
    public static void main(String[] args) throws InterruptedException {
        GraphicFrame g = new GraphicFrame();
        Thread.sleep(1000);
    }

    static class GraphicFrame extends JFrame {
        private Graphics graphics;
        private Image image;

        public GraphicFrame() {
            init(200, 200);
        }

        public void init(int width, int height) {
            this.setSize(width, height);
            this.setTitle("随机分布");
            this.setBackground(Color.white);
            this.addListener();
            this.setVisible(true);
        }

        @Override
        public void update(Graphics g) {
            System.out.println("update ..");
            image = this.createImage(this.getWidth(), this.getHeight());
            graphics = image.getGraphics();
            paint(graphics);
            g.drawImage(image, 0, 0, this);
        }

        public void paint(Graphics g) {
            g.setColor(Color.BLUE);
            addPoints(g,new PointManager().getRandomPoints(this.getWidth(),this.getHeight(),10000));
        }

        public void addListener() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        public GraphicFrame addPoints(Graphics graphics, List<Point> points) {
            graphics.setColor(Color.BLUE);
            points.forEach(point -> graphics.drawRect(Double.valueOf(point.getX()).intValue(), Double.valueOf(point.getY()).intValue(), 2, 2));
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
