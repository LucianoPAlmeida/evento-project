package br.ucb.projeto.controller.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexManagedBean {
	public static final String APPLICATION_DOMAIN = "192.168.1.8:8080";
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
	
	public String getAplicationDomain(){
		return APPLICATION_DOMAIN;
	}
	
}
