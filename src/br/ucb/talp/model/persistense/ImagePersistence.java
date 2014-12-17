package br.ucb.talp.model.persistense;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.servlet.http.Part;

import br.ucb.projeto.util.ImageManipupulation;

public class ImagePersistence {
	private static ImagePersistence imgPersistence;
	
	private String serverPath;
	private String tmpFileName;
	private ImagePersistence(){
		
	}
	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	
	public String getTmpFileName() {
		return tmpFileName;
	}
	public void setTmpFileName(String tmpFileName) {
		this.tmpFileName = tmpFileName;
	}
	public String getTmpFilePath(){
		return getServerPath()+getTmpFileName()+".png";
	}
	public static ImagePersistence getInstance(){
		if(imgPersistence == null){
			imgPersistence = new ImagePersistence();
		}
		return imgPersistence;
	}
	
	public String persist(Part image,String name,String formato){
		String fileName,filePath;
		delete(getServerPath()+getTmpFileName());
		do{
			if(name == null){
				fileName = getTmpFileName()+"."+formato;
			}else{
				fileName = name+"."+formato;
			}
			filePath = getServerPath()+fileName;
		}while(new File(filePath).exists());
		try {
			image.write(filePath);
			if(formato.equals("jpeg")){
				ImageManipupulation.imageJpegToPng(new File(filePath), true);
				filePath = filePath.replace("jpeg","png");
			}
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String randomName(){
		Random random = new Random();
		Integer randomNumber = random.nextInt();
		randomNumber = (randomNumber < 0)?randomNumber*(-1):randomNumber; 
		return randomNumber.toString();
	}
	public boolean delete(String imagePath){
		try {
			Files.deleteIfExists(Paths.get(imagePath));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String copy(String pathOriginal,String copyPath,boolean deleteOrinal){
		try {
			Files.copy(Paths.get(pathOriginal),Paths.get(copyPath), StandardCopyOption.REPLACE_EXISTING);
			if(deleteOrinal){
				delete(pathOriginal);
			}
			return copyPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String rename(String path,String novoNome){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		File file = new File(path);
		File newFile = new File(imgPersistence.getServerPath()+((novoNome == null)?imgPersistence.randomName():novoNome)+".png");
		file.renameTo(newFile);
		return newFile.getAbsolutePath(); 
	}
}
