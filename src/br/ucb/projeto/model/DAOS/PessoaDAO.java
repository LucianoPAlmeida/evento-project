package br.ucb.projeto.model.DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public void update(Pessoa pessoa){
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(pessoa);
		getEntityManager().getTransaction().commit();
	}
	
	public Pessoa find(String rg){
		return getEntityManager().find(Pessoa.class, rg);
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
	
	public List<Pessoa> findByEvento(Integer idEvento){
		List<Pessoa> ret = new ArrayList<Pessoa>();
		for(Pessoa pessoa : getAll()){
			if(pessoa.contemEvento(idEvento)){
				ret.add(pessoa);
			}
		}
		return ret;
	}
}
