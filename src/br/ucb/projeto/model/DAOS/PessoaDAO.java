package br.ucb.projeto.model.DAOS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.model.comparators.EventQueueComparator;
import br.ucb.projeto.model.beans.Evento;
import br.ucb.projeto.model.beans.Pessoa;
import br.ucb.projeto.model.factory.ManagerFactory;

public class PessoaDAO {
	private EntityManager entityManager;
	public PessoaDAO() {
		setEntityManager(ManagerFactory.getInstance());
	}
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void add(Pessoa pessoa){
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(pessoa);
		getEntityManager().getTransaction().commit();
	}
	
	public void update(Pessoa pessoa,boolean relaceEventos){
		if(!relaceEventos){
			Pessoa p = find(pessoa.getEmail());
			if(p != null){
				for(Integer key : p.getEventoEnumeroIncricao().keySet()){
					pessoa.getEventoEnumeroIncricao().put(key, p.getEventoEnumeroIncricao().get(key));
				}
			}
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(pessoa);
		getEntityManager().getTransaction().commit();
	}
	
	public Pessoa find(String email){
		return getEntityManager().find(Pessoa.class, email);
	}
	
	public void delete(String rg){
		Pessoa pessoa = find(rg);
		delete(pessoa);
	}
	
	public void delete(Pessoa pessoa){
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(pessoa);
		getEntityManager().getTransaction().commit();
	}
	
	public List<Pessoa> getAll(){
		TypedQuery<Pessoa> query = getEntityManager().createNamedQuery("allPessoas",Pessoa.class);
		return query.getResultList();
	}
	public List<Pessoa> getAll(Comparator<Pessoa> comparator){
		List<Pessoa> list = getAll();
		Collections.sort(list, comparator);
		return list;
	}
	public List<Pessoa> findByEvento(Integer idEvento,boolean sorted){
		List<Pessoa> ret = new ArrayList<Pessoa>();
		for(Pessoa pessoa : getAll()){
			if(pessoa.contemEvento(idEvento)){
				ret.add(pessoa);
			}
		}
		if (sorted) {
			Collections.sort(ret,new EventQueueComparator(idEvento));
		}
		return ret;
	}
	public int getQuantidade(){
		return getAll().size();
	}
	
	public boolean isEmpty(){
		return (getQuantidade() == 0);
	}
	
	public int quantidadeParticipantes(Evento evento){
		int cont = 0;
		for(Pessoa pessoa : getAll()){
			if(pessoa.contemEvento(evento.getId())){
				cont++;
			}
		}
		return cont;
	}
}
