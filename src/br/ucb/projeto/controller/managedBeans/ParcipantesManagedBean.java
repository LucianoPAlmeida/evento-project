package br.ucb.projeto.controller.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.projeto.controller.generator.CSVGenerator;
import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.DAOS.PessoaDAO;
import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.Pessoa;
import br.ucb.projeto.model.enuns.EventoComparator;


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
		String zipFilePath = path+"participantes.zip";
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String [] eventosSelecionados = request.getParameterValues("selectedEvents"); 
		System.out.println("Eventos selecionados = "+eventosSelecionados);
		if(eventosSelecionados == null || eventosSelecionados.length == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecione no mínimo um evento para o Download!"));
			return null;
		}
		
		try {
			Files.deleteIfExists(Paths.get(zipFilePath));
			ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFilePath));
			for(String id :eventosSelecionados){
				Files.deleteIfExists(Paths.get(filePath));
				setEventoSelecionado(getDaoEvento().find(Integer.parseInt(id)));
				CSVGenerator generator = new CSVGenerator(filePath);
				List<Pessoa> list = getDaoPessoa().findByEvento(Integer.parseInt(id),true);
				for (Pessoa pessoa : list) {
					pessoa.preencherDataField(Integer.parseInt(id));
				}
				boolean sucess = generator.generateFile(list,Pessoa.class);
				if(sucess){
					String name = getEventoSelecionado().getTitle()+".csv";
					name = name.replaceAll("[\\/:;*?\"<>|%,#$!+{}&\\[\\]•']*","");
					outputStream.putNextEntry(new ZipEntry(name));
					FileInputStream in = new FileInputStream(filePath);
					int bit = 0;
					while(bit != -1){
						bit = in.read();
						if(bit != -1){
							outputStream.write(bit);
						}
					}
					in.close();
				}
				generator.close();
			}
			outputStream.close();
			enviarArquivo(zipFilePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	public void enviarArquivo(String filePath){
		FileInputStream in = null;
		try {
			HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename= Participantes.zip"); 
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
	public List<Evento> getEventosOrdenados(){
		List<Evento> lista = getDaoEvento().getAll();
		Collections.sort(lista,EventoComparator.POR_TITLE);
		return lista;
	}
}
