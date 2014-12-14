package br.ucb.projeto.controller.managedBeans;

import java.io.File;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.MapPoint;
import br.ucb.talp.model.DAOS.EventoDAO;
import br.ucb.talp.model.DAOS.MapPointDAO;
import br.ucb.talp.model.persistense.ImagePersistence;
@RequestScoped
@ManagedBean(name = "mapaBean")
public class MapaManagedBean {
	private ImagePath mapPath;
	private Part mapImage;
	private MapPointDAO daoMap;
	private Evento evento;
	public MapaManagedBean(){
		setDaoMap(new MapPointDAO());
		setMapPath(new ImagePath(ImagePersistence.getInstance().getServerPath()+"mapa.png"));
	}
	
	public ImagePath getMapPath() {
		return mapPath;
	}

	public void setMapPath(ImagePath mapPath) {
		this.mapPath = mapPath;
	}
	
	public Part getMapImage() {
		return mapImage;
	}

	public void setMapImage(Part mapImage) {
		this.mapImage = mapImage;
	}
	
	public MapPointDAO getDaoMap() {
		return daoMap;
	}

	public void setDaoMap(MapPointDAO daoMap) {
		this.daoMap = daoMap;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isMapaExiste(){
		if(getMapPath() != null){
			return new File(getMapPath().getPath()).exists();
		}
		return false;
	}
	public String actionAddPonto(){
		MapPointDAO dao = new MapPointDAO();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String points = request.getParameter("j_idt5:points");
		if(points != null){
			MapPoint p = new MapPoint(points);
			p.setIdEvento(getEvento().getId());
			dao.add(p);
		}
		return null;
	}
	public String submitImage(){
		String formato = getMapImage().getContentType();
		formato = formato.substring(formato.indexOf("/")+1);
		ImagePersistence imgPersitence = ImagePersistence.getInstance();
		imgPersitence.delete(imgPersitence.getServerPath()+"mapa.png");
		imgPersitence.persist(getMapImage(),"mapa", formato);
		return null;
	}
	
	public String deletarPonto(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ponto = request.getParameter("j_idt5:pointToDelete");
		getDaoMap().delete(Integer.parseInt(ponto));
		return null;
	}
	public List<Evento> getEventosNaoMarcados(){
		return new EventoDAO().getEventosSemPonto(getDaoMap());
	}
	public String alterarMapa(){
		getDaoMap().deleteAll();
		submitImage();
		return null;
	}
}
