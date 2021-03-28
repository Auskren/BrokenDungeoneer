import java.awt.image.BufferedImage;

public class Visual {

    // Vertical mirroring of image from right to left
    public static void mirror(BufferedImage image) {
        //get width of image
        int width = image.getWidth();
        //mirror point of image
        int mirrorPoint = width / 2;
        int leftPixel;
        int rightPixel;
        int temp;

        // loop through all the row of image
        for (int y = 0; y < image.getHeight(); y++) {
            // loop from 0 to the middle (mirror point) that is half part of image
            for (int x = 0; x < mirrorPoint; x++) {
                //get left pixel of image
                leftPixel = image.getRGB(x, y);
                //get right pixel of image
                rightPixel = image.getRGB(width-x-1,y);
                //replace right pixel with left pixel color
                image.setRGB(x,y,rightPixel);
                image.setRGB(width-x-1,y,leftPixel);
            }
        }
    }
}
