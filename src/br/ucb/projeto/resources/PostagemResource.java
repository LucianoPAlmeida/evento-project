package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.PostagemDAO;
import br.ucb.projeto.model.beans.Postagem;

@Path("/postagens")
public class PostagemResource {
	private PostagemDAO daoPostagem;
	public PostagemResource(){
		setDaoPostagem(new PostagemDAO());
	}
	
	public PostagemDAO getDaoPostagem() {
		return daoPostagem;
	}

	public void setDaoPostagem(PostagemDAO daoPostagem) {
		this.daoPostagem = daoPostagem;
	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Postagem find(@QueryParam("id")Integer id){
		return getDaoPostagem().find(id);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Postagem> getAll(){
		return getDaoPostagem().getAll();
	}
}
