/**
 *
 * @author evely
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FractalCuatro extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int MAX_ITER = 1000;
    private static final double RE_START = -2;
    private static final double RE_END = 2;
    private static final double IM_START = -2;
    private static final double IM_END = 2;
    private static final double C_RE = -0.7;
    private static final double C_IM = 0.27015;

    public FractalCuatro() {
        setTitle("Fractal cuatro");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        generateFractal(image);

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        add(label);

        setVisible(true);
    }

    private void generateFractal(BufferedImage image) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double re = map(x, 0, WIDTH, RE_START, RE_END);
                double im = map(y, 0, HEIGHT, IM_START, IM_END);

                int color = calculateJulia(re, im);
                image.setRGB(x, y, color);
            }
        }
    }

    private int calculateJulia(double re, double im) {
        int iter = 0;
        double zx = re;
        double zy = im;

        while (iter < MAX_ITER) {
            double xTemp = (zx * zx) - (zy * zy) + C_RE;
            zy = (2 * zx * zy) + C_IM;
            zx = xTemp;

            if ((zx * zx + zy * zy) > 4) {
                break;
            }

            iter++;
        }

        int color = iter % 256;
        return new Color(color, color, color).getRGB();
    }

    private double map(int value, int start1, int stop1, double start2, double stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (double) (stop1 - start1));
    }

    public static void main(String[] args) {
        new FractalCuatro();
    }
}
