package br.ucb.projeto.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Video;
import br.ucb.projeto.model.factory.ManagerFactory;

public class VideoDAO {
private EntityManager entityManager;
	
	public VideoDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Video video) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(video);
		getEntityManager().getTransaction().commit();
	}

	public Video find(Object key) {
		return getEntityManager().find(Video.class,key);
	}

	public List<Video> getAll() {
		TypedQuery<Video> query = getEntityManager().createNamedQuery("allVideos",Video.class);
		return query.getResultList();
	}

	public void update(Video entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void delete(Integer id) {
		Video video = find(id);
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(video);
		getEntityManager().getTransaction().commit();
	}
}
