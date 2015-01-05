package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.projeto.model.beans.Video;
import br.ucb.projeto.util.FacesUtil;
import br.ucb.talp.model.DAOS.VideoDAO;

@RequestScoped
@ManagedBean(name = "videoListagem")
public class ListagemVideosManagedBean {
	private VideoDAO daoVideo;
	public ListagemVideosManagedBean() {
		setDaoVideo(new VideoDAO());
	}
	
	public VideoDAO getDaoVideo() {
		return daoVideo;
	}

	public void setDaoVideo(VideoDAO daoVideo) {
		this.daoVideo = daoVideo;
	}
	
	public String editar(Integer id){
		CadastroVideoManagedBean cvMb = (CadastroVideoManagedBean)FacesUtil.getManagedBean("videoBean",CadastroVideoManagedBean.class);
		Video video = getDaoVideo().find(id);
		cvMb.setVideo(video);
		cvMb.setUpdating(true);
		return "cadastrarVideo";
	}
	public String excluir(Integer id){
		getDaoVideo().delete(id);
		return null;
	}
}
