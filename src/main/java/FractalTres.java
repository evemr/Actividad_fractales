/**
 *
 * @author evely
 */
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;

public class FractalTres {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;
    private static ComplexNumber c = new ComplexNumber(-0.223, 0.745);
    private boolean[][] values = null;
    private double minX = -1.5;
    private double maxX = 1.5;
    private double minY = -1.5;
    private double maxY = 1.5;
    private BufferedImage image = null;
    private double threshold = 1;
    private int iterations = 7;

    public FractalTres(){
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        getValues();
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){
                // If the point is in the Set, color it White, else, color it Black.
                if(values[i][j]) image.setRGB(i, j, Color.MAGENTA.getRGB());
                if(!values[i][j]) image.setRGB(i, j, Color.WHITE.getRGB());
            }
        }
        
        JFrame f = new JFrame("FractalTres"){
            @Override
            public void paint(java.awt.Graphics g){
                g.drawImage(image,0,0,null);
            }
        };
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH,HEIGHT);
        f.repaint();
        f.setVisible(true);
    }
    
    private void getValues(){
        values = new boolean[WIDTH][HEIGHT];
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){
		double a = (double)i*(maxX-minX)/(double)WIDTH + minX;
		double b = (double)j*(maxY-minY)/(double)HEIGHT + minY;
                values[i][j] = isInSet(new ComplexNumber(a,b));
            }
        }
    }
    private boolean isInSet(ComplexNumber cn){
        for(int i=0;i<iterations;i++){
            cn = cn.square().add(c);
        }
        return cn.magnitude()<threshold*threshold;
    }

    public static void main(String[] args){
        new FractalTres();
    }
}

class ComplexNumber{
    
    private double a, b;
    // Create a Complex Number with the given real numbers.
    public ComplexNumber(double a, double b){
        this.a = a;
        this.b = b;
    }
    // Method for squaring a ComplexNumber
    public ComplexNumber square(){
        return new ComplexNumber(this.a*this.a - this.b*this.b, 2*this.a*this.b);
    }
    
    // Method for adding 2 complex numbers
    public ComplexNumber add(ComplexNumber cn){
        return new ComplexNumber(this.a+cn.a, this.b+cn.b);
    }
    // Method for calculating magnitude^2 (how close the number is to infinity)
    public double magnitude(){
        return a*a+b*b;
    }
}

