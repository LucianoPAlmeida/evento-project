package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.ParceiroDAO;
import br.ucb.projeto.model.beans.Parceiro;
import br.ucb.projeto.model.enuns.TipoParceiro;

@Path("/parceiros")
public class ParceiroResource {
	private ParceiroDAO daoParceiro;
	public ParceiroResource() {
		setDaoParceiro(new ParceiroDAO());
	}
	public ParceiroDAO getDaoParceiro() {
		return daoParceiro;
	}
	public void setDaoParceiro(ParceiroDAO daoParceiro) {
		this.daoParceiro = daoParceiro;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Parceiro> getAll(@QueryParam("tipo")String tipo){
		List<Parceiro> list = null;
		if(tipo == null){
			list = getDaoParceiro().getAll();
		}else if(tipo.equalsIgnoreCase("apoio")){
			list = getDaoParceiro().findByTipo(TipoParceiro.APOIO);
		}else if(tipo.equalsIgnoreCase("patrocinador")){
			list = getDaoParceiro().findByTipo(TipoParceiro.PATROCINADOR);
		}
		return list;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Parceiro find(@QueryParam("id")Integer id){
		return getDaoParceiro().find(id);
	}
}
