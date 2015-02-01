package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ucb.projeto.model.DAOS.AlertaDAO;
import br.ucb.projeto.model.beans.Alerta;
import br.ucb.projeto.util.FacesUtil;

@RequestScoped
@ManagedBean(name = "alertaListagem")
public class ListagemAlertasManagedBean {
	private AlertaDAO daoAlerta;
	private Alerta curretAlerta;
	
	public ListagemAlertasManagedBean() {
		setDaoAlerta(new AlertaDAO());
	}
	public AlertaDAO getDaoAlerta() {
		return daoAlerta;
	}
	public void setDaoAlerta(AlertaDAO daoAlerta) {
		this.daoAlerta = daoAlerta;
	}
	public Alerta getCurretAlerta() {
		return curretAlerta;
	}
	public void setCurretAlerta(Alerta curretAlerta) {
		this.curretAlerta = curretAlerta;
	}
	public String alterar(Integer id){
		CadastroAlertaManageBean caMb = (CadastroAlertaManageBean)FacesUtil.getManagedBean("alertaBean",CadastroAlertaManageBean.class);
		Alerta alerta = getDaoAlerta().find(id);
		caMb.setAlerta(alerta);
		caMb.setUpdating(true);
		return "cadastrarAlerta";
	}
	public String excluir(Integer id){
		getDaoAlerta().delete(id);
		return null;
	}
	
	public String detalhar(Integer id){
		setCurretAlerta(getDaoAlerta().find(id));
		return null;
	}
}
