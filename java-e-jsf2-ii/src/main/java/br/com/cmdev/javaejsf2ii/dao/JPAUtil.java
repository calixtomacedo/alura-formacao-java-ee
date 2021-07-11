package br.com.cmdev.javaejsf2ii.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager getEntityManager() throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = Persistence.createEntityManagerFactory("java-e-jsf").createEntityManager();
		} catch (IllegalStateException e) {
			throw new Exception(e);
		}
		return entityManager;
	}
	
	public static EntityManager getEntityManagerCmdev() throws Exception {
		EntityManager entityManager = null;
		try {
			entityManager = Persistence.createEntityManagerFactory("java-e-jsf-cmdev-db").createEntityManager();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return entityManager;
	}
}
