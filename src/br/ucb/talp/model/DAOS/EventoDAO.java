package br.ucb.talp.model.DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.talp.model.persistense.ImagePersistence;

public class EventoDAO{
	private EntityManager entityManager;
	
	public EventoDAO() {
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(Evento entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public Evento find(Object key) {
		return getEntityManager().find(Evento.class,key);
	}

	public List<Evento> getAll() {
		TypedQuery<Evento> query = getEntityManager().createNamedQuery("getAllEventos",Evento.class);
		return query.getResultList();
	}

	public void update(Evento entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void delete(Object id,boolean deleteImage) {
		Evento evento = find(id);
		if(deleteImage){
			ImagePersistence imgPersistence = ImagePersistence.getInstance();
			imgPersistence.delete(evento.getPhoto().getPath());
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(evento);
		getEntityManager().getTransaction().commit();
	}
	public List<Evento> getEventosSemPonto(MapPointDAO daoPontos){
		List<Evento> list = new ArrayList<Evento>();
		for(Evento evento : getAll()){
			if(!daoPontos.isEventoMapeado(evento.getId())){
				list.add(evento);
			}
		}
		return list;
	}

}
