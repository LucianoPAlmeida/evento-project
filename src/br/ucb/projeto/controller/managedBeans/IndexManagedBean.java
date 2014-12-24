package br.ucb.projeto.controller.managedBeans;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexManagedBean {
	public static final String APPLICATION_DOMAIN = "192.168.1.4:8080";
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
	public String getRandomValue(){
		return String.valueOf(new Random().nextInt());
	}
}
