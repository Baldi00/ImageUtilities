/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrea
 */
public class ImageAggregator {

    private static final int WIDTH = 271;
    private static final int HEIGHT = 377;
    private static final int NUM_COL = 35;
    private static final int NUM_RIG = 14;
    private static final int TOTAL_IMGS = 490;
    
    
    public static void main(String[] args) throws IOException {
        String pathDir = "C:\\Users\\Andrea\\Desktop\\Cover Videogiochi\\Tutte per grande immagine";
        
        File dir = new File(pathDir);
        BufferedImage bigImage = new BufferedImage(WIDTH*NUM_COL, HEIGHT*NUM_RIG, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bigImage.createGraphics();
        File [] covers = dir.listFiles();
        
        int current = 0;
        for(int i=0;i<NUM_RIG;i++){
            for(int j=0;j<NUM_COL;j++){
                BufferedImage myPicture = ImageIO.read(covers[current]);
                g.drawImage(myPicture, j*WIDTH, i*HEIGHT, null);
                current++;
                System.out.println(current);
                if(current>=TOTAL_IMGS)
                    current = 0;
            }
        }
        g.dispose();
        
        ImageIO.write(bigImage, "PNG", new FileOutputStream("Covers.png"));
        Desktop.getDesktop().open(new File("Covers.png"));
    }
    
}
