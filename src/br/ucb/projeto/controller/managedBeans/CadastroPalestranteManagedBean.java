package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import br.ucb.projeto.model.DAOS.PalestranteDAO;
import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.model.persistense.ImagePersistence;
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
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		imgPersistence.delete(ImagePersistence.getInstance().getTmpFilePath());
		if(isUpdating()){
			imgPersistence.copy(getPalestrante().getPhoto().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		if((getImage().getSize() != 0)){
			uploadFile();
		}else if(!isUpdating()){
			getPalestrante().setPhoto(new ImagePath(ImagePersistence.getInstance().getServerPath()+"palestranteDefalut.png"));
			imgPersistence.copy(getPalestrante().getPhoto().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
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
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		PalestranteDAO dao = new PalestranteDAO();
		String simplePath = null;
		if(getPalestrante() != null && getPalestrante().getPhoto()!= null){
			simplePath = getPalestrante().getPhoto().getSimplePath();
		}
		if(!(simplePath != null && simplePath.endsWith("palestranteDefalut.png"))){
			String path = imgPersistence.rename(imgPersistence.getTmpFilePath(),null);
			getPalestrante().setPhoto(new ImagePath(path));
		}
		dao.add(getPalestrante());
		clear();
		return "listarPalestrantes";
	}
	
	public String confirmarAlteracao(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		PalestranteDAO dao = new PalestranteDAO();
		if(isLoadFile()){
			String path = getPalestrante().getPhoto().getPath();
			String newPath = imgPersistence.rename(imgPersistence.getTmpFilePath(), null);
			getPalestrante().setPhoto(new ImagePath(newPath));
			if(!path.endsWith("palestranteDefalut.png")){
				imgPersistence.delete(path);
			}
		}
		dao.update(getPalestrante());
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
