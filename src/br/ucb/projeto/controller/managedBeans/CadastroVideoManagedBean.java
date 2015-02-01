package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.projeto.model.DAOS.VideoDAO;
import br.ucb.projeto.model.beans.Video;
@SessionScoped
@ManagedBean(name  = "videoBean")
public class CadastroVideoManagedBean {
	private Video video;
	private VideoDAO daoVideo;
	private boolean updating;
	
	public CadastroVideoManagedBean() {
		setDaoVideo(new VideoDAO());
		setVideo(new Video());
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public VideoDAO getDaoVideo() {
		return daoVideo;
	}
	public void setDaoVideo(VideoDAO daoVideo) {
		this.daoVideo = daoVideo;
	}
	public boolean isUpdating() {
		return updating;
	}
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	public void clear(){
		setDaoVideo(new VideoDAO());
		setVideo(new Video());
		setUpdating(false);
	}
	
	public String alterar(){
		getDaoVideo().update(getVideo());
		clear();
		return "listarVideo";
	}
	
	public String cadastrar(){
		getDaoVideo().add(getVideo());
		clear();
		return "listarVideo";
	}
	
	public String cancelar(){
		clear();
		return "principal";
	}
}
