package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.beans.Evento;
import br.ucb.talp.model.DAOS.EventoDAO;
@Path("/eventos")
public class EventoResource {
	private EventoDAO daoEvento;
	
	public EventoResource(){
		setDaoEvento(new EventoDAO());
	}
	public EventoDAO getDaoEvento() {
		return daoEvento;
	}


	public void setDaoEvento(EventoDAO daoEvento) {
		this.daoEvento = daoEvento;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evento> getAll(){
		return getDaoEvento().getAll();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Evento find(@QueryParam("id")String id){
		if(id.matches("[0-9]+")){
			return getDaoEvento().find(Integer.parseInt(id));
		}
		return null;
	}
}
