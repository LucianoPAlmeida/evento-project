package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import br.projeto.controller.annotatios.CSVRootElement;
import br.projeto.controller.annotatios.CSVTransient;
@Entity
@Table(name = "tb_pessoas")
@NamedQueries({
	@NamedQuery(name = "allPessoas",query = "select a from Pessoa a")
})
@XmlRootElement
@CSVRootElement
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 8019175132090316786L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String nome;
	private String telefone;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataInscricao;
	@ElementCollection
	private List<String> codigosEventos;
	
	public Pessoa() {
		setDataInscricao(new GregorianCalendar());
	}
	
	public Pessoa(String nome, String email, String telefone, List<String> codigosEventos) {
		this();
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		setCodigosEventos(codigosEventos);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@CSVTransient
	public Calendar getDataInscricao() {
		return dataInscricao;
	}
	
	public void setDataInscricao(Calendar dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@CSVTransient
	public List<String> getCodigosEventos() {
		return codigosEventos;
	}

	public void setCodigosEventos(List<String> codigosEventos) {
		this.codigosEventos = codigosEventos;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof Pessoa){
			Pessoa p =(Pessoa)obj;
			return getId().equals(p.getId());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + getId() + ", email=" + getEmail() + ", nome=" + getNome()
				+ ", telefone=" + getTelefone() + ", dataInscricao=" + getDataInscricao()
				+ ", codigosEventos=" + getCodigosEventos() + "]";
	}
	public boolean contemEvento(Integer idEvento){
		if(idEvento == null) return false;
		for(String id : getCodigosEventos()){
			if(id.matches("\\d+")){
				if(idEvento.intValue() == Integer.parseInt(id)) return true;
			}
		}
		return false;
	}
}
