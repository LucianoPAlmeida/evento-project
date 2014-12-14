package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.talp.model.DAOS.PalestranteDAO;
@Path("/palestrantes")
public class PalestranteResource {
	private PalestranteDAO daoPalestrante;
	
	public PalestranteResource(){
		setDaoPalestrante(new PalestranteDAO());
	}
	
	public PalestranteDAO getDaoPalestrante() {
		return daoPalestrante;
	}

	public void setDaoPalestrante(PalestranteDAO daoPalestrante) {
		this.daoPalestrante = daoPalestrante;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Palestrante> getAll(){
		return getDaoPalestrante().getAll(); 
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Palestrante find(@QueryParam("id") Integer id){
		return getDaoPalestrante().find(id);
	}
	
}
