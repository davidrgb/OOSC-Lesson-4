package model.images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;

public class ImageStore {
    

    public static BufferedImage airplane;
    public static BufferedImage car;
    public static BufferedImage dog;
    public static BufferedImage bird;

    static {
        //String cwd = System.getProperty("user.dir");
        //System.out.println("cwd: " + cwd + "\n");
        airplane = readImage("model/images/airplane.png", 80, 60);
        car = readImage("model/images/car.png", 80, 50);
        dog = readImage("model/images/dog.jpg", 70, 50);
        bird = readImage("model/images/bird.jpg", 70, 50);
    }

    public static BufferedImage readImage(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.drawImage(tmp, 0, 0, null);
            g2.dispose();
            return resizedImage;
        } catch (Exception e) {
            System.out.println("Image file load error");
        }
        return null;
    }
}
