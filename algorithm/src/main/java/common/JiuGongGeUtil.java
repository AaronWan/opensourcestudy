package common;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.List;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class JiuGongGeUtil {


    private static List<ImageCell> createMergeCell(int n, int totalWidth) {
        int totalRow = (int) Math.ceil(Math.sqrt(n));
        int outline = 5;
        int width = ((totalWidth - outline) / totalRow);
        int border = width / 20;
        if (n == 1) {
            return Lists.newArrayList(new ImageCell(border, border, width - 2 * border));
        }
        int lastAloneNum = n % totalRow;
        int totalFullRow = n / totalRow;
        int lastRow = totalRow - totalFullRow - 1;

        int firstStartX = (totalWidth - lastAloneNum * width) / 2;
        int firstStartY = lastRow * width;

        int otherSpace = (totalWidth - totalRow * width) / 2;
        int yOffset = 0;
        if (totalRow != totalFullRow + (lastAloneNum != 0 ? 1 : 0)) {
            yOffset = -width / 2;
        }
        List<ImageCell> imageCells = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            int x = 0, y = firstStartY;
            if (i < lastAloneNum) {
                x = firstStartX + i * width;
            } else {
                x = (i - lastAloneNum) % totalRow * width;
                y = firstStartY + ((i - lastAloneNum) / totalRow + 1) * width;
            }
            imageCells.add(new ImageCell(x + border + otherSpace, y + border + otherSpace + yOffset, width - 2 * border));
        }
        return imageCells;
    }

    @Data
    @AllArgsConstructor
    static class ImageCell {
        int x;
        int y;
        int width;

    }

    public static void main(String[] args) throws IOException {

        for (int i = 1; i <= 9; i++) {
            System.out.printf("-------------------n=%d---------------------\n", i);
            List<ImageCell> rst = createMergeCell(i, 150);
            rst.forEach(temp -> {
                System.out.printf("\t\t\tx:%d,y:%d,width:%d\n", temp.getX(), temp.getY(), temp.getWidth());
            });
            System.out.println("\n\n\n");
        }
    }

}
