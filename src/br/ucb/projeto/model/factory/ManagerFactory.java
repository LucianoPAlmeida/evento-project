package br.ucb.projeto.model.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ManagerFactory {
	
	private static EntityManager em;
	private ManagerFactory(){
		
	}
	public static EntityManager getInstance(){
		if(em == null){
			em = Persistence.createEntityManagerFactory("EVENTO_UP").createEntityManager();
		}
		return em;
	}
	
}
