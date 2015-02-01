package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.projeto.controller.annotatios.CSVElement;
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
	private String rg;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String universidade;
	private String curso;
	private Integer semestre;
	private String emprego;
	private String localTrabalho;
	@ElementCollection
	private List<String> codigosEventos;
	
	public Pessoa() {
	}
	
	public Pessoa(String rg, String nome, String sobrenome, String telefone,
			String universidade, String curso, Integer semestre,
			String emprego, String localTrabalho, List<String> codigosEventos) {
		setRg(rg);
		setNome(nome);
		setSobrenome(sobrenome);
		setTelefone(telefone);
		setUniversidade(universidade);
		setCurso(curso);
		setSemestre(semestre);
		setEmprego(emprego);
		setLocalTrabalho(localTrabalho);
		setCodigosEventos(codigosEventos);
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	@XmlTransient
	@CSVTransient
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlTransient
	@CSVTransient
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	@XmlElement
	@CSVElement
	public String getNomeCompleto(){
		return getNome()+" "+getSobrenome();
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getUniversidade() {
		return universidade;
	}
	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	public String getEmprego() {
		return emprego;
	}
	public void setEmprego(String emprego) {
		this.emprego = emprego;
	}
	public String getLocalTrabalho() {
		return localTrabalho;
	}
	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}
	@CSVTransient
	public List<String> getCodigosEventos() {
		return codigosEventos;
	}

	public void setCodigosEventos(List<String> codigosEventos) {
		this.codigosEventos = codigosEventos;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Pessoa){
			Pessoa p = (Pessoa)obj;
			return getRg().equals(p.getRg());
		}
		return false;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getRg() == null) ? 0 : getRg().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Pessoa [rg=" + getRg() + ", nome=" + getNome() + ", sobrenome="
				+ getSobrenome() + ", telefone=" + getTelefone() + ", universidade="
				+ getUniversidade() + ", curso=" + getCurso() + ", semestre=" + getSemestre()
				+ ", emprego=" + getEmprego() + ", localTrabalho=" + getLocalTrabalho()
				+ ", codigosEventos= "+getCodigosEventos()+"]";
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
