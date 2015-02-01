package br.ucb.projeto.controller.managedBeans;

import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.ucb.projeto.model.DAOS.UsuarioDAO;
import br.ucb.projeto.model.beans.Usuario;
@RequestScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioManagedBean {
	private Usuario usuario;
	private String confirmacaoSenha;
	private UsuarioDAO daoUsuario;
	
	public UsuarioManagedBean(){
		setUsuario(new Usuario());
		setDaoUsuario(new UsuarioDAO());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(UsuarioDAO daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String cadastrarUsuario(){
		if(!getUsuario().getSenha().equals(getConfirmacaoSenha())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirme a senha corretamente!"));
			return null;
		}else{
			getUsuario().setDataCadastro(new GregorianCalendar());
			getDaoUsuario().add(getUsuario());
			return "listarUsuarios";
		}
		
	}
	
}
