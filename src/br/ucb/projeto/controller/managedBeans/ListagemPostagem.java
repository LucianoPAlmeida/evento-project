package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;

import br.ucb.projeto.model.beans.Postagem;
import br.ucb.projeto.util.FacesUtil;
import br.ucb.talp.model.DAOS.PostagemDAO;

@ManagedBean(name = "postagemListagem")
public class ListagemPostagem {
	private PostagemDAO daoPostagem;
	
	public ListagemPostagem(){
		System.out.println(new PostagemDAO().getAll());
		setDaoPostagem(new PostagemDAO());
	}

	public PostagemDAO getDaoPostagem() {
		return daoPostagem;
	}

	public void setDaoPostagem(PostagemDAO daoPostagem) {
		this.daoPostagem = daoPostagem;
	}
	public String excluir(Integer id){
		getDaoPostagem().delete(id);
		return null;
	}
	public String alterar(Integer id){
		CadastroPostagemManageBean cdpMb = FacesUtil.getManagedBean("postagemBean",CadastroPostagemManageBean.class);
		Postagem postagem = getDaoPostagem().find(id);
		cdpMb.setPostagem(postagem);
		cdpMb.setUpdating(true);
		return "cadastrarPostagem";
	}
}
