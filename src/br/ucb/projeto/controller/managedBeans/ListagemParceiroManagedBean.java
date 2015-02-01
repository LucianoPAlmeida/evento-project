package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.projeto.model.DAOS.ParceiroDAO;
import br.ucb.projeto.model.beans.Parceiro;
import br.ucb.projeto.util.FacesUtil;

@RequestScoped
@ManagedBean(name = "parceiroListagem")
public class ListagemParceiroManagedBean {
	private ParceiroDAO daoParceiro;
	public ListagemParceiroManagedBean() {
		setDaoParceiro(new ParceiroDAO());
	}
	public ParceiroDAO getDaoParceiro() {
		return daoParceiro;
	}

	public void setDaoParceiro(ParceiroDAO daoParceiro) {
		this.daoParceiro = daoParceiro;
	}
	
	public String editar(Integer id){
		CadastroParceiroManagedBean cpMb = (CadastroParceiroManagedBean)FacesUtil.getManagedBean("parceiroBean",CadastroParceiroManagedBean.class);
		Parceiro parceiro = getDaoParceiro().find(id);
		cpMb.setParceiro(parceiro);
		cpMb.setUpdating(true);
		return "cadastrarParceiro";
	}
	
	public String excluir(Integer id){
		Parceiro parceiro = getDaoParceiro().find(id);
		if(parceiro != null && parceiro.getLogo() != null){
			getDaoParceiro().delete(id,!parceiro.getLogo().getSimplePath().endsWith("parceiroDefault.png"));
		}
		return null;
	}
}
