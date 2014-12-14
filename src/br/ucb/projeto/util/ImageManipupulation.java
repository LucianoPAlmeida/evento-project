package br.ucb.projeto.util;

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
}
