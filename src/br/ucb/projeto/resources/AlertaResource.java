package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.AlertaDAO;
import br.ucb.projeto.model.beans.Alerta;
import br.ucb.projeto.util.DateUtil;

@Path("alertas")
public class AlertaResource {
	
	private AlertaDAO daoAlerta; 
	
	public AlertaResource() {
		setDaoAlerta(new AlertaDAO());
	}
	
	public AlertaDAO getDaoAlerta() {
		return daoAlerta;
	}


	public void setDaoAlerta(AlertaDAO daoAlerta) {
		this.daoAlerta = daoAlerta;
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alerta> getAll(){ 
		return getDaoAlerta().getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Alerta find(@QueryParam("id") Integer id){
		return getDaoAlerta().find(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/after")
	public List<Alerta> alertaAfterDate(@QueryParam("date")String date){
		if(!date.matches("\\d{4}-\\d{2}-\\d{2}")) return null;
		return getDaoAlerta().alertsAfterDate(DateUtil.dateFromString(date));
	}
	
}
