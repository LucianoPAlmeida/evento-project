package br.ucb.projeto.controller.managedBeans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.talp.model.DAOS.PalestranteDAO;
import br.ucb.talp.model.persistense.ImagePersistence;
@SessionScoped
@ManagedBean(name = "palestranteBean")
public class CadastroPalestranteManagedBean {
	private Palestrante palestrante;
	private Part image;
	private boolean updating,loadFile;
	private String tmpPath;
	public CadastroPalestranteManagedBean(){
		setPalestrante(new Palestrante());
		setTmpPath("http://"+IndexManagedBean.APPLICATION_DOMAIN+"/EventoProject/images/"+ImagePersistence.getInstance().getTmpFileName()+".png");
	}
	public Palestrante getPalestrante() {
		return palestrante;
	}
	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}
	public Part getImage() {
		return image;
	}
	public void setImage(Part image) {
		this.image = image;
	}
	public boolean isUpdating() {
		return updating;
	}
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	public boolean isLoadFile() {
		return loadFile;
	}
	public void setLoadFile(boolean loadFile) {
		this.loadFile = loadFile;
	}
	
	public String getTmpPath() {
		return tmpPath;
	}
	public void setTmpPath(String tmpPath) {
		this.tmpPath = tmpPath;
	}
	public String submit(){
		ImagePersistence.getInstance().delete(ImagePersistence.getInstance().getTmpFilePath());
		if(isUpdating()){
			try {
				Files.copy(Paths.get(getPalestrante().getPhoto().getPath()),Paths.get(ImagePersistence.getInstance().getTmpFilePath()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if((getImage().getSize() != 0)){
			uploadFile();
		}else if(!isUpdating()){
			try {
				getPalestrante().setPhoto(new ImagePath(ImagePersistence.getInstance().getServerPath()+"palestranteDefalut.png"));
				Files.copy(Paths.get(getPalestrante().getPhoto().getPath()),Paths.get(ImagePersistence.getInstance().getTmpFilePath()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "confirmarPalestrante";
	}
	
	public void uploadFile(){
		setLoadFile((getImage().getSize() != 0));
		String formato = getImage().getContentType();
		formato = formato.substring(formato.indexOf("/")+1);
		ImagePersistence.getInstance().persist(getImage(),null, formato);
	}
	
	public String confirmarCadastro(){
		PalestranteDAO dao = new PalestranteDAO();
		String simplePath = null;
		if(getPalestrante() != null && getPalestrante().getPhoto()!= null){
			simplePath = getPalestrante().getPhoto().getSimplePath();
		}
		dao.add(getPalestrante(),!(simplePath != null && simplePath.endsWith("palestranteDefalut.png")));
		clear();
		return "listarPalestrantes";
	}
	
	public String confirmarAlteracao(){
		PalestranteDAO dao = new PalestranteDAO();
		dao.update(getPalestrante(),isLoadFile());
		clear();
		return "listarPalestrantes";
	}
	public String cancelar(){
		clear();
		return "principal";
	}
	public void clear(){
		setPalestrante(new Palestrante());
		setUpdating(false);
		setLoadFile(false);
		ImagePersistence.getInstance().delete(ImagePersistence.getInstance().getTmpFilePath());
	}
}
