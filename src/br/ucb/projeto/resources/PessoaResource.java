package br.ucb.projeto.resources;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.ucb.projeto.util.EmailSender;
import br.ucb.projeto.util.MessageGenerator;

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
		return getDaoPessoa().findByEvento(idEvento,true);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Pessoa find(@QueryParam("email")String email){
		Pessoa pessoa = getDaoPessoa().find(email);
		return pessoa;
	}
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/cadastro")
	public void cadastro(@FormParam("mail")String email,@FormParam("name")String nome,@FormParam("phone")String telefone,@FormParam("event")List<String> codigosEventos){
		Map<Integer,Long> eventosOrdem = new HashMap<Integer, Long>();
		for(String cod: codigosEventos){
			Integer valor = Integer.parseInt(cod);
			eventosOrdem.put(valor,new GregorianCalendar().getTimeInMillis());
		}
		Pessoa pessoa = new Pessoa(nome, email, telefone, eventosOrdem);
		System.out.println(pessoa);
		getDaoPessoa().update(pessoa,false);
		pessoa = getDaoPessoa().find(pessoa.getEmail());
		EmailSender sender = new EmailSender("The Wall Break - Confirmação de Inscrição",MessageGenerator.generateText(pessoa), "thewbreak@gmail.com","The Wall Break","matrizsalak262", pessoa.getEmail());
		sender.enviar();
	}
}
