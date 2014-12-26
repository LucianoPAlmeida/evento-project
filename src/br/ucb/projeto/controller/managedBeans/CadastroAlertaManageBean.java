package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.projeto.model.beans.Alerta;
import br.ucb.talp.model.DAOS.AlertaDAO;
@SessionScoped
@ManagedBean(name = "alertaBean")
public class CadastroAlertaManageBean {
	
	private Alerta alerta;
	private boolean updating;
	private AlertaDAO daoAlerta;
	
	public CadastroAlertaManageBean(){
		setAlerta(new Alerta());
		setDaoAlerta(new AlertaDAO());
	}
	public Alerta getAlerta() {
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	public boolean isUpdating() {
		return updating;
	}
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	
	public AlertaDAO getDaoAlerta() {
		return daoAlerta;
	}
	public void setDaoAlerta(AlertaDAO daoAlerta) {
		this.daoAlerta = daoAlerta;
	}
	public String cadastrar(){
		return null;
	}
	public String alterar(){
		return null;
	}
	public void clear(){
		setAlerta(new Alerta());
		setUpdating(false);
	}
}
