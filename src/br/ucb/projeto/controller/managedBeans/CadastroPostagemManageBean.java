package br.ucb.projeto.controller.managedBeans;

import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.projeto.model.DAOS.PostagemDAO;
import br.ucb.projeto.model.beans.Postagem;
@SessionScoped
@ManagedBean(name = "postagemBean")
public class CadastroPostagemManageBean {
	private Postagem postagem;
	private PostagemDAO daoPostagem;
	private boolean updating;
	
	public CadastroPostagemManageBean() {
		setDaoPostagem(new PostagemDAO());
		setPostagem(new Postagem());
	}
	
	public Postagem getPostagem() {
		return postagem;
	}
	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
	public PostagemDAO getDaoPostagem() {
		return daoPostagem;
	}
	public void setDaoPostagem(PostagemDAO daoPostagem) {
		this.daoPostagem = daoPostagem;
	}
	public boolean isUpdating() {
		return updating;
	}
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	
	public String postar(){
		getPostagem().setData(new GregorianCalendar());
		getDaoPostagem().add(getPostagem());
		clear();
		return "listarPostagem";
	}
	public String alterar(){
		getPostagem().setData(new GregorianCalendar());
		getDaoPostagem().update(getPostagem());
		clear();
		return "listarPostagem";
	}
	public String cancelar(){
		clear();
		return "principal";
	}
	public void clear(){
		setPostagem(new Postagem());
		setUpdating(false);		
	}
	
}
