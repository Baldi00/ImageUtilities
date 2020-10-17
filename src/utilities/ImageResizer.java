package utilities;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageResizer {
    
    //private static final int WIDTH = 436;
    //private static final int HEIGHT = 436;
    private static final int WIDTH = 271;
    private static final int HEIGHT = 377;
    
    private static final String ENDING = "";
    
    private static final boolean DELETE_ORIGINAL_AFTER_RESIZE = true;
    private static final boolean RESIZE_SMOOTH = true;
    
    public static void main(String[] args) throws IOException {
        File dir = new File("C:\\Users\\Andrea\\Desktop\\Nuova cartella");
        for(File cover : dir.listFiles()){
            if(cover.isFile()){
                Image original = ImageIO.read(cover);
                if(RESIZE_SMOOTH){
                    ImageIO.write(resizeImageSmooth(original, WIDTH, HEIGHT), "png", new FileOutputStream(cover.getAbsolutePath().substring(0,cover.getAbsolutePath().lastIndexOf("."))+ENDING+".png"));
                }else{
                    ImageIO.write(resizeImage(original, WIDTH, HEIGHT), "png", new FileOutputStream(cover.getAbsolutePath().substring(0,cover.getAbsolutePath().lastIndexOf("."))+ENDING+".png"));
                }
                
                if(DELETE_ORIGINAL_AFTER_RESIZE){
                    cover.delete();
                }
            }
        }
    }
    
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
    
    public static BufferedImage resizeImageSmooth(final Image image, int width, int height) {
        ImageIcon resized = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(resized.getImage(), 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
