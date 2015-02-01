package br.ucb.projeto.controller.managedBeans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import br.ucb.projeto.model.DAOS.ParceiroDAO;
import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.Parceiro;
import br.ucb.projeto.model.enuns.TipoParceiro;
import br.ucb.projeto.model.persistense.ImagePersistence;
@SessionScoped
@ManagedBean(name = "parceiroBean")
public class CadastroParceiroManagedBean {
	private ParceiroDAO daoParceiro;
	private Parceiro parceiro;
	private boolean updating,loadFile;
	private String tempPath;
	private Part logo;
	public CadastroParceiroManagedBean() {
		setDaoParceiro(new ParceiroDAO());
		setParceiro(new Parceiro());
		setTempPath("http://"+IndexManagedBean.APPLICATION_DOMAIN+"/EventoProject/images/"+ImagePersistence.getInstance().getTmpFileName()+".png");

	}

	public ParceiroDAO getDaoParceiro() {
		return daoParceiro;
	}

	public void setDaoParceiro(ParceiroDAO daoParceiro) {
		this.daoParceiro = daoParceiro;
	}

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public boolean isUpdating() {
		return updating;
	}

	public void setUpdating(boolean updating) {
		this.updating = updating;
	}

	public Part getLogo() {
		return logo;
	}

	public void setLogo(Part logo) {
		this.logo = logo;
	}
	
	public boolean isLoadFile() {
		return loadFile;
	}

	public void setLoadFile(boolean loadFile) {
		this.loadFile = loadFile;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String submitForm(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		imgPersistence.delete(ImagePersistence.getInstance().getTmpFilePath());
		if(isUpdating()){
			imgPersistence.copy(getParceiro().getLogo().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		if((getLogo().getSize() != 0)){
			uploadFile();
		}else if(!isUpdating()){
			getParceiro().setLogo(new ImagePath(ImagePersistence.getInstance().getServerPath()+"parceiroDefault.png"));
			imgPersistence.copy(getParceiro().getLogo().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		return "confimarParceiro";
	}
	public void uploadFile(){
		setLoadFile((getLogo().getSize() != 0));
		String formato = getLogo().getContentType();
		formato = formato.substring(formato.indexOf("/")+1);
		ImagePersistence.getInstance().persist(getLogo(),null, formato);
	}
	public String confirmarCadastro(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		String simplePath = null;
		if(getParceiro() != null && getParceiro().getLogo()!= null){
			simplePath = getParceiro().getLogo().getSimplePath();
		}
		if(!(simplePath != null && simplePath.endsWith("parceiroDefault.png"))){
			String path = imgPersistence.rename(imgPersistence.getTmpFilePath(),null);
			getParceiro().setLogo(new ImagePath(path));
		}
		getDaoParceiro().add(getParceiro());
		clear();
		return "listarParceiro";
	}
	
	public String confirmarAlteracao(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		if(isLoadFile()){
			String path = getParceiro().getLogo().getPath();
			String newPath = imgPersistence.rename(imgPersistence.getTmpFilePath(), null);
			getParceiro().setLogo(new ImagePath(newPath));
			if(!path.endsWith("parceiroDefault.png")){
				imgPersistence.delete(path);
			}
		}
		getDaoParceiro().add(getParceiro());
		clear();
		return "listarParceiro";
	}
	public String cancelar(){
		clear();
		return "principal";
	}
	public void clear(){
		setParceiro(new Parceiro());
		setUpdating(false);
		setLoadFile(false);
		setLogo(null);
	}
	
	public List<TipoParceiro> getTiposParceiros(){
		return Arrays.asList(TipoParceiro.values());
	}
}
