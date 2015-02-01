package br.ucb.projeto.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.ucb.projeto.model.beans.Postagem;
import br.ucb.projeto.model.factory.ManagerFactory;

public class PostagemDAO {
private EntityManager entityManager;
	
	public PostagemDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Postagem alerta) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(alerta);
		getEntityManager().getTransaction().commit();
	}

	public Postagem find(Object key) {
		return getEntityManager().find(Postagem.class,key);
	}

	public List<Postagem> getAll() {
		TypedQuery<Postagem> query = getEntityManager().createNamedQuery("allPostagens",Postagem.class);
		return query.getResultList();
	}

	public void update(Postagem entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}
	
	public void delete(Integer id) {
		Postagem postegem = find(id);
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(postegem);
		getEntityManager().getTransaction().commit();
	}
}
