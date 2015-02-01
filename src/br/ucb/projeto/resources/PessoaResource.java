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
			return getDaoPessoa().getAll();
		}
		return getDaoPessoa().findByEvento(idEvento);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Pessoa find(@QueryParam("rg")String rg){
		Pessoa pessoa = getDaoPessoa().find(rg);
		System.out.println(pessoa);
		return pessoa;
	}
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/cadastro")
	public void cadastro(@FormParam("rg")String rg,@FormParam("name")String nome,
			@FormParam("surname")String sobreNome,@FormParam("phone")String telefone,
			@FormParam("university")String universidade,@FormParam("course")String curso,
			@FormParam("semester")String semestre,@FormParam("job")String emprego,
			@FormParam("workplace")String localTrabalho,@FormParam("event")List<String> codigosEventos){
		Pessoa pessoa = new Pessoa(rg, nome, sobreNome, telefone, universidade, curso,(!semestre.isEmpty())?Integer.parseInt(semestre):0, emprego, localTrabalho, codigosEventos);
		System.out.println(pessoa);
		getDaoPessoa().update(pessoa);
	}
	
}
