/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

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
public class ImageSplitter {

    private static final int WIDTH = 33;
    private static final int HEIGHT = 33;
    private static final int VERTICAL_OFFSET_UP = 1;
    private static final int VERTICAL_OFFSET_DOWN = 0;
    private static final int HORIZONTAL_OFFSET_LEFT = 1;
    private static final int HORIZONTAL_OFFSET_RIGHT = 0;
    
    private static final int VERTICAL_DIVISION = 37;
    private static final int HORIZONTAL_DIVISION = 16;
    
    private static final boolean USE_DIVISION_INSTEAD_OF_PIXEL = false;
    
    public static void main(String[] args) throws IOException {
        String destDir = "C:\\Users\\Andrea\\Desktop\\test\\";
        String imgToSplit = "All Icons.png";
        
        BufferedImage bigImage = ImageIO.read(new File(imgToSplit));
        
        if(USE_DIVISION_INSTEAD_OF_PIXEL){
            
            int width = bigImage.getWidth()/VERTICAL_DIVISION;
            int height = bigImage.getHeight()/HORIZONTAL_DIVISION;
            
            for(int i=0;i<HORIZONTAL_DIVISION;i++){
                for(int j=0;j<VERTICAL_DIVISION;j++){
                    BufferedImage singleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = singleImage.createGraphics();

                    g.drawImage(bigImage.getSubimage(j*width, i*height, width, height), 0, 0, null);
                    g.dispose();

                    ImageIO.write(singleImage, "PNG", new FileOutputStream(destDir + "img" + (i*VERTICAL_DIVISION+j+1) + ".png"));
                    System.out.println(i*VERTICAL_DIVISION+j+1);
                }
            }
            
        } else {
            
            int numRig = bigImage.getHeight()/HEIGHT;
            int numCol = bigImage.getWidth()/WIDTH;
            
            int width = WIDTH-HORIZONTAL_OFFSET_LEFT-HORIZONTAL_OFFSET_RIGHT;
            int height = HEIGHT-VERTICAL_OFFSET_DOWN-VERTICAL_OFFSET_UP;
            
            for(int i=0;i<numRig;i++){
                for(int j=0;j<numCol;j++){
                    BufferedImage singleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = singleImage.createGraphics();

                    g.drawImage(bigImage.getSubimage(j*WIDTH+HORIZONTAL_OFFSET_LEFT, i*HEIGHT+VERTICAL_OFFSET_UP, width, height), 0, 0, null);
                    g.dispose();

                    ImageIO.write(singleImage, "PNG", new FileOutputStream(destDir + "img" + (i*numCol+j+1) + ".png"));
                    System.out.println(i*numCol+j+1);
                }
            }
            
        }
    }
    
}
