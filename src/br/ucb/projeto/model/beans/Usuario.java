package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ucb.projeto.util.DateUtil;
@Entity
@Table(name = "tb_usuarios")
@NamedQueries({
	@NamedQuery(name = "allUsers",query = "select a from Usuario a")
})
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 8228081884774629569L;
	
	private String nome;
	@Id
	private String email;
	private String senha;
	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar dataCadastro;
	private boolean administrador;
	public Usuario(){
		
	}
	public Usuario(String nome, String email, String senha,
			GregorianCalendar dataCadastro, boolean administrador) {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setDataCadastro(dataCadastro);
		setAdministrador(administrador);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public GregorianCalendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(GregorianCalendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public String getDataCadastroFormatada(){
		if(getDataCadastro() == null) return null;
		int mes = getDataCadastro().get(Calendar.MONTH)+1;
		return DateUtil.stringIntComZerosEsquerda(getDataCadastro().get(Calendar.DAY_OF_MONTH))+"/"+DateUtil.stringIntComZerosEsquerda(mes)+"/"+getDataCadastro().get(Calendar.YEAR);
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
		if(obj != null && obj instanceof Usuario){
			Usuario user = (Usuario) obj;
			return getEmail().equalsIgnoreCase(user.getEmail());
		}
		return false;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + getNome() + ", email=" + getEmail() + ", senha="
				+ getSenha() + ", dataCadastro=" + getDataCadastro() + ", administrador="
				+ isAdministrador() + "]";
	}
	
}
