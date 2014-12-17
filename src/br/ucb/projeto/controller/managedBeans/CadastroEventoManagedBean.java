package br.ucb.projeto.controller.managedBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.util.DateUtil;
import br.ucb.talp.model.DAOS.EventoDAO;
import br.ucb.talp.model.persistense.ImagePersistence;
@SessionScoped
@ManagedBean(name = "eventoBean")
public class CadastroEventoManagedBean {
	private Evento evento;
	private Part file;
	private String horaEvento;
	private String minutoEvento;
	private boolean updating,loadFile;
	private String tmpPath;
	public GregorianCalendar dataAux;
	public CadastroEventoManagedBean(){
		setEvento(new Evento());
		setTmpPath("http://"+IndexManagedBean.APPLICATION_DOMAIN+"/EventoProject/images/"+ImagePersistence.getInstance().getTmpFileName()+".png");
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public String getHoraEvento() {
		return horaEvento;
	}
	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
	}
	public String getMinutoEvento() {
		return minutoEvento;
	}
	public void setMinutoEvento(String minutoEvento) {
		this.minutoEvento = minutoEvento;
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
	public void clear(){
		setEvento(new Evento());
		setHoraEvento("00");
		setMinutoEvento("00");
		setLoadFile(false);
		setUpdating(false);
		ImagePersistence.getInstance().delete(ImagePersistence.getInstance().getTmpFilePath());
	}
	public String getDataString(){
		if(getDataAux() == null)
			setDataAux(new GregorianCalendar());
		return DateUtil.stringFromDate(getDataAux());
	}
	public GregorianCalendar getDataAux() {
		return dataAux;
	}
	public void setDataAux(GregorianCalendar dataAux) {
		this.dataAux = dataAux;
	}
	public String cadastrar(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		imgPersistence.delete(ImagePersistence.getInstance().getTmpFilePath());
		if(isUpdating()){
			imgPersistence.copy(getEvento().getPhoto().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		if((getFile().getSize() != 0)){
			uploadFile();
		}else if(!isUpdating()){
			getEvento().setPhoto(new ImagePath(ImagePersistence.getInstance().getServerPath()+"eventoDefault.png"));
			imgPersistence.copy(getEvento().getPhoto().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		preencherData();
		return "confirmar";
	}
	public void uploadFile(){
		setLoadFile((getFile().getSize() != 0));
		String formato = getFile().getContentType();
		formato = formato.substring(formato.indexOf("/")+1);
		ImagePersistence.getInstance().persist(getFile(),null, formato);
	}
	public String confirmarCadastro(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		EventoDAO dao = new EventoDAO();
		String simplePath = null;
		if(getEvento() != null && getEvento().getPhoto()!= null){
			simplePath = getEvento().getPhoto().getSimplePath();
		}
		if(!(simplePath != null && simplePath.endsWith("eventoDefault.png"))){
			String path = imgPersistence.rename(imgPersistence.getTmpFilePath(),null);
			getEvento().setPhoto(new ImagePath(path));
		}
		dao.add(getEvento());
		clear();
		return "listarEventos";
	}
	public String confirmarAlteracao(){
		ImagePersistence imgPersistence = ImagePersistence.getInstance();
		EventoDAO dao = new EventoDAO();
		if(isLoadFile()){
			String path = getEvento().getPhoto().getPath();
			String newPath = imgPersistence.rename(imgPersistence.getTmpFilePath(), null);
			getEvento().setPhoto(new ImagePath(newPath));
			if(!path.endsWith("eventoDefault.png")){
				imgPersistence.delete(path);
			}
		}
		dao.update(getEvento());
		clear();
		return "listarEventos";
	}
	public String cancelar(){
		clear();
		return "principal";
	}
	public void preencherData(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String data = request.getParameter("dataEvento");
		System.out.println(data);
		if(data.isEmpty() || !data.matches("\\d{4}-\\d{2}-\\d{2}")){
			data = getDataMinima();
		}
		getEvento().setData(DateUtil.dateFromString(data));
		Calendar dataEvento = getEvento().getData();
		dataEvento.set(Calendar.HOUR_OF_DAY,Integer.parseInt(getHoraEvento()));
		dataEvento.set(Calendar.MINUTE,Integer.parseInt(getMinutoEvento()));
		
	}
	public List<EventType> allEventos(){
		return Arrays.asList(EventType.values());
	}
	
	public List<String> horas(){
		return listaDeNumeros(0, 23);
	}
	
	public List<String> minutos(){
		return listaDeNumeros(0, 59);
	}
	public String getDataMinima(){
		Calendar data = new GregorianCalendar();
		return DateUtil.stringFromDate(data);
	}
	public List<String>listaDeNumeros(int inicio,int fim){
		List<String> list = new ArrayList<String>();
		for(int i = inicio;i <= fim; i++){
			list.add(DateUtil.stringIntComZerosEsquerda(i));
		}
		return list;
	}
	public String voltar(){
		clear();
		return "principal";
	}
}
