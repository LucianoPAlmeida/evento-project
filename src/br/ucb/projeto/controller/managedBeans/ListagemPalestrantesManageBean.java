package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.util.FacesUtil;
import br.ucb.talp.model.DAOS.PalestranteDAO;
@RequestScoped
@ManagedBean(name = "palestranteListagem")
public class ListagemPalestrantesManageBean {
	private PalestranteDAO daoPalestrante;
	public ListagemPalestrantesManageBean(){
		setDaoPalestrante(new PalestranteDAO());
	}
	
	public PalestranteDAO getDaoPalestrante() {
		return daoPalestrante;
	}

	public void setDaoPalestrante(PalestranteDAO daoPalestrante) {
		this.daoPalestrante = daoPalestrante;
	}
	public String alterar(String id){
		Palestrante palestrante = getDaoPalestrante().find(Integer.parseInt(id));
		CadastroPalestranteManagedBean cpMb =(CadastroPalestranteManagedBean)FacesUtil.getManagedBean("palestranteBean",CadastroPalestranteManagedBean.class);
		cpMb.setPalestrante(palestrante);
		cpMb.setUpdating(true);
		return "cadastroPalestrante";
	}
	
	public String excluir(String id){
		Palestrante p = getDaoPalestrante().find(Integer.parseInt(id));
		getDaoPalestrante().delete(Integer.parseInt(id),!p.getPhoto().getSimplePath().endsWith("palestranteDefalut.png"));
		return null;
	}
}
