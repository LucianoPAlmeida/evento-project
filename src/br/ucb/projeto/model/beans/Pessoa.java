package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.projeto.controller.annotatios.CSVElement;
import br.projeto.controller.annotatios.CSVRootElement;
import br.projeto.controller.annotatios.CSVTransient;
import br.ucb.projeto.util.DateUtil;
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
	private String email;
	private String nome;
	private String telefone;
	@Transient
	private String data;
	@ElementCollection
	private Map<Integer,Long> eventoEnumeroIncricao;
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String email, String telefone, Map<Integer,Long> eventoEnumeroIncricao) {
		this();
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		setEventoEnumeroIncricao(eventoEnumeroIncricao);
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@XmlTransient
	@CSVElement(name ="horaCadastro")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@CSVTransient
	public Map<Integer, Long> getEventoEnumeroIncricao() {
		return eventoEnumeroIncricao;
	}

	public void setEventoEnumeroIncricao(Map<Integer, Long> eventoEnumeroIncricao) {
		this.eventoEnumeroIncricao = eventoEnumeroIncricao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
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
			return getEmail().equals(p.getEmail());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Pessoa [email=" + getEmail() + ", nome=" + getNome()
				+ ", telefone=" + getTelefone() + ", eventoEnumeroIncricao=" + getEventoEnumeroIncricao() + "]";
	}
	public boolean contemEvento(Integer idEvento){
		if(idEvento == null || getEventoEnumeroIncricao() == null) return false;
		for(Integer id : getEventoEnumeroIncricao().keySet()){
			if(idEvento.intValue() == id.intValue()) return true;
		}
		return false;
	}
	public void preencherDataField(Integer idEvento){
		Calendar date = getDataIncricao(idEvento);
		setData(DateUtil.dateMMDD(date)+" "+DateUtil.stringTimeFromDate(date));
	}
	public Calendar getDataIncricao(Integer idEvento){
		if(getEventoEnumeroIncricao() != null){
			GregorianCalendar data = new GregorianCalendar();
			data.setTimeInMillis(getEventoEnumeroIncricao().get(idEvento));
			return data;
		}
		return null;
	}
}
