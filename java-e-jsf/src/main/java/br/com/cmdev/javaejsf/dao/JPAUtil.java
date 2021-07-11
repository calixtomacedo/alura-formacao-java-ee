package br.com.cmdev.javaejsf.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("java-e-jsf");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	public static EntityManager getEntityManagerCmdev() {
		return Persistence.createEntityManagerFactory("java-e-jsf-cmdev-db").createEntityManager();
	}
}
