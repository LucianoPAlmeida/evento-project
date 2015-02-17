package br.ucb.projeto.model.DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.Palestra;
import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.model.enuns.LocalEvento;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.projeto.model.persistense.ImagePersistence;
import br.ucb.projeto.util.DateUtil;

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
	//Para que o update funcione corretamente o objeto de photo do tipo ImagePath deve vir com o idOriginal.
	public void update(Evento entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity.getPhoto());
		String nativeQuery = "update tb_eventos set type = '"+entity.getTipo().toString()+"'"
				+ ",summary = '"+entity.getSummary()+"',title = '"+entity.getTitle()+"',DATA = '"+DateUtil.dateTimeStringFromDate(entity.getData())+"',"
				+ "LOCAL = "+entity.getLocal().ordinal()+", id_photo = "+entity.getPhoto().getId()+",id_palestrante = "+((entity instanceof Palestra)?((Palestra)entity).getPalestrante().getId():"NULL")+" "
						+ "where ID = "+entity.getId()+";";
		Query query = getEntityManager().createNativeQuery(nativeQuery);
		query.executeUpdate();
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
	
	}
	
	public void delete(Object id,boolean deleteImage) {
		Evento evento = find(id);
		if(deleteImage){
			if(evento.getPhoto() != null){
				ImagePersistence imgPersistence = ImagePersistence.getInstance();
				imgPersistence.delete(evento.getPhoto().getPath());
			}
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
	public List<Evento> getEventosSemPonto(MapPointDAO daoPontos,LocalEvento local){
		List<Evento> list = new ArrayList<Evento>();
		for(Evento e : getEventosSemPonto(daoPontos)){
			if(e.getLocal() == local){
				list.add(e);
			}
		}
		return list;
	}
	public List<Evento> findByLocal(LocalEvento local){
		TypedQuery<Evento> query = getEntityManager().createNamedQuery("findByLocal",Evento.class);
		query.setParameter("local",local);
		return query.getResultList();
	}
	
	public int getQuantidade(){
		return getAll().size();
	}
	
	public boolean isEmpty(){
		return (getQuantidade() == 0);
	}
	public boolean eventoTemPalestrante(Palestrante palestrante){
		if(palestrante != null){
			for(Evento e : getAll()){
				if(e instanceof Palestra){
					Palestra p = (Palestra)e;
					if(p.getPalestrante().equals(palestrante)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
