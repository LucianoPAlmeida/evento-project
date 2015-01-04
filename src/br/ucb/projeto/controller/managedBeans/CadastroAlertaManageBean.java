package br.ucb.projeto.controller.managedBeans;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.ucb.projeto.model.beans.Alerta;
import br.ucb.projeto.util.DateUtil;
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
	
	public String getDataAux() {
		if(!isUpdating()){
			return DateUtil.stringFromDate(new GregorianCalendar());
		}else if(getAlerta() != null){
			return DateUtil.stringFromDate(getAlerta().getData());
		}else{
			return null;
		}
	}
	
	public String getDataMinima(){
		Calendar data = new GregorianCalendar();
		return DateUtil.stringFromDate(data);
	}
	public String cadastrar(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String data = request.getParameter("dataAlerta");
		System.out.println(data);
		getAlerta().setData(DateUtil.dateFromString(data));
		getDaoAlerta().add(getAlerta());
		clear();
		return "listarAlerta";
	}
	public String alterar(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String data = request.getParameter("dataAlerta");
		getAlerta().setData(DateUtil.dateFromString(data));
		getDaoAlerta().update(getAlerta());
		clear();
		return "listarAlerta";
	}
	public String cancelar(){
		clear();
		return "principal";
	}
	public void clear(){
		setAlerta(new Alerta());
		setUpdating(false);
	}
}
