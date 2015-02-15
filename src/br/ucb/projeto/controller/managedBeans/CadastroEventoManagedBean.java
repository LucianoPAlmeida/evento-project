package br.ucb.projeto.controller.managedBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.DAOS.PalestranteDAO;
import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.Palestra;
import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.model.beans.WorkShop;
import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.model.enuns.LocalEvento;
import br.ucb.projeto.model.persistense.ImagePersistence;
import br.ucb.projeto.util.DateUtil;
@SessionScoped
@ManagedBean(name = "eventoBean")
public class CadastroEventoManagedBean {
	//Campos Formulário
	private String titulo;
	private String descricao;
	private LocalEvento localEvento;
	private EventType tipoEvento;
	private Palestrante palestrante;
	
	private Evento evento;
	private Part file;
	private String horaEvento;
	private String minutoEvento;
	private boolean updating,loadFile;
	private String tmpPath;
	public GregorianCalendar dataAux;
	private PalestranteDAO daoPalestrante;
	
	public CadastroEventoManagedBean(){
		setDaoPalestrante(new PalestranteDAO());
		setTmpPath("http://"+IndexManagedBean.APPLICATION_DOMAIN+"/EventoProject/images/"+ImagePersistence.getInstance().getTmpFileName()+".png");
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalEvento getLocalEvento() {
		return localEvento;
	}

	public void setLocalEvento(LocalEvento localEvento) {
		this.localEvento = localEvento;
	}
	
	public EventType getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(EventType tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
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
	
	public PalestranteDAO getDaoPalestrante() {
		return daoPalestrante;
	}

	public void setDaoPalestrante(PalestranteDAO daoPalestrante) {
		this.daoPalestrante = daoPalestrante;
	}

	public void clear(){
		setTitulo(null);
		setDescricao(null);
		setLocalEvento(LocalEvento.FNAC);
		setTipoEvento(EventType.PALESTRA);
		setPalestrante(null);
		setHoraEvento("00");
		setMinutoEvento("00");
		setLoadFile(false);
		setUpdating(false);
		ImagePersistence.getInstance().delete(ImagePersistence.getInstance().getTmpFilePath());
	}
	public String getDataString(){
		if(getDataAux() == null || !isUpdating())
			setDataAux(new GregorianCalendar());
		return DateUtil.stringFromDate(getDataAux());
	}
	public GregorianCalendar getDataAux() {
		return dataAux;
	}
	public void setDataAux(GregorianCalendar dataAux) {
		this.dataAux = dataAux;
	}
	
	public void validateRequiredPalestrante(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(getTipoEvento() == EventType.PALESTRA && value == null){
			throw new ValidatorException(new FacesMessage("Por favor selecione o palestrante!"));
		}
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
			if(getTipoEvento() == EventType.PALESTRA){
				setEvento(new Palestra(getTitulo(), getDescricao(),null,null,getPalestrante()));
				getEvento().setLocal(getLocalEvento());
			}else if(getTipoEvento() == EventType.WORKSHOP){
				setEvento(new WorkShop(getTitulo(), getDescricao(), null, null));
				getEvento().setLocal(getLocalEvento());
			}
			getEvento().setPhoto(new ImagePath(ImagePersistence.getInstance().getServerPath()+"eventoDefault.png"));
			imgPersistence.copy(getEvento().getPhoto().getPath(), ImagePersistence.getInstance().getTmpFilePath(), false);
		}
		if(!preencherData()){
			return null;
		}
		if(getEvento() instanceof Palestra){
			Palestra p = (Palestra)getEvento();
			p.setPalestrante(getPalestrante());
		}
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
	public boolean preencherData(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String data = request.getParameter("dataEvento");
		if(data.isEmpty() || !data.matches("\\d{4}-\\d{2}-\\d{2}")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Informe a data no formato yyyy-mm-dd Ex:"+DateUtil.stringFromDate(new GregorianCalendar())+"(hoje)"));
			return false;
		}else{
			getEvento().setData(DateUtil.dateFromString(data));
			Calendar dataEvento = getEvento().getData();
			dataEvento.set(Calendar.HOUR_OF_DAY,Integer.parseInt(getHoraEvento()));
			dataEvento.set(Calendar.MINUTE,Integer.parseInt(getMinutoEvento()));
		}
		return true;
	}
	public List<EventType> allEventos(){
		return Arrays.asList(EventType.values());
	}
	public List<LocalEvento> allLocais(){
		return Arrays.asList(LocalEvento.values());
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
	public void addEventoToUpdate(Evento evento){
		setTitulo(evento.getTitle());
		setDescricao(evento.getSummary());
		setTipoEvento(evento.getTipo());
		setLocalEvento(evento.getLocal());
		setEvento(evento);
	}
	public String voltar(){
		clear();
		return "principal";
	}
}
