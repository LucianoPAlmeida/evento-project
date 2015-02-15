package br.ucb.projeto.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Like;
import br.ucb.projeto.model.factory.ManagerFactory;

public class LikeDAO{
	private EntityManager entityManager;
	
	public LikeDAO() {
		setEntityManager(ManagerFactory.getInstance());
	}

	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Like entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public Like find(Object key) {
		return getEntityManager().find(Like.class, key);
	}

	public List<Like> getAll() {
		TypedQuery<Like> query = getEntityManager().createNamedQuery("allLikes",Like.class);
		return query.getResultList();
	}


	public void update(Like entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();		
	}


	public void delete(Object id) {
		Like like = find(id);
		if(like != null){
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(like);
			getEntityManager().getTransaction().commit();
		}	
	}
	public List<Like> likesByUser(Integer idUser){
		TypedQuery<Like> query = getEntityManager().createNamedQuery("likesByUser",Like.class);
		query.setParameter("id", idUser);
		return query.getResultList();
	}
	
	public List<Like> likesByEvento(Integer idEvento){
		TypedQuery<Like> query = getEntityManager().createNamedQuery("likesByEvento",Like.class);
		query.setParameter("id", idEvento);
		return query.getResultList();
	}
	
	public void delete(Integer idUser,Integer idEvento){
		getEntityManager().getTransaction().begin();
		getEntityManager().createQuery("delete from Like a where a.idUser="+idUser+" and a.idEvento="+idEvento).executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	public int getQuantidade(){
		return getAll().size();
	}
	
	public boolean isEmpty(){
		return (getQuantidade() == 0);
	}
	
}
