package br.com.cmdev.javaejsf2iii.util.jpa;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cmdev.javaejsf2iii.util.annotations.CmdevDB;

public class EntityManagerProducer implements Serializable {

	private static final long serialVersionUID = -9186322432272303681L;

	//@PersistenceUnit(unitName = "java-e-jsf") private EntityManagerFactory aluraFactory;
	
	private static EntityManagerFactory alura = Persistence.createEntityManagerFactory("aluradb");
	private static EntityManagerFactory cmdev = Persistence.createEntityManagerFactory("cmdevdb");

	
    //@PersistenceUnit(unitName = "java-e-jsf-cmdev-db") private EntityManagerFactory cmdevFactory;
	
	@Produces
	@RequestScoped
	@Default
	public EntityManager getAluraEntityManager() {
		return alura.createEntityManager();
	}

	@Produces
	@RequestScoped
	@CmdevDB
	public EntityManager getCmdevEntityManager() {
		return cmdev.createEntityManager();
	}


	public void close(@Disposes EntityManager entityManager) {
		if(entityManager.isOpen()) {
			entityManager.close();
		}
	}

}
