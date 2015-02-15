package br.ucb.projeto.model.DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.Usuario;
import br.ucb.projeto.model.factory.ManagerFactory;

public class UsuarioDAO {
	
	private EntityManager entityManager;
	
	public UsuarioDAO(){
		setEntityManager(ManagerFactory.getInstance());
	}

	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void add(Usuario user){
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(user);
		getEntityManager().getTransaction().commit();
	}
	
	public Usuario find(String email){
		return getEntityManager().find(Usuario.class, email);
	}
	
	public List<Usuario> getAll(){
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("allUsers", Usuario.class);
		return query.getResultList();
	}
	
	public boolean emailExiste(String email){
		return (find(email) != null);
	}
	
	public Usuario autenticarUsuario(String email,String senha){
		Usuario user = find(email);
		if(user != null && user.getSenha().equals(senha)){
			return user;
		}
		return null;
	}
	
	public void delete(String email){
		Usuario usuario = find(email);
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(usuario);
		getEntityManager().getTransaction().commit();
	}
	private void update(Usuario usuario){
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(usuario);
		getEntityManager().getTransaction().commit();
	}
	public void alterarSenha(String email,String senha,String novaSenha){
		Usuario user = find(email);
		if(user != null && user.getSenha().equals(senha)){
			user.setSenha(novaSenha);
			update(user);
		}
	}
	public int getQuantidade(){
		return getAll().size();
	}
	
	public boolean isEmpty(){
		return (getQuantidade() == 0);
	}
}
