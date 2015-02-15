package br.ucb.projeto.controller.managedBeans;

import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.DAOS.MapPointDAO;
import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.util.FacesUtil;
@RequestScoped
@ManagedBean(name = "eventoListagem")
public class ListagemEventosManagedBean {
	private EventoDAO daoEvento;
	public ListagemEventosManagedBean(){
		setDaoEvento(new EventoDAO());
	}
	
	public EventoDAO getDaoEvento() {
		return daoEvento;
	}

	public void setDaoEvento(EventoDAO daoEvento) {
		this.daoEvento = daoEvento;
	}
	
	public String alterar(String id){
		Evento e = getDaoEvento().find(Integer.parseInt(id));
		CadastroEventoManagedBean ceMb = FacesUtil.getManagedBean("eventoBean",CadastroEventoManagedBean.class);
		String horario[] = e.getHoraEvento().split(":");
		ceMb.setHoraEvento(horario[0]);
		ceMb.setMinutoEvento(horario[1]);
		if(e.getData().before(new GregorianCalendar())){
			e.setData(new GregorianCalendar());
		}
		ceMb.setDataAux(e.getData());
		ceMb.addEventoToUpdate(e);
		ceMb.setUpdating(true);
		return "cadastroEvento";
	}
	public String excluir(String id){
		if(!new MapPointDAO().isEventoMapeado(Integer.parseInt(id))){
			Evento evento  = getDaoEvento().find(Integer.parseInt(id));
			getDaoEvento().delete(Integer.parseInt(id),!evento.getPhoto().getSimplePath().endsWith("eventoDefault.png"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O evento não pode ser excluido pois está marcado no mapa!"));
		}
		return null;
	}
	
	
}
