package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.PessoaDAO;
import br.ucb.projeto.model.beans.Pessoa;
import br.ucb.projeto.model.enuns.PessoaComparator;

@Path("pessoas")
public class PessoaResource {
	private PessoaDAO daoPessoa;
	
	public PessoaResource() {
		setDaoPessoa(new PessoaDAO());
	}
	
	
	public PessoaDAO getDaoPessoa() {
		return daoPessoa;
	}


	public void setDaoPessoa(PessoaDAO daoPessoa) {
		this.daoPessoa = daoPessoa;
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> getAll(@QueryParam("evento")Integer idEvento){
		if(idEvento == null){
			return getDaoPessoa().getAll(PessoaComparator.POR_DATA_INSCRICAO);
		}
		return getDaoPessoa().findByEvento(idEvento);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Pessoa find(@QueryParam("id")String id){
		Pessoa pessoa = getDaoPessoa().find(id);
		return pessoa;
	}
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/cadastro")
	public void cadastro(@FormParam("email")String email,@FormParam("name")String nome,@FormParam("phone")String telefone,@FormParam("event")List<String> codigosEventos){
		Pessoa pessoa = new Pessoa(nome, email, telefone, codigosEventos);
		System.out.println(pessoa);
		getDaoPessoa().update(pessoa);
	}
	
}
