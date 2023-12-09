/**
 *
 * @author evely
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class FractalDos extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_ITER = 1000;

    private void drawMandelbrotSet(Graphics2D g2) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double zx, zy, cX, cY;
                zx = zy = 0;
                cX = (x - WIDTH / 2.0) / (WIDTH / 4.0);
                cY = (y - HEIGHT / 2.0) / (HEIGHT / 4.0);
                int iter = 0;

                while (zx * zx + zy * zy < 4 && iter < MAX_ITER - 1) {
                    double tmp = zx * zx * zx - 3 * zx * zy * zy + cX;
                    zy = 3 * zx * zx * zy - zy * zy * zy + cY;
                    zx = tmp;
                    iter++;
                }

                int color = iter == MAX_ITER - 1 ? Color.BLACK.getRGB() : Color.HSBtoRGB(iter / 256.0f, 1, iter / (iter + 8.0f));
                image.setRGB(x, y, color);
            }
        }

        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawMandelbrotSet(g2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fractal dos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setContentPane(new FractalDos());
            frame.setVisible(true);
        });
    }
}
