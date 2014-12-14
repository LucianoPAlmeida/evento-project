package br.ucb.projeto.controller.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.ucb.projeto.model.beans.Usuario;
import br.ucb.talp.model.DAOS.UsuarioDAO;

@ManagedBean(name = "alterarSenhaBean")
@RequestScoped
public class AlterarSenhaManageBean {
	private String senhaAtual;
	private String novaSenha;
	private String confirmacaoSenha;
	
	public AlterarSenhaManageBean(){
		
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public String alterar(){
		HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		FacesMessage mensagem = null;
		if(!user.getSenha().equals(getSenhaAtual())){
		   mensagem = new FacesMessage("Sua senha está incorreta!");
		}else if(!getNovaSenha().equals(getConfirmacaoSenha())){
			mensagem = new FacesMessage("A nova senha e a confirmação não correspondem!");
		}else{
			UsuarioDAO daoUsuario = new UsuarioDAO();
			daoUsuario.alterarSenha(user.getEmail(),getSenhaAtual(),getNovaSenha());
			mensagem = new FacesMessage("Senha alterada com sucesso!");
		}
		FacesContext.getCurrentInstance().addMessage(null,mensagem);
		return null;
	}
	
}
