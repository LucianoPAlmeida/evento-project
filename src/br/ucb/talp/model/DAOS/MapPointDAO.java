package br.ucb.talp.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.MapPoint;
import br.ucb.projeto.model.factory.ManagerFactory;

public class MapPointDAO{
	EntityManager entityManager;
	public MapPointDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(MapPoint entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public MapPoint find(Object key) {
		return getEntityManager().find(MapPoint.class,key);
	}

	public List<MapPoint> getAll() {
		TypedQuery<MapPoint> query = getEntityManager().createNamedQuery("allMapLocais",MapPoint.class);
		return query.getResultList();
	}

	public void update(MapPoint entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}

	public void delete(Object id) {
		MapPoint entity = find(id);
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();
	}
	public void deleteAll(){
		getEntityManager().getTransaction().begin();
		getEntityManager().createQuery("delete from MapPoint").executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	public boolean isEventoMapeado(Integer idEvento){
		for(MapPoint mp : getAll()){
			if(mp.getIdEvento().intValue() == idEvento.intValue()) return true;
		}
		return false;
	}
}
