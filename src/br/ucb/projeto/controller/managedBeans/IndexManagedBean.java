package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexManagedBean {
	
	public IndexManagedBean() {
	}
	
	public String cadastrarPalestrante(){ 
		return "cadastroPalestrante";
	}
	public String cadastrarEvento(){
		return "cadastroEvento";
	}
	public void clear(){
	}
	
}
