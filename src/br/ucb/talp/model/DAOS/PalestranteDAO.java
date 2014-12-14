package br.ucb.talp.model.DAOS;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ucb.projeto.model.beans.ImagePath;
import br.ucb.projeto.model.beans.Palestrante;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.talp.model.persistense.ImagePersistence;

public class PalestranteDAO{
	EntityManager entityManager;
	public PalestranteDAO() {
		setEntityManager(ManagerFactory.getInstance());
	}

	private EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void add(Palestrante entity,boolean persistImage) {
		if(persistImage){
			ImagePersistence imgPersistence = ImagePersistence.getInstance();
			File file = new File(imgPersistence.getTmpFilePath());
			File newFile = new File(imgPersistence.getServerPath()+imgPersistence.randomName()+".png");
			file.renameTo(newFile);
			entity.setPhoto(new ImagePath(newFile.getAbsolutePath()));
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
	}

	public Palestrante find(Object key) {
		return getEntityManager().find(Palestrante.class, key);
	}

	public List<Palestrante> getAll() {
		TypedQuery<Palestrante> query = getEntityManager().createNamedQuery("getAllPalestrantes", Palestrante.class);
		return query.getResultList();
	}

	public void update(Palestrante entity,boolean updateImage) {
		if(updateImage){
			ImagePersistence imgPersistence = ImagePersistence.getInstance();
			String pathToDelete = entity.getPhoto().getPath();
			File file = new File(imgPersistence.getTmpFilePath());
			File newFile = new File(imgPersistence.getServerPath()+imgPersistence.randomName()+".png");
			file.renameTo(newFile);
			entity.setPhoto(new ImagePath(newFile.getAbsolutePath()));
			if(!pathToDelete.endsWith("palestranteDefalut.png")){
				imgPersistence.delete(pathToDelete);
			}
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
	}

	public void delete(Object id,boolean deleteImage) {
		Palestrante palestrante = find(id);
		if(deleteImage){
			ImagePersistence imgPersistence = ImagePersistence.getInstance();
			imgPersistence.delete(palestrante.getPhoto().getPath());
		}
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(palestrante);
		getEntityManager().getTransaction().commit();
	}

}
