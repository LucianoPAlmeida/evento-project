package br.ucb.projeto.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ImageManipupulation {
	public static boolean imageJpegToPng(File image,boolean deleteOriginal){
		try {
			BufferedImage bfImg = ImageIO.read(image);
			File outputFile =  new File(image.getAbsolutePath().replace(".jpeg",".png"));
			ImageIO.write(bfImg, "png",outputFile);
			if(deleteOriginal){
				Files.deleteIfExists(Paths.get(image.getAbsolutePath()));				
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean changeScale(String imagePath,int newWidth,int newHeigth){
		try {
			BufferedImage bfImg = ImageIO.read(new File(imagePath));
			BufferedImage novaImagem = new BufferedImage(newWidth,newHeigth, bfImg.getType());    
	        Graphics2D g2d = novaImagem.createGraphics();    
	        g2d.drawImage(bfImg, 0, 0, newWidth, newHeigth, null);    
	        g2d.dispose();
			ImageIO.write(novaImagem, "png",new File(imagePath));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//public static boolean wi
}
