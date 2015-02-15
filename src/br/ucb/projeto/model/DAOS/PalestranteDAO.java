package br.ucb.projeto.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.projeto.model.persistense.ImagePersistence;

public class PalestranteDAO{
	EntityManager entityManager;
	public PalestranteDAO() {
		setEntityManager(ManagerFactory.getInstance());
	}

	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Palestrante entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public Palestrante find(Object key) {
		return getEntityManager().find(Palestrante.class, key);
	}

	public List<Palestrante> getAll() {
		TypedQuery<Palestrante> query = getEntityManager().createNamedQuery("getAllPalestrantes", Palestrante.class);
		return query.getResultList();
	}

	public void update(Palestrante entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}

	public void delete(Object id,boolean deleteImage) {
		Palestrante palestrante = find(id);
		if(deleteImage){
			if(palestrante.getPhoto() != null){
				ImagePersistence imgPersistence = ImagePersistence.getInstance();
				imgPersistence.delete(palestrante.getPhoto().getPath());
			}
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(palestrante);
		getEntityManager().getTransaction().commit();
	}
	public int getQuantidade(){
		return getAll().size();
	}
	
	public boolean isEmpty(){
		return (getQuantidade() == 0);
	}
}
