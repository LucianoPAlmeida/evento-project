package br.ucb.talp.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Parceiro;
import br.ucb.projeto.model.enuns.TipoParceiro;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.talp.model.persistense.ImagePersistence;

public class ParceiroDAO {
	private EntityManager entityManager;
	
	public ParceiroDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Parceiro parceiro) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(parceiro);
		getEntityManager().getTransaction().commit();
	}

	public Parceiro find(Object key) {
		return getEntityManager().find(Parceiro.class,key);
	}

	public List<Parceiro> getAll() {
		TypedQuery<Parceiro> query = getEntityManager().createNamedQuery("allParceiros",Parceiro.class);
		return query.getResultList();
	}

	public void update(Parceiro entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void delete(Integer id,boolean deleteImage) {
		Parceiro parceiro = find(id);
		if(deleteImage){
			if(parceiro.getLogo() != null){
				ImagePersistence.getInstance().delete(parceiro.getLogo().getPath());
			}
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(parceiro);
		getEntityManager().getTransaction().commit();
	}
	
	public List<Parceiro> findByTipo(TipoParceiro tipo){
		TypedQuery<Parceiro> query = getEntityManager().createNamedQuery("findByTipo",Parceiro.class);
		query.setParameter("tipo",tipo);
		return query.getResultList();
	}
}
