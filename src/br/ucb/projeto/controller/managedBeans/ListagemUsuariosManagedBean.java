package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.projeto.model.DAOS.UsuarioDAO;
@RequestScoped
@ManagedBean(name = "listagemUsuarios")
public class ListagemUsuariosManagedBean {
	private UsuarioDAO daoUsuario;
	
	public ListagemUsuariosManagedBean(){
		setDaoUsuario(new UsuarioDAO());
	}
	public UsuarioDAO getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(UsuarioDAO daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	
	public String deletarUsuario(String id){
		getDaoUsuario().delete(id);
		return null;
	}
}
