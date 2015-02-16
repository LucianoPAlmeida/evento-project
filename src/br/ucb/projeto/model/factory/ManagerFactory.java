package br.ucb.projeto.model.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerFactory {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ManagerFactory(){
		
	}
	public static EntityManager getInstance(){
		if(em == null){
			emf = Persistence.createEntityManagerFactory("EVENTO_UP");
			em = emf.createEntityManager();
		}
		return em;
	}
}
