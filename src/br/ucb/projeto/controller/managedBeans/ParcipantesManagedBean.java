package br.ucb.projeto.controller.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.projeto.controller.generator.CSVGenerator;
import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.DAOS.PessoaDAO;
import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.Pessoa;


@ManagedBean(name = "participanteBean")	
public class ParcipantesManagedBean {
	private PessoaDAO daoPessoa;
	private EventoDAO daoEvento;
	private Evento eventoSelecionado;
	public ParcipantesManagedBean() {
		setDaoPessoa(new PessoaDAO());
		setDaoEvento(new EventoDAO());
	}

	public PessoaDAO getDaoPessoa() {
		return daoPessoa;
	}

	public void setDaoPessoa(PessoaDAO daoPessoa) {
		this.daoPessoa = daoPessoa;
	}
	
	
	public EventoDAO getDaoEvento() {
		return daoEvento;
	}

	public void setDaoEvento(EventoDAO daoEvento) {
		this.daoEvento = daoEvento;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public String baixarArquivo(){
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator);
		String filePath = path+"participantes.csv";
		try {
			Files.deleteIfExists(Paths.get(filePath));
			CSVGenerator generator = new CSVGenerator(filePath);
			boolean sucess = generator.generateFile(getDaoPessoa().findByEvento(getEventoSelecionado().getId()),Pessoa.class);
			generator.close();
			if(sucess){
				enviarArquivo(filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void enviarArquivo(String filePath){
		FileInputStream in = null;
		try {
			HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("text/plain"); 
			response.setHeader("Content-Disposition", "attachment; filename= Inscritos Evento - "+getEventoSelecionado().getTitle()+".csv"); 
			in = new FileInputStream(filePath); 
            PrintWriter output = response.getWriter(); 
            int bit = 0;
            while(bit != -1){
            	bit = in.read();
            	if(bit != -1)
            		output.write(bit);
            } 
            if(in != null)
            	in.close();
            FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
