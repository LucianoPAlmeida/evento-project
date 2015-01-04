package br.ucb.talp.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Alerta;
import br.ucb.projeto.model.factory.ManagerFactory;

public class AlertaDAO {
	private EntityManager entityManager;
	
	public AlertaDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Alerta alerta) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(alerta);
		getEntityManager().getTransaction().commit();
	}

	public Alerta find(Object key) {
		return getEntityManager().find(Alerta.class,key);
	}

	public List<Alerta> getAll() {
		TypedQuery<Alerta> query = getEntityManager().createNamedQuery("allAlertas",Alerta.class);
		return query.getResultList();
	}

	public void update(Alerta entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void delete(Integer id) {
		Alerta alerta = find(id);
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(alerta);
		getEntityManager().getTransaction().commit();
	}
	
}
